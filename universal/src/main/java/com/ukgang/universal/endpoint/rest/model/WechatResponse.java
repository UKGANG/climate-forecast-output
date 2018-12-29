package com.ukgang.universal.endpoint.rest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ukgang.universal.endpoint.rest.JsonDateSerializer;
import com.ukgang.universal.endpoint.rest.JsonDoubleSerializer;

public class WechatResponse implements Serializable {

	private static final long serialVersionUID = 5546089083818765246L;

	private Integer peek;
	private Integer mode;
	private Integer nadir;
	private List<EstimationItem> items;

	public Integer getPeek() {
		return peek;
	}

	public void setPeek(Integer peek) {
		this.peek = peek;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public Integer getNadir() {
		return nadir;
	}

	public void setNadir(Integer nadir) {
		this.nadir = nadir;
	}

	public List<EstimationItem> getItems() {
		return items;
	}

	public void setItems(List<EstimationItem> items) {
		this.items = items;
	}

	public Integer getMinTemp() {
		if (null == nadir) {
			return 0;
		}
		return 19 * nadir / 20;
	}

	public static class EstimationItem implements Serializable {

		private static final long serialVersionUID = -6840335567840762796L;

		private Date date;
		private Double minTemp;
		private Double maxTemp;
		private Double estimation;

		@JsonSerialize(using = JsonDateSerializer.class)
		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		@JsonSerialize(using = JsonDoubleSerializer.class)
		public Double getMinTemp() {
			return minTemp;
		}

		public void setMinTemp(Double minTemp) {
			this.minTemp = minTemp;
		}

		@JsonSerialize(using = JsonDoubleSerializer.class)
		public Double getMaxTemp() {
			return maxTemp;
		}

		public void setMaxTemp(Double maxTemp) {
			this.maxTemp = maxTemp;
		}

		@JsonSerialize(using = JsonDoubleSerializer.class)
		public Double getEstimation() {
			return estimation;
		}

		public void setEstimation(Double estimation) {
			this.estimation = estimation;
		}
	}
}
