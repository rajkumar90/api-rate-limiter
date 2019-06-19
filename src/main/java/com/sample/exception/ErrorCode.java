package com.sample.exception;

/**
 * Common error codes
 */
public enum ErrorCode {

	/**
	 * DAO layer error codes 501 - 550 401 - 450
	 */
	DAO_UNEXPECTED_ERROR("500.501"), DAO_CONSTRAINT_VIOLATION_EXCEPTION(
			"500.502"),

	/**
	 * BIZ layer error codes 551 - 590 451 - 490
	 */
	BIZ_INVALID_ENTITY_ID("400.451"),

	/**
	 * Client side error codes 001 - 100
	 */
	CLIENT_INVALID_CREDENTIAL("401.001"), WEB_APPLICATION_ERROR("400.002"), CLIENT_FORBIDDEN_ACCESS("403.401"), TOO_MANY_REQUESTS("400.429"),
	/**
	 * Service layer error codes 591 - 599
	 */
	SERVICE_UNEXPECTED_ERROR("500.591"), SERVICE_ERROR("500.592");

	private String errorCode;

	ErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return error code
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Converts from string error to {@link ErrorCode}
	 * 
	 * @param label
	 *            error code in string
	 * @return matching {@link ErrorCode} or null
	 */
	public static ErrorCode getErrorCode(String label) {
		for (ErrorCode errorCode : ErrorCode.values()) {
			if (errorCode.errorCode.equalsIgnoreCase(label)) {
				return errorCode;
			}
		}
		return null;
	}
}
