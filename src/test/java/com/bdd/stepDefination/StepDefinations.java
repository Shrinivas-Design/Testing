package com.bdd.stepDefination;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bdd.Pojo.AddPlace;
import com.bdd.Pojo.Location;
import com.bdd.Pojo.ResponseAddPlace;
import com.bdd.resources.ResourceAPI;
import com.bdd.resources.TestDataBuild;
import com.bdd.resources.Utils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;

public class StepDefinations extends Utils {
	TestDataBuild ts;
	RequestSpecification reqspecification;
	ResponseAddPlace resAdd;
	public  Response res;
	JsonPath js;
	String val;
	public static String placeID;
	public StepDefinations() {
		ts= new TestDataBuild();
		
	}
	
	@Given("combine request payload and query Parameter with {string} {string} {string}")
	public void combine_request_payload_and_query_Parameter_with(String name, String language, String address) throws IOException {
		reqspecification =given().spec(requesSpecification().body(ts.addPlacePayloadData(name, language, address)));
	}

	@When("user call {string} with {string} http request")
	public void user_call_with_http_request(String resource, String method) {
		  
		ResourceAPI values =	ResourceAPI.valueOf(resource);
		
		System.out.println(values.getResource());
		
		 if(method.equalsIgnoreCase("post")) {
			 res = reqspecification.log().all().
	                 when().post(values.getResource());
		 }else if(method.equalsIgnoreCase("get")){
			 res = reqspecification.log().all().
	                 when().get(values.getResource());
			 
		 }else if(method.equalsIgnoreCase("delete")) {
			 res = reqspecification.log().all().
	                 when().delete(values.getResource());
		 }
		 else {
			System.out.println("Specific method is not mentioned");
		 }
		 
	}

	@Then("verify that getting response code as {string}")
	public void verify_that_getting_response_code_as(String string) {
		  assertEquals(res.getStatusCode(),200);
	}

	@Then("veriy that {string} as {string}")
	public void veriy_that_as(String responseKey, String responseValue) {
		
	     assertEquals(getjasonPath(res,responseKey), responseValue);
	}
	@Then("verify placeId created to {string} using {string}")
	public void verify_placeId_created_to_using(String field, String resource) throws IOException {
		 placeID =getjasonPath(res,"place_id");
		//while working on designing this framework i havent mentioned this reqspecification because of this fetting error 
		reqspecification=given().spec(requesSpecification()).queryParam("place_id", placeID);
	    user_call_with_http_request(resource, "get");
	    assertEquals(res.getStatusCode(),200);
	    assertEquals(getjasonPath(res,"name"), field);
	}
	
	@Given("Request the Payload for delete API")
	public void request_the_Payload_for_delete_API() throws IOException {
	   
		reqspecification=given().spec(requesSpecification()).body(ts.deletePlacePayload(placeID));
	}

	
	}
