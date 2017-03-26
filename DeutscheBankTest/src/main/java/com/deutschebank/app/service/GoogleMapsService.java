package com.deutschebank.app.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.deutschebank.app.client.model.GeoLocation;
import com.deutschebank.app.exception.GoogleMapException;

@Service
public class GoogleMapsService {
	
	@Value("${google.api.geocode.url}")
	private String googleGeoCodeURL;
	
	@Value("${google.api.distance.matrix.urll}")
	private String googleDistanceMartixURL;
	
	public GeoLocation getLatLongPositions(String address) {
		GeoLocation geoLocation = null;
		try{
			int responseCode = 0;
			String api = googleGeoCodeURL + "?address=" + URLEncoder.encode(address, "UTF-8")
							+ "&sensor=true";
			System.out.println("URL : " + api);
			URL url = new URL(api);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.connect();
			responseCode = httpConnection.getResponseCode();
			if (responseCode == 200) {
				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				;
				Document document = builder.parse(httpConnection.getInputStream());
				XPathFactory xPathfactory = XPathFactory.newInstance();
				XPath xpath = xPathfactory.newXPath();
				XPathExpression expr = xpath.compile("/GeocodeResponse/status");
				String status = (String) expr.evaluate(document, XPathConstants.STRING);
				if (status.equals("OK")) {
					expr = xpath.compile("//geometry/location/lat");
					String latitude = (String) expr.evaluate(document, XPathConstants.STRING);
					expr = xpath.compile("//geometry/location/lng");
					String longitude = (String) expr.evaluate(document, XPathConstants.STRING);
					geoLocation = GeoLocation.builder().latitude(latitude).longitude(longitude).build();
				} else {
					throw new GoogleMapException("Error from the API - response status: " + status);
				}
			}
		}
		catch(Exception exception){
			throw new GoogleMapException("Something went wrong while connecting to  ");
		}
		return geoLocation;
	}
}