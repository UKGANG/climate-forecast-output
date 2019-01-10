package com.ukgang.universal.startup;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.ukgang.universal.repository.ClimateRepository;

@Configuration
public class SystemMetadata {

    private static String apiKey;
    private static String taoxiaId;

	@Autowired
	private ClimateRepository repository;

	@PostConstruct
	public void init() {
		apiKey = repository.getConfig(PropertyKey.API_KEY);
		taoxiaId = repository.getConfig(PropertyKey.TAOXIA_ID);
	}

	public static String getApiKey() {
		return apiKey;
	}

	public static String getTaoxiaId() {
		return taoxiaId;
	}
	public enum PropertyKey {
		API_KEY, TAOXIA_ID
	}
}
