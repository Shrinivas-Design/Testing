package com.bdd.stepDefination;

import java.io.IOException;

import com.bdd.stepDefination.StepDefinations;

import io.cucumber.java.Before;

//Imp Point
  
/*   Here important point is that Hooks.java must be present in same pkg where StepDefination.java is present
 * 
 *   This class should be executed if placeID==null

 */

public class Hooks {
	
	
	@Before("@DeletePlace")
	public void applyHooks() throws IOException 
	{ 
		StepDefinations	st = new StepDefinations();
		if(StepDefinations.placeID==null) {
	st.combine_request_payload_and_query_Parameter_with("ShrinivasMahabole", "English", "India");
	st.user_call_with_http_request("AddPlaceAPI", "post");
	st.verify_placeId_created_to_using("ShrinivasMahabole", "GetPlaceAPI");
		}
	
	}
	
}
