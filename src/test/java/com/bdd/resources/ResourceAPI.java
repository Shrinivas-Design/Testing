package com.bdd.resources;

public enum ResourceAPI {
	AddPlaceAPI("/maps/api/place/add/json"),
	DeletePlaceAPI("/maps/api/place/delete/json"),
	GetPlaceAPI("/maps/api/place/get/json");
	
	public String resource;
	ResourceAPI(String resource){
		this.resource=resource;
		
	}
	
	public String getResource() {
		return resource;
	}
	
}
