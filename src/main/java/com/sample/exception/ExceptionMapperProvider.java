package com.sample.exception;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Provider for mapping exceptions,for returns platform services.
 * 
 * @author msing23
 *
 */
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Provider
@Component
public final class ExceptionMapperProvider extends AbstractExceptionMapper implements ExceptionMapper<Exception> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionMapperProvider.class);

	@Override
	public Response toResponse(final Exception exception) {
		LOGGER.error("Exception occured : " + exception);
		Response response = null;

		ErrorResponse error = new ErrorResponse(new Error(ErrorCode.TOO_MANY_REQUESTS.getErrorCode(),
				"Too many requests received within a short time"));
		response = Response.status(Status.TOO_MANY_REQUESTS).entity(error).type(MediaType.APPLICATION_JSON).build();

		return response;
	}

}