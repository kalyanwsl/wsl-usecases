package com.whiteskylabs.loggermanager;

import org.apache.commons.lang.StringEscapeUtils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *	Convert InstrumentationBO object into JSON message
 */
public class LogJSONMessageFormatImpl implements ILogMessageFormat {

	@Override
	public String generateLogMessage(InstrumentationBO instrumentationBO) {

		Gson gson = new GsonBuilder()
		 .disableHtmlEscaping()
	     .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
	     .setPrettyPrinting()
	     .create();

		//  Convert Object to Json
		String json = gson.toJson(instrumentationBO);

		return StringEscapeUtils.unescapeJavaScript(json);
	}

}
