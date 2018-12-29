package com.ukgang.universal.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ukgang.universal.client.ClimateDataSource;
import com.ukgang.universal.domain.Climate;
import com.ukgang.universal.domain.GPSLocation;
import com.ukgang.universal.repository.ClimateRepository;

@Component
public class ClimateService {

	@Autowired
	private ClimateRepository repository;
	@Resource(name = "OpenWeatherMapClient")
	private ClimateDataSource extraResource;

	public void forcasting() {
		List<Climate> climates = extraResource.forcastClimateByCityId();
		repository.repleteRecord(climates);
	}

	public void recording() {
		Climate climate = extraResource.getWeather();
		repository.repleteRecord(climate);
	}

	public List<String> getClimateRecord(Date from, Date to, GPSLocation location) {
		return DataIntegrationService.calculate(repository.getRecord(from, to, location));
	}

	public List<Climate> getClimateRecord(Date from, Date to) {
		return repository.getRecord(from, to, null);
	}
}
