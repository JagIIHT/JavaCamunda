package com.example.camunda;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JSONTraverse {
	
	private static ObjectMapper mapper = new ObjectMapper();
	private static Map<String, String> jsonValueStore = new HashMap<>();
	private static Map<String, String> jsonFieldStore = new HashMap<>();

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {

		String json = "{\"name\":\"college1\",\"@replace@\":\"COLLEGE.RANK\",\"address\":{\"street1\":\"3124\",\"street2\":null,\"city\":null,\"@replace@\":\"COLLEGE.ZIP\",\"country\":\"US\"},\"students\":[{\"firstName\":\"john\",\"lastName\":\"wick\",\"age\":31,\"address\":{\"street1\":\"31\",\"street2\":null,\"city\":\"Bangalore\",\"@replace@\":\"STUDENT.1.STATE\",\"country\":\"India\"}}]}";

		jsonValueStore.put("COLLEGE.RANK", "1");
		jsonValueStore.put("COLLEGE.ZIP", "637102");
		jsonValueStore.put("STUDENT.1.STATE", "TN");
		
		jsonFieldStore.put("COLLEGE.RANK", "{\"rank\":\"\"}");
		jsonFieldStore.put("COLLEGE.ZIP", "{\"zip\":\"\"}");
		jsonFieldStore.put("STUDENT.1.STATE", "{\"state\":\"\"}");
		JsonNode jsonNode = mapper.readTree(json);
		traverse(jsonNode);
		System.out.println(jsonNode.toPrettyString());
	}
	
	private static void traverse(JsonNode root) throws JsonMappingException, JsonProcessingException{
	    
	    if(root.isObject()){
	    	Iterator<Entry<String,JsonNode>> nodes = root.fields();
	    	ObjectNode replacementNode = mapper.createObjectNode();
	        while(nodes.hasNext()) {
	        	Entry<String, JsonNode> node = nodes.next();
	        	if (node.getValue().isObject() || node.getValue().isArray()) {
	        		traverse(node.getValue());
	        	} else {
	        		if(node.getKey().equalsIgnoreCase("@replace@")) {
		            	nodes.remove();
//		            	replacementNode.put(jsonFieldStore.get(node.getValue().asText()), jsonValueStore.get(node.getValue().asText()));
		            	replacementNode.setAll((ObjectNode)mapper.readTree(jsonFieldStore.get(node.getValue().asText())));
		            }
	        	}
	        }
	        ObjectNode rootObject = (ObjectNode)root;
	        rootObject.setAll(replacementNode);
	        
	    } else if(root.isArray()){
	        ArrayNode arrayNode = (ArrayNode) root;
	        for(int i = 0; i < arrayNode.size(); i++) {
	            JsonNode arrayElement = arrayNode.get(i);
	            traverse(arrayElement);
	        }
	    }
	}

}
