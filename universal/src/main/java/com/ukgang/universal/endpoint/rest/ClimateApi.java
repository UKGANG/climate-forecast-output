package com.ukgang.universal.endpoint.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.ukgang.universal.domain.Climate;
import com.ukgang.universal.endpoint.rest.model.WechatResponse;
import com.ukgang.universal.service.ClimateService;

@RestController
@RequestMapping("/statistics")
public class ClimateApi {

	@Autowired
	private ClimateService climateService;

	@GetMapping(path = "/forecast")
	public void trigger1() {
		climateService.forcasting();
	}

	@GetMapping(path = "/record")
	public void trigger2() {
		climateService.recording();
	}

	@GetMapping(path = "/test")
	public String getRecordsTest(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
		from = DateUtils.truncate(from, Calendar.MILLISECOND);
		to = DateUtils.truncate(to, Calendar.MILLISECOND);
		to = DateUtils.addDays(to, 1);
		List<String> result = climateService.getClimateRecordStr(from, to);
		return Optional.ofNullable(result).orElseGet(Lists::newArrayList).stream()
				.collect(Collectors.joining("<br><br>"));
	}

	@GetMapping
	public WechatResponse getRecords(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
		from = DateUtils.truncate(from, Calendar.MILLISECOND);
		to = DateUtils.truncate(to, Calendar.MILLISECOND);
		to = DateUtils.addMilliseconds(to, -1);
		List<Climate> result = climateService.getClimateRecord(from, to);
		List<WechatResponse.EstimationItem> items = result.stream()
				.map(c -> {
					double temp = (c.getTempMin() + c.getTempMax()) / 2 + 25;
					double estimation = temp < 31.5 ? 660 : -0.44 * temp * temp + 18.1 * temp + 525;
					WechatResponse.EstimationItem item = new WechatResponse.EstimationItem();
					item.setDate(c.getDate());
					item.setMinTemp(c.getTempMin());
					item.setMaxTemp(c.getTempMax());
					item.setEstimation(estimation);
					return item;
				}).collect(Collectors.toList());
		List<Double> outputList = items.stream()
				.map(WechatResponse.EstimationItem::getEstimation)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());

		Integer peek = (int) outputList.stream().mapToDouble(Double::new).max().orElse(0.0);
		Integer mode = (int) outputList.stream().mapToDouble(Double::new).average().orElse(0.0);
		Integer nadir = (int) outputList.stream().mapToDouble(Double::new).min().orElse(0.0);
		WechatResponse response = new WechatResponse();
		response.setItems(items);
		response.setPeek(peek);
		response.setMode(mode);
		response.setNadir(nadir);

		return response;
	}
}
