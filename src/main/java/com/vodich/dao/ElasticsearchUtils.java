package com.vodich.dao;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodich.core.bean.Scenario;

public class ElasticsearchUtils {
	
	private static Client esClient;
	private static ObjectMapper mapper;
	public static void init() {
		Settings.Builder settings = Settings.builder();
        Path dataPath = FileSystems.getDefault().getPath("data");
        settings.put("cluster.name", "index");
        settings.put("http.port", 9200);
        settings.put("network.host","localhost");
        settings.put("transport.tcp.port", 9300);
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
	
	
}
