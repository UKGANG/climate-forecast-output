package com.ukgang.universal.client;

import java.util.List;

import com.ukgang.universal.domain.Climate;

public interface ClimateDataSource {

	public Climate getWeather();
	public List<Climate> forcastClimateByCityId();
}
