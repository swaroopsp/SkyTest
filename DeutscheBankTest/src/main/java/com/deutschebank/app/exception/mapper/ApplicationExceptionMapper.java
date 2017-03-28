package com.deutschebank.app.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.deutschebank.app.exception.ApplicationException;
/**
 * Created by swaroop on 27/03/2017.
 */
@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {
	@Override
	public Response toResponse(ApplicationException e) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
						.type(MediaType.TEXT_PLAIN)
						.entity("Catching Exception : " + e.getMessage()).build();
	}
}