package com.timeforge.weather.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WeatherDTO implements Serializable {

	private String date;
	private double highTemp;
	private double lowTemp;
	private int humidity;
	private String descriptiveCondition;
	public WeatherDTO(String date, double highTemp, double lowTemp, int humidity, String descriptiveCondition) {
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
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
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
