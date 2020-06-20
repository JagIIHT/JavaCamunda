package com.example.camunda;

import java.util.Iterator;

import javax.json.JsonObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JSONTest2 {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {

		String json = "{\"name\":\"college1\",\"@replace@\":\"COLLEGE.RANK\",\"address\":{\"street1\":\"3124\",\"street2\":null,\"city\":null,\"@replace@\":\"COLLEGE.ZIP\",\"country\":\"US\"},\"students\":[{\"firstName\":\"john\",\"lastName\":\"wick\",\"age\":31,\"address\":{\"street1\":\"31\",\"street2\":null,\"city\":\"Bangalore\",\"@replace@\":\"STUDENT.1.COUNTY\",\"country\":\"India\"}}]}";

		JsonNode jsonNode = new ObjectMapper().readTree(json);
		traverse(jsonNode);
		System.out.println(jsonNode.asText());
	}
	
	public static void traverse(JsonNode root) throws JsonMappingException, JsonProcessingException{
	    
	    if(root.isObject()){
	        Iterator<String> fieldNames = root.fieldNames();
	        while(fieldNames.hasNext()) {
	            String fieldName = fieldNames.next();
//	            System.out.println(fieldName);
	            JsonNode fieldValue = root.get(fieldName);
	            if(fieldName.equalsIgnoreCase("@replace@")) {
	            	ObjectNode obj = (ObjectNode)root;
	            	obj.remove("@replace@");
	            	obj.set("rank", new ObjectMapper().readTree("1"));
	            }
	            
	            
	            traverse(fieldValue);
	        }
	    } else if(root.isArray()){
	        ArrayNode arrayNode = (ArrayNode) root;
	        for(int i = 0; i < arrayNode.size(); i++) {
	            JsonNode arrayElement = arrayNode.get(i);
	            traverse(arrayElement);
	        }
	    } else {
	    	root.fieldNames().forEachRemaining(System.out::println);
//	        System.out.println(root.textValue());
	    }
	}

}
