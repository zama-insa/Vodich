package com.vodich.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.search.SearchHit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodich.core.bean.Result;
import com.vodich.core.bean.Scenario;
import com.vodich.core.util.VodichUtils;

public class ElasticsearchUtils {

	private static Client esClient;
	private static ObjectMapper mapper;
	private static Properties properties;

	public static void init() {
		Settings.Builder settings = Settings.builder();
		Path dataPath = FileSystems.getDefault().getPath(getProperties().getProperty("dataPath"));
		System.out.println("Elasticsearch data folder: " + dataPath.toFile().getAbsolutePath());
		settings.put("cluster.name", getProperties().getProperty("cluster.name"));
		settings.put("http.port", getProperties().getProperty("http.port"));
		settings.put("network.host", getProperties().getProperty("network.host"));
		settings.put("transport.tcp.port", getProperties().getProperty("transport.tcp.port"));
		settings.put("path.home", dataPath.toFile().getAbsolutePath());
		settings.put("path.data", dataPath.toFile().getAbsolutePath());
		settings.build();
		Node esNode = NodeBuilder.nodeBuilder().settings(settings).node();
		esClient = esNode.client();
		mapper = new ObjectMapper();
		// Create index
		try {
			esClient.admin().indices().prepareCreate("vodich").execute().actionGet();
		} catch (IndexAlreadyExistsException e) {
			// never mind if index already exists
		}
		String vodichMapping;
		try {
			vodichMapping = VodichUtils.readResource("mapping/scenario.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		try {
			esClient.admin().indices().preparePutMapping("vodich").setType("scenario").setSource(vodichMapping)
			.execute().actionGet();
		} catch (Throwable e) {
			e.printStackTrace();
			return;
		}
		// Wait for healty status of shards
		ClusterHealthResponse health;
		do {
			System.out.println("Waiting for elasticsearch yellow status");
			health = esClient.admin().cluster().prepareHealth().setWaitForYellowStatus().execute().actionGet();
		} while (health.isTimedOut());
	}

	public static void close() {
		esClient.close();
	}

	/**
	 * 
	 * @param scenario
	 * @return Auto-generated id of the scenario in Elasticsearch
	 */
	public static String saveScenario(Scenario scenario) {
		try {
			byte[] json = mapper.writeValueAsBytes(scenario);
			return esClient.prepareIndex("vodich", "scenario").setSource(json).get().getId();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void updateScenario(Scenario scenario) {
		try {
			byte[] json = mapper.writeValueAsBytes(scenario);
			esClient.prepareIndex("vodich", "scenario", scenario.getId()).setSource(json).get().getId();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public static DeleteResponse deleteScenario(String scenarioId) {
		return esClient.prepareDelete("vodich", "scenario", scenarioId).execute().actionGet();
	}

	public static List<Scenario> loadScenarii() {

		List<Scenario> listScenarii = new ArrayList<Scenario>();

		// Query to search in ES
		SearchResponse response = esClient.prepareSearch("vodich").setTypes("scenario").execute().actionGet();

		// Serialize to Scenario
		for (SearchHit hit : response.getHits()) {
			Scenario scenario;
			try {
				scenario = mapper.readValue(hit.getSourceAsString(), Scenario.class);
				scenario.setId(hit.getId());
				listScenarii.add(scenario);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listScenarii;
	}

	public static Scenario load(String scenarioID) {
		Scenario scenario;
		GetResponse response = esClient.prepareGet("vodich", "scenario", scenarioID).execute().actionGet();
		if (!response.isExists()) {
			return null;
		}
		try {
			scenario = mapper.readValue(response.getSourceAsBytes(), Scenario.class);
			scenario.setId(response.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return scenario;

	}

	public static Scenario loadByName(String scenarioName) throws DAOException {
		Scenario scenario;
		SearchResponse response = esClient.prepareSearch("vodich").setTypes("scenario")
				.setQuery(QueryBuilders.matchQuery("name", scenarioName)).execute().actionGet();
		try {
			if (response.getHits().getTotalHits() < 1)
				return null;
			if (response.getHits().getTotalHits() > 1)
				throw new DAOException("Multiple return values");
			scenario = mapper.readValue(response.getHits().getAt(0).getSourceAsString(), Scenario.class);
			scenario.setId(response.getHits().getAt(0).getId());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return scenario;

	}

	public static Result loadScenarioResult(String resultId) {
		Result result;
		GetResponse response = esClient.prepareGet("vodich", "result", resultId)
				.execute()
				.actionGet();
		try {
			if (!response.isExists()) {
				return null;
			}
			result = mapper.readValue(response.getSourceAsBytes(), Result.class);
			result.setId(response.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return result;
	}


	public static List<Result> loadAllScenarioResults() {
		
		List<Result> listResults = new ArrayList<>();

		// Query to search in ES
		SearchResponse response = esClient.prepareSearch("vodich").setTypes("result").execute().actionGet();

		// Serialize to Scenario
		for (SearchHit hit : response.getHits()) {
			Result result;
			try {
				result = mapper.readValue(hit.getSourceAsString(), Result.class);
				result.setId(hit.getId());
				listResults.add(result);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listResults;
	}



	public static IndexResponse saveScenarioResult(Result result)  {
		try {


			// save result first
			byte[] resultJson = mapper.writeValueAsBytes(result);
			IndexResponse indexResponse = esClient.prepareIndex("vodich", "result").setSource(resultJson).get();

			// save bulk result units later (for kibana graphs)
			BulkRequestBuilder requestBuilder = esClient.prepareBulk();
			if (result.getResult() == null) return indexResponse;
			for (Object singleResult : result.getResult()) {

				@SuppressWarnings("unchecked")
				Map<String, Object> singleResultMap = (Map<String, Object>) singleResult;
				List<Object> results_unit = (List<Object>) singleResultMap.get("messageResults");
				for (Object o: results_unit) {
					Map<String, Object> mo = (Map<String, Object>) o;
				XContentBuilder builder;
			
				try {
					
					builder = XContentFactory.jsonBuilder()
							.startObject()
							.field("id", mo.get("id"))
							.field("time", mo.get("time"))
							.field("consumer", singleResultMap.get("consumer"))
							.endObject();
				} catch (IOException e) {
					continue;
				}
				System.out.println(indexResponse);
				IndexRequestBuilder request = esClient
						.prepareIndex("result_unit", indexResponse.getId())
						.setSource(builder);
				requestBuilder.add(request);
			}
			BulkResponse bulkResponse = requestBuilder.execute().actionGet();
			int items= bulkResponse.getItems().length;
			System.out.println("indexed [" + items + "] items, with failures? [" + bulkResponse.hasFailures()  + "]");
			}
			return indexResponse;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}


	public static Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
			try {
				properties.load(
						ElasticsearchUtils.class.getClassLoader().getResourceAsStream("elasticsearch.properties"));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return properties;
	}

}
