package com.ukgang.universal.client.owm;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ukgang.universal.client.ClimateDataSource;
import com.ukgang.universal.domain.Climate;
import com.ukgang.universal.startup.SystemMetadata;

@Component("OpenWeatherMapClient")
public class OpenWeatherMapClient implements ClimateDataSource {

	public static final String BEAN_NAME = "OpenWeatherMapClient";
    private static final String OWM_URL = "http://api.openweathermap.org/data/2.5";
    private static final String QUERY_BY_REGION_NAME = "weather?id=%s";
    private static final String FORCAST_BY_REGION_ID = "forecast?id=%s";
    private static final String URL_FORMATTER = "%s/%s&units=metric&APPID=%s";
    private String urlGetWeather;
    private String urlForcastClimateByCityId;

    @Autowired
	private Client jerseyClient;

    @PostConstruct
    public void init() {
    		urlGetWeather = String.format(URL_FORMATTER, OWM_URL, QUERY_BY_REGION_NAME, SystemMetadata.getApiKey());
    		urlGetWeather = String.format(urlGetWeather, SystemMetadata.getTaoxiaId());
    		urlForcastClimateByCityId = String.format(URL_FORMATTER, OWM_URL, FORCAST_BY_REGION_ID, SystemMetadata.getApiKey());
    		urlForcastClimateByCityId = String.format(urlForcastClimateByCityId, SystemMetadata.getTaoxiaId());
    }

    public final Climate getWeather() {
		return build(urlGetWeather).get().readEntity(OWMCurrentDateResponse.class).extract();
	}

	public final List<Climate> forcastClimateByCityId() {
		return build(urlForcastClimateByCityId).get().readEntity(OWMForcastResponse.class).extract();
	}

	private Builder build(String host) {
    		return jerseyClient
				.target(host)
				.request()
                .accept(MediaType.APPLICATION_JSON_TYPE);
	}

}
