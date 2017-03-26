package com.deutschebank.app.client.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import lombok.Data;
import lombok.experimental.Builder;

@Data
@Builder
@Document(indexName = "conference-index", type = "geo-class-point-type", shards = 1, replicas = 0,
		refreshInterval = "-1")
public class Conference {
	private GeoPoint location;
}
