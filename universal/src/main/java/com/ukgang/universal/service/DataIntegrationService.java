package com.ukgang.universal.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.ukgang.universal.domain.Climate;

public abstract class DataIntegrationService {

	private DataIntegrationService() {
	}

	public static final List<String> calculate(List<Climate> data) {
		return Optional.ofNullable(data)
				.orElseGet(Lists::newArrayList)
				.stream()
				.map(Climate::toString)
				.collect(Collectors.toList());
	}
}
