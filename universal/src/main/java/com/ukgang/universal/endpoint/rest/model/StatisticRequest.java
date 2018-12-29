package com.ukgang.universal.endpoint.rest.model;

import java.util.Date;

import com.ukgang.universal.domain.GPSLocation;

public class StatisticRequest {
	private Date from;
	private Date to;
	private GPSLocation location;

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public GPSLocation getLocation() {
		return location;
	}

	public void setLocation(GPSLocation location) {
		this.location = location;
	}
}
