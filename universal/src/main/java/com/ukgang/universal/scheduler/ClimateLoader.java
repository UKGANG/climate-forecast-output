package com.ukgang.universal.scheduler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ukgang.universal.client.owm.OpenWeatherMapClient;
import com.ukgang.universal.service.ClimateService;

@Configuration
@EnableScheduling
public class ClimateLoader {

	private static final Logger LOG = LoggerFactory.getLogger(ClimateLoader.class);
	private static final String CRON_EVERY_HOUR = "0 30 0-23 * * ? ";
	private static final String CRON_EVERY_DAY = "0 0 23 * * ?";
	@Autowired
	private ClimateService climateService;
	OpenWeatherMapClient weatherAPIClient;

	@Scheduled(cron = CRON_EVERY_DAY)
	public void forecast() {
		LOG.info("Start forcasting on {}", new Date());
		climateService.forcasting();
		LOG.info("Forcasting done {}", new Date());
	}

	@Scheduled(cron = CRON_EVERY_HOUR)
	public void record() {
		LOG.info("Start recording on {}", new Date());
		climateService.recording();
		LOG.info("Recording done {}", new Date());
	}
}
