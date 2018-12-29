package com.ukgang.universal.startup;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ClimateRecordInitializer {

	@PostConstruct
	public void init() {
		// Read from bulk
	}
}
