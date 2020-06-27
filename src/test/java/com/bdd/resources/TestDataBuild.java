package com.bdd.resources;

import java.util.ArrayList;
import java.util.List;

import com.bdd.Pojo.AddPlace;
import com.bdd.Pojo.Location;

public class TestDataBuild {
     
	public AddPlace addPlacePayloadData(String name, String language, String address) {
		AddPlace p = new AddPlace();
		p = new AddPlace();
		p.setAccuracy(50);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress(address);
		p.setWebsite("http://google.com");
		p.setLanguage(language);
		List<String> ls = new ArrayList<String>();
		ls.add("shoe park");
		ls.add("shop");
		p.setTypes(ls);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}
	
	public String deletePlacePayload(String placeID) {
		return "{\"place_id\":\""+placeID+"\"}";
	}
	
}
