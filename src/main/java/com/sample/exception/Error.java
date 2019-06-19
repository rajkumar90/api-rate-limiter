package com.sample.exception;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Error")
public class Error {
	@XmlAttribute(name = "code")
	private String code;

	@XmlAttribute(name = "description")
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Error(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}
}
