package com.deutschebank.app.configuration;

import java.util.UUID;

import javax.annotation.PreDestroy;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.NodeClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import com.deutschebank.app.client.model.Conference;
import com.deutschebank.app.repository.ConferenceRepository;

@Configuration
@EnableElasticsearchRepositories
class ApplicationConfiguration {

	@Autowired ElasticsearchOperations operations;
	@Autowired ConferenceRepository repository;

	@Bean
	public NodeClientFactoryBean client() {
		NodeClientFactoryBean bean = new NodeClientFactoryBean(true);
		bean.setClusterName(UUID.randomUUID().toString());
		bean.setEnableHttp(false);

		return bean;
	}

	@Bean
	public ElasticsearchTemplate elasticsearchTemplate(Client client) throws Exception {
		return new ElasticsearchTemplate(client);
	}

	@PreDestroy
	public void deleteIndex() {
		operations.deleteIndex(Conference.class);
	}

}
