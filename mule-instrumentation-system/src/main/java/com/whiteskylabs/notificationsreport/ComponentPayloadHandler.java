package com.whiteskylabs.notificationsreport;

/**
 * This class is used to get component's payload.
 */
public class ComponentPayloadHandler {

	/**
	 * Get Payload if its a type of String or byte[]
	 * 
	 * @param payload
	 * @return Payload as String
	 */
	public String getComponentPayload(Object payload) {

		// if Payload is a type of String, return it.
		if (payload instanceof String) {
			return payload.toString();
		}
		// If Payload is a type of Byte[], convert it into String and return.
		else if (payload instanceof byte[]) {
			return new String((byte[]) payload);
		}
		// Return null if the Payload is neither String nor byte[].
		else {
			return null;
		}
	}
}
