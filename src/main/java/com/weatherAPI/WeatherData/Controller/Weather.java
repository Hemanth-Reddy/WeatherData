package com.weatherAPI.WeatherData.Controller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "weather")
public class Weather {

	/**
	 * Id associated with the task.
	 */
	@Id
	@Column(name = "date")
	private String date;

	@Column(name = "high_temp")
	private double highTemp;

	@Column(name = "low_temp")
	private double lowTemp;

	@Column(name = "humidity")
	private double humidity;

	@Column(name = "condition")
	private String descriptiveCondition;

	public Weather() {

	}

	public Weather(String date, double highTemp, double lowTemp, double humidity, String descriptiveCondition) {
		super();
		this.date = date;
		this.highTemp = highTemp;
		this.lowTemp = lowTemp;
		this.humidity = humidity;
		this.descriptiveCondition = descriptiveCondition;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getHighTemp() {
		return highTemp;
	}

	public void setHighTemp(double highTemp) {
		this.highTemp = highTemp;
	}

	public double getLowTemp() {
		return lowTemp;
	}

	public void setLowTemp(double lowTemp) {
		this.lowTemp = lowTemp;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public String getDescriptiveCondition() {
		return descriptiveCondition;
	}

	public void setDescriptiveCondition(String descriptiveCondition) {
		this.descriptiveCondition = descriptiveCondition;
	}

}
