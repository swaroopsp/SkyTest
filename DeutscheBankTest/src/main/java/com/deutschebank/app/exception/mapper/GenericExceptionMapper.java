package com.deutschebank.app.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
/**
 * Created by swaroop on 27/03/2017.
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
	@Override
	public Response toResponse(Throwable e) {

		return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).type(MediaType.TEXT_PLAIN)
						.entity("Catching Exception : " + e.getMessage()).build();
	}
}