package com.deutschebank.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.junit.Test;
import org.mockito.Mockito;

import com.deutschebank.app.ShopController;
import com.deutschebank.app.exception.GoogleMapException;
import com.deutschebank.app.exception.mapper.GoogleMapExceptionMapper;
import com.deutschebank.app.service.GoogleMapsService;
import com.deutschebank.test.utils.TestUtils;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.core.ClassNamesResourceConfig;
import com.sun.jersey.spi.container.servlet.WebComponent;
import com.sun.jersey.spi.inject.SingletonTypeInjectableProvider;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

/**
 * Created by swaroop on 27/03/2017.
 */
public class ShopControllerExceptionTest extends JerseyTest {
	public static GoogleMapsService googleMapsService = Mockito.mock(GoogleMapsService.class);

	@Override
	public WebAppDescriptor configure() {
		return new WebAppDescriptor.Builder()
						.initParam(WebComponent.RESOURCE_CONFIG_CLASS, ClassNamesResourceConfig.class.getName())
						.initParam(ClassNamesResourceConfig.PROPERTY_CLASSNAMES,
							ShopController.class.getName() + ";"
							+ MockGoogleMapServiceProvider.class.getName() + ";"
							+ GoogleMapExceptionMapper.class.getName())
						.build();
	}
	
	@Test
	public void shouldThrowGoogleMapException() throws IOException {
		String result = new TestUtils().getFile("input.json");
		Mockito.doThrow(new GoogleMapException()).when(googleMapsService).getLatLongPositions(result);
		ClientResponse response = resource().path("/shops/add").post(ClientResponse.class);
		assertEquals(Status.INTERNAL_SERVER_ERROR, response.getClientResponseStatus());
	}

	@Provider
	public static class MockGoogleMapServiceProvider
					extends SingletonTypeInjectableProvider<Context, GoogleMapsService> {

		public MockGoogleMapServiceProvider() {
			super(GoogleMapsService.class, googleMapsService);
		}
	}

}