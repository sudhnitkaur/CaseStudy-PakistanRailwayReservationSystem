package com.eureka.discovery.model;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Trains")
public class Train {

	@Id
	private String train_no;
	private String train_name;
	private String start_from;
	private LocalTime departure_time;
	private String to_destination;
	private LocalTime arrival_time;
	private String train_type;
	private Map<String, Double> coaches_fair = new LinkedHashMap<>();
	private int train_length;

	private Map<String, Detail> route;

	private List<String> run_days;

	public String getTrain_no() {
		return train_no;
	}

	public void setTrain_no(String train_no) {
		this.train_no = train_no;
	}

	public String getTrain_name() {
		return train_name;
	}

	public void setTrain_name(String train_name) {
		this.train_name = train_name;
	}

	public String getStart_from() {
		return start_from;
	}

	public void setStart_from(String start_from) {
		this.start_from = start_from;
	}

	public LocalTime getDeparture_time() {
		return departure_time;
	}

	public void setDeparture_time(LocalTime departure_time) {
		this.departure_time = departure_time;
	}

	public String getTo_destination() {
		return to_destination;
	}

	public void setTo_destination(String to_destination) {
		this.to_destination = to_destination;
	}

	public LocalTime getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(LocalTime arrival_time) {
		this.arrival_time = arrival_time;
	}

	public String getTrain_type() {
		return train_type;
	}

	public void setTrain_type(String train_type) {
		this.train_type = train_type;
	}

	public List<String> getRun_days() {
		return run_days;
	}

	public void setRun_days(List<String> run_days) {
		this.run_days = run_days;
	}

	public Map<String, Double> getCoaches_fair() {
		return coaches_fair;
	}

	public void setCoaches_fair(Map<String, Double> coaches_fair) {
		this.coaches_fair = coaches_fair;
	}

	public int getTrain_length() {
		return train_length;
	}

	public void setTrain_length(int train_length) {
		this.train_length = train_length;
	}



	public Map<String, Detail> getRoute() {
		return route;
	}

	public void setRoute(Map<String, Detail> route) {
		this.route = route;
	}

	public Train(String train_no, String train_name, String start_from, LocalTime departure_time, String to_destination,
			LocalTime arrival_time, String train_type, List<String> run_days, Map<String, Double> coaches_fair,
			int train_length, Map<String, Detail> route) {
		super();
		this.train_no = train_no;
		this.train_name = train_name;
		this.start_from = start_from;
		this.departure_time = departure_time;
		this.to_destination = to_destination;
		this.arrival_time = arrival_time;
		this.train_type = train_type;
		this.run_days = run_days;
		this.coaches_fair = coaches_fair;
		this.train_length = train_length;
		this.route = route;
	}

	public Train() {
		super();
		// TODO Auto-generated constructor stub
	}
}
