package com.orangehrm.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiUtility {
	
	//Method to send the GET Request
	public static Response sendGetRequest(String endpoint) {
		return RestAssured.get(endpoint);
	}
	
	//Method to send the POST Request
	public static Response sendPostRequest(String endPoint,String payLoad) {
		return RestAssured.given().header("Content-Type","application/json")
						   .body(payLoad)
						   .post();
	}
	
	//Method to validate the Response status
	public static boolean validateStatusCode(Response response,int statusCode) {
		return response.getStatusCode()==statusCode;
	}
	
	//Method to extract value from JSON response
	public static String getJsonValue(Response response,String value) {
		return response.jsonPath().getString(value);
	}
	
}
