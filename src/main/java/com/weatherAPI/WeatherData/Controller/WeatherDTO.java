package com.weatherAPI.WeatherData.Controller;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WeatherDTO implements Serializable {

	private String date;
	private double highTemp;
	private double lowTemp;
	private double humidity;
	private String descriptiveCondition;
	public WeatherDTO(String date, double highTemp, double lowTemp, double humidity, String descriptiveCondition) {
		super();
		this.date = date;
		this.highTemp = highTemp;
		this.lowTemp = lowTemp;
		this.humidity = humidity;
		this.descriptiveCondition = descriptiveCondition;
	}
	public WeatherDTO() {

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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
