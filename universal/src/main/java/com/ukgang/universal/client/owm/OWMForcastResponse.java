package com.ukgang.universal.client.owm;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ukgang.universal.domain.Climate;
import com.ukgang.universal.domain.GPSLocation;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OWMForcastResponse implements VendorResponse<List<Climate>> {
	@JsonProperty("city")
	private City city;
	@JsonProperty("list")
	private List<Record> list;

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Record> getList() {
		return list;
	}

	public void setList(List<Record> list) {
		this.list = list;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class City {
		@JsonProperty("id")
		private Long id;
		@JsonProperty("name")
		private String name;
		@JsonProperty("coord")
		private Coord coord;
		@JsonProperty("country")
		private String country;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Coord getCoord() {
			return coord;
		}

		public void setCoord(Coord coord) {
			this.coord = coord;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

	}

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
	private static class Record {
		@JsonProperty("dt")
		private Date dt;
		@JsonProperty("main")
		private Main main;

		public Date getDt() {
			return dt;
		}

		public void setDt(Long dt) {
			this.dt = new Date(dt * 1000);
		}

		public Main getMain() {
			return main;
		}

		public void setMain(Main main) {
			this.main = main;
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

	@Override
	public List<Climate> extract() {
		GPSLocation location = new GPSLocation();
		Optional.ofNullable(this.getCity()).map(City::getId).ifPresent(location::setRefId);
		Optional.ofNullable(this.getCity()).map(City::getCountry).ifPresent(location::setCountry);
		Optional.ofNullable(this.getCity()).map(City::getName).ifPresent(location::setCity);
		Optional.ofNullable(this.getCity()).map(City::getCoord).map(Coord::getLon).ifPresent(location::setLongitude);
		Optional.ofNullable(this.getCity()).map(City::getCoord).map(Coord::getLat).ifPresent(location::setLatitude);
		return this.getList().stream()
				.map(rec -> {
					Climate climate = new Climate();
					climate.setDate(rec.getDt());
					climate.setCelsius(true);
					Optional.ofNullable(rec.getMain()).map(Main::getHumidity).ifPresent(climate::setHumidity);
					Optional.ofNullable(rec.getMain()).map(Main::getPressure).ifPresent(climate::setPressure);
					Optional.ofNullable(rec.getMain()).map(Main::getTempMin).ifPresent(climate::setTempMin);
					Optional.ofNullable(rec.getMain()).map(Main::getTempMax).ifPresent(climate::setTempMax);
					climate.setLocation(location);
					return climate;
				}).collect(Collectors.toList());
	}

}
