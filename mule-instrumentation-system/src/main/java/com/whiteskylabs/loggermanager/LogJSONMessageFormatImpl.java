package com.whiteskylabs.loggermanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 *	Convert InstrumentationBO object into JSON message
 */
public class LogJSONMessageFormatImpl implements ILogMessageFormat {

	@Override
	public String generateLogMessage(InstrumentationBO instrumentationBO) {

		Gson gson = new Gson();
		
		//  Convert Object to Json
		String json = gson.toJson(instrumentationBO);

		// Make JSON pretty message
		gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(json.toString());
		String prettyJsonString = gson.toJson(je);
		
		return prettyJsonString;
	}

}
