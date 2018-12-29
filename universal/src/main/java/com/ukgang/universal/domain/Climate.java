package com.ukgang.universal.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Climate implements Serializable {

	private static final long serialVersionUID = 1113758163659961964L;

	private Long id;
	private Date date;
	private GPSLocation location;
	private Double humidity;
	private boolean isCelsius;
	private Double tempMin;
	private Double tempMax;
	private Double pressure;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public GPSLocation getLocation() {
		return location;
	}

	public void setLocation(GPSLocation location) {
		this.location = location;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public boolean isCelsius() {
		return isCelsius;
	}

	public void setCelsius(boolean isCelsius) {
		this.isCelsius = isCelsius;
	}

	public Double getTempMin() {
		return tempMin;
	}

	public void setTempMin(Double tempMin) {
		this.tempMin = tempMin;
	}

	public Double getTempMax() {
		return tempMax;
	}

	public void setTempMax(Double tempMax) {
		this.tempMax = tempMax;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getPressure() {
		return pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
