package com.sample.exception;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ErrorResponse")
public class ErrorResponse {
	private Error error;

	public ErrorResponse(Error error) {
		super();
		this.error = error;
	}

	@XmlElement
	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}
}
