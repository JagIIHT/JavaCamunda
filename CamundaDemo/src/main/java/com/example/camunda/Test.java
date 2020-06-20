package com.example.camunda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.JsonPath;

public class Test {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		// First Approach
		ObjectMapper mapper = new ObjectMapper();
		ProductRules pr = new ProductRules("Life", null, null);
		String address = "{\"street1\": \"123\", \"street2\":\"Bangalore\"}";
		InjectableValues.Std iv = new InjectableValues.Std().addValue("address", address);
		pr = mapper.reader(iv).forType(ProductRules.class).readValue(mapper.writeValueAsString(pr));
		System.out.println(mapper.writeValueAsString(pr));
		
		// Second approach
		String prs = "{\"productName\":\"Life\"}";
		String str = JsonPath.parse(prs).put("$", "address", address).jsonString();
		System.out.println(str);
		
		
		String json = "{\"productName\":\"Life\"}";
        Gson gson = new Gson();
        JsonObject inputObj  = gson.fromJson(json, JsonObject.class);
        JsonObject newObject = new JsonObject() ;
        newObject.addProperty("address", "{\"street1\": \"123\", \"street2\":\"Bangalore\"}");
//        inputObj.get("results").getAsJsonArray().add(newObject);
//        System.out.println(inputObj);
        
        College c = new College();
        c.getStudents().add(new Student());
        System.out.println(mapper.writeValueAsString(c));
	}
}
