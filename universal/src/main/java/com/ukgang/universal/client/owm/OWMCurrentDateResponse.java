package com.ukgang.universal.client.owm;

import java.util.Date;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ukgang.universal.domain.Climate;
import com.ukgang.universal.domain.GPSLocation;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OWMCurrentDateResponse implements VendorResponse<Climate> {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("coord")
	private Coord coord;
	@JsonProperty("sys")
	private Sys sys;
	@JsonProperty("main")
	private Main main;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class Coord {
		@JsonProperty("lon")
		private String lon;
		@JsonProperty("lat")
		private String lat;

		public String getLon() {
			return lon;
		}

		public void setLon(String lon) {
			this.lon = lon;
		}

		public String getLat() {
			return lat;
		}

		public void setLat(String lat) {
			this.lat = lat;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class Sys {
		@JsonProperty("country")
		private String country;

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class Main {
		@JsonProperty("temp_min")
		private Double tempMin;
		@JsonProperty("temp_max")
		private Double tempMax;
		@JsonProperty("humidity")
		private Double humidity;
		@JsonProperty("pressure")
		private Double pressure;

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

		public Double getHumidity() {
			return humidity;
		}

		public void setHumidity(Double humidity) {
			this.humidity = humidity;
		}

		public Double getPressure() {
			return pressure;
		}

		public void setPressure(Double pressure) {
			this.pressure = pressure;
		}
	}

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	@Override
	public Climate extract() {
		Date now = new Date();
		GPSLocation location = new GPSLocation();
		location.setCity(name);
		location.setRefId(id);
		Optional.ofNullable(this.getSys()).map(Sys::getCountry).ifPresent(location::setCountry);
		Optional.ofNullable(this.getCoord()).map(Coord::getLon).ifPresent(location::setLongitude);
		Optional.ofNullable(this.getCoord()).map(Coord::getLat).ifPresent(location::setLatitude);
		Climate climate = new Climate();
		climate.setCelsius(true);
		climate.setDate(now);
		Optional.ofNullable(this.getMain()).map(Main::getHumidity).ifPresent(climate::setHumidity);
		Optional.ofNullable(this.getMain()).map(Main::getPressure).ifPresent(climate::setPressure);
		Optional.ofNullable(this.getMain()).map(Main::getTempMin).ifPresent(climate::setTempMin);
		Optional.ofNullable(this.getMain()).map(Main::getTempMax).ifPresent(climate::setTempMax);
		climate.setLocation(location);
		return climate;
	}

}
