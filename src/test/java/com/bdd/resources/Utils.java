package com.bdd.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification req;
	ResponseSpecification rs;
	Properties prop;
	FileInputStream fis;
	/*
	 * public Utils() { req = new }
	 */

	public RequestSpecification requesSpecification() throws IOException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("RequestLogging.txt"));

			req = new RequestSpecBuilder().setBaseUri(globalProp("basrURI")).addQueryParam("key",globalProp("authKey"))
					.setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			return req;
		}
		return req;
	}

	public ResponseSpecification responseSpecification() {
		rs = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return rs;
	}
	
	public String globalProp(String key) throws IOException {
	    fis  = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\19ThAprilRestCompleteProject\\APIFrameWork\\src\\test\\java\\com\\bdd\\resources\\global.properties");
        prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	public String getjasonPath(Response response, String key) {
		String value =response.asString();
		System.out.println("Value of Response is *******" +value);
		JsonPath js = new JsonPath(value);
		//return js.get(key).toString();
		return js.get(key);
		
	}

}
