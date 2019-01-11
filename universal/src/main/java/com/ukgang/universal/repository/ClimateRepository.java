package com.ukgang.universal.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.ukgang.universal.dao.ClimateMapper;
import com.ukgang.universal.domain.Climate;
import com.ukgang.universal.domain.GPSLocation;
import com.ukgang.universal.startup.SystemMetadata.PropertyKey;

@Component
public class ClimateRepository {

	@Autowired
	private ClimateMapper mapper;

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void repleteRecord(Climate climate) {
		GPSLocation location = climate.getLocation();
		Long gpsLocationId = mapper.searchGPSLocationId(location);
		if (null == gpsLocationId) {
			gpsLocationId = mapper.insertGPSLocation(location);
		}
		location.setId(gpsLocationId);
		Date now = DateUtils.truncate(climate.getDate(), Calendar.HOUR);
		climate.setDate(now);
		Climate forcast = mapper.getRecord(now, location);
		if (null != forcast) {
			climate.setId(forcast.getId());
			mapper.updateRecord(climate);
		} else {
			mapper.insertRecords(Lists.newArrayList(climate));
		}
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void repleteRecord(List<Climate> climates) {
		climates.forEach(this::repleteRecord);
	}

	public List<Climate> getRecord(Date from, Date to, GPSLocation location) {
		return mapper.getRecords(from, to, location);
	}

	public String getConfig(PropertyKey key) {
		return mapper.getConfig(key.name());
	}
}
