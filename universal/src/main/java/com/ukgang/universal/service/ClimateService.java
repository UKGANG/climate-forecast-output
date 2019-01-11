package com.ukgang.universal.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.ukgang.universal.client.ClimateDataSource;
import com.ukgang.universal.domain.Climate;
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

	public List<String> getClimateRecordStr(Date from, Date to) {
		return DataIntegrationService.calculate(Lists.newArrayList(extraResource.getWeather()));
	}

	public List<Climate> getClimateRecord(Date from, Date to) {
		return repository.getRecord(from, to, null);
	}
}
