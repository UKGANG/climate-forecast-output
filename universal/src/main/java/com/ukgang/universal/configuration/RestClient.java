package com.ukgang.universal.configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.RequestEntityProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestClient {

	@Bean
    public Client jerseyClient() throws Exception {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setDefaultMaxPerRoute(200);
		cm.setMaxTotal(400);
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(10000)
				.setConnectionRequestTimeout(11000)
				.setSocketTimeout(20000)
				.build();
		ClientConfig clientConfig = new ClientConfig()
				.property(ApacheClientProperties.CONNECTION_MANAGER, cm)
				.property(ApacheClientProperties.REQUEST_CONFIG, requestConfig)
				.property(ClientProperties.REQUEST_ENTITY_PROCESSING, RequestEntityProcessing.BUFFERED);

		ApacheConnectorProvider connectorProvider = new ApacheConnectorProvider();
		clientConfig.connectorProvider(connectorProvider);

		
		return ClientBuilder.newBuilder().withConfig(clientConfig).build();
	}
}
