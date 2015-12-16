package com.vodich.dao;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.search.SearchHit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodich.core.bean.Scenario;

public class ElasticsearchUtils {
	
	private static Client esClient;
	private static ObjectMapper mapper;
	private static Properties properties;
	public static void init() {
		System.out.println("Elasticsearch data folder: " + getProperties().getProperty("dataPath"));
		Settings.Builder settings = Settings.builder();
        Path dataPath = FileSystems.getDefault().getPath(getProperties().getProperty("dataPath"));
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
        	//never mind if index already exists
        }
        
        //Wait for healty status of shards
        ClusterHealthResponse health;
        do {
            System.out.println("Waiting for elasticsearch yellow status");
            health = esClient.admin().cluster().prepareHealth().setWaitForYellowStatus().execute().actionGet();
        } while (health.isTimedOut());
	}
	
	public static void close() {
		esClient.close();
	}
	
	public static IndexResponse saveScenario(Scenario scenario) {
		try {
			byte[] json = mapper.writeValueAsBytes(scenario);
			return esClient.prepareIndex("vodich", "scenario").setSource(json).get();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static DeleteResponse deleteScenario(String scenarioId){
		
		SearchResponse response = esClient.prepareSearch("vodich")
				 .setTypes("scenario")
				 .setQuery(QueryBuilders.matchQuery("id", scenarioId))
				.execute()
				.actionGet();
		System.out.println(response.getHits().getAt(0).getSourceAsString());
			
		return esClient.prepareDelete("vodich", "scenario", response.getHits().getAt(0).id()).execute().actionGet();
	}
	
	public static List<Scenario> loadScenarii(){
		
		List<Scenario> listScenarii = new ArrayList<Scenario>();
		
		//Query to search in ES
		SearchResponse response = esClient.prepareSearch("vodich")
				 .setTypes("scenario")
				 .execute()
				 .actionGet();
		
		//Serialize to Scenario
		 for(SearchHit hit : response.getHits()){
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
		GetResponse response = esClient.prepareGet("vodich", "scenario", scenarioID)
				.execute()
				.actionGet();
		try {
			scenario = mapper.readValue(response.getSourceAsBytes(), Scenario.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return scenario;

	}
	public static Scenario loadByName(String scenarioName) throws DAOException {
		Scenario scenario;
		SearchResponse response = esClient.prepareSearch("vodich")
				.setTypes("scenario")
				.setQuery(QueryBuilders.matchQuery("name",scenarioName))
				.execute()
				.actionGet();
		try {
			if (response.getHits().getTotalHits() < 1) return null;
			if (response.getHits().getTotalHits() > 1) throw new DAOException("Multiple return values");
			scenario = mapper.readValue(response.getHits().getAt(0).getSourceAsString(), Scenario.class);
			scenario.setId(response.getHits().getAt(0).getId());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return scenario;

	}

	public static Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
			try {
				properties.load(ElasticsearchUtils.class.getClassLoader().getResourceAsStream("elasticsearch.properties"));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return properties;
	}
	

	
}
