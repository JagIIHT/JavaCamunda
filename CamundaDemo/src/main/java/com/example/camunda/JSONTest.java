package com.example.camunda;

import java.io.StringReader;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import org.json.JSONObject;

public class JSONTest {

	public static void main(String[] args) {
		String json = "{\"name\":\"college1\",\"@replace@\":\"COLLEGE.RANK\",\"address\":{\"street1\":\"3124\",\"street2\":null,\"city\":null,\"@replace@\":\"COLLEGE.ZIP\",\"country\":\"US\"},\"students\":[{\"firstName\":\"john\",\"lastName\":\"wick\",\"age\":31,\"address\":{\"street1\":\"31\",\"street2\":null,\"city\":\"Bangalore\",\"@replace@\":\"STUDENT.1.COUNTY\",\"country\":\"India\"}}]}";

		JsonParser jsonParser = Json.createParser(new StringReader(json));
		int count = 0;
		String result = null;
		 
		while(jsonParser.hasNext()) {
		    Event e = jsonParser.next();
		     
		    if (e == Event.KEY_NAME) {
		        if(jsonParser.getString().equals("@replace@")) {
		            Event ev = jsonParser.next();
		            System.out.println(jsonParser.getLocation());
		            System.out.println(jsonParser.getValue());
		        }   
		    }
		}
	}
	
	private String replacePlaceHolder() {
		return null;
	}

}
