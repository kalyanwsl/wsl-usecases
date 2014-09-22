package com.whiteskylabs.common;

/**
 * This class is used to convert payload into String type.
 */
public class ComponentPayloadHandler {

	/**
	 * Convert Payload into String
	 * 
	 * @param payload
	 * @return Payload as String
	 */
	public String getComponentPayloadAsString(Object payload) {

		// If Payload is a type of Byte[], convert it into String and return.
		if (payload instanceof byte[]) {
			return new String((byte[]) payload);
		}
		return payload.toString();
	}
}