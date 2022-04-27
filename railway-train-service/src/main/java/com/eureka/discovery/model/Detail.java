package com.eureka.discovery.model;

import java.time.LocalTime;

public class Detail {

	private Integer price;
	private LocalTime arrival_time;
	private LocalTime departure_time;
	public Detail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Detail(Integer price, LocalTime arrival_time, LocalTime departure_time) {
		super();
		this.price = price;
		this.arrival_time = arrival_time;
		this.departure_time = departure_time;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public LocalTime getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(LocalTime arrival_time) {
		this.arrival_time = arrival_time;
	}
	public LocalTime getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(LocalTime departure_time) {
		this.departure_time = departure_time;
	}
	
	

}
