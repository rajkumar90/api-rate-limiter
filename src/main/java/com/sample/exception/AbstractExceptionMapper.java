package com.sample.exception;

import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * Abstract exception mapper, implements the utility methods.
 * 
 * @author msing23
 *
 */
public abstract class AbstractExceptionMapper {

	@Context
	protected HttpHeaders headers;

	/**
	 * This method determines the media type of the response.
	 *
	 * @param headers
	 * @return MediaType
	 */
	protected static MediaType determineResponseMediaType(
			final HttpHeaders headers) {
		if (headers != null) {
			if (headers.getMediaType() == null) {
				if (acceptApplicationXml(headers
						.getRequestHeader(HttpHeaders.ACCEPT))) {
					return MediaType.APPLICATION_XML_TYPE;
				}
				return MediaType.APPLICATION_JSON_TYPE;
			} else if (acceptApplicationXml(headers
					.getRequestHeader(HttpHeaders.ACCEPT))) {
				return MediaType.APPLICATION_XML_TYPE;
			} else if (acceptApplicationJson(headers
					.getRequestHeader(HttpHeaders.ACCEPT))) {
				return MediaType.APPLICATION_JSON_TYPE;
			}
		}
		return MediaType.APPLICATION_JSON_TYPE;
	}

	/**
	 * Checks if application/xml is accepted.
	 * 
	 * @param mediaTypes
	 * @return
	 */
	protected static boolean acceptApplicationXml(final List<String> mediaTypes) {
		if (mediaTypes != null) {
			for (final String requestMediaType : mediaTypes) {
				if (requestMediaType.toLowerCase().contains(
						MediaType.APPLICATION_XML)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if application/json is accepted.
	 * 
	 * @param mediaTypes
	 * @return
	 */
	protected static boolean acceptApplicationJson(final List<String> mediaTypes) {
		if (mediaTypes != null) {
			for (final String requestMediaType : mediaTypes) {
				if (requestMediaType.toLowerCase().contains(
						MediaType.APPLICATION_JSON)) {
					return true;
				}
			}
		}
		return false;
	}

}
