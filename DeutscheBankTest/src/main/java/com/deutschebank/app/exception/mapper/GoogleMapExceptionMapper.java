package com.deutschebank.app.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.deutschebank.app.exception.GoogleMapException;
/**
 * Created by swaroop on 27/03/2017.
 */
@Provider
public class GoogleMapExceptionMapper implements ExceptionMapper<GoogleMapException> {
	@Override
	public Response toResponse(GoogleMapException e) {

		return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).type(MediaType.APPLICATION_JSON)
						.entity("Catching GoogleMapException : " + e.getMessage()).build();
	}
}