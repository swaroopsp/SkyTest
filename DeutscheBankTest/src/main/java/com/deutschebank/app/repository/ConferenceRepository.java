package com.deutschebank.app.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.deutschebank.app.client.model.Conference;

public interface ConferenceRepository extends ElasticsearchRepository<Conference, String> {
}
