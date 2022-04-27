package com.eureka.discovery.service;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.eureka.discovery.exception.InvalidTrainNoException;
import com.eureka.discovery.exception.NoTrainExistException;
import com.eureka.discovery.exception.StationNotExistException;
import com.eureka.discovery.exception.TrainNotRunningOnThisDayException;
import com.eureka.discovery.model.Detail;
import com.eureka.discovery.model.Train;
import com.eureka.discovery.repository.TrainRepository;

@Service
public class TrainServiceImpl implements TrainService {

	@Autowired
	private TrainRepository trainRepository;

	@Value("${get.city:Karachi City}")
	private String city;

	// *---------------------------- Exception Messages -------------------------*
	String noTrainsExistException = " !!! There is no train exist on this train Information !!!";
	String stationNotExistException = "!!! there is no Station exist on this Train Route !!!";
	String trainNotRunningOnThisDayException = "!!! This train is not running usually on that day !!!";
	String invalidTrainNoException = "!!! Invalid Train Number !!! ";
	// *-------------------------------------------------------------------------*

	@Override
	public List<Train> displayAllTrains() {
		return trainRepository.findAll();
	}



	@Override
	public Train displayTrain(String trainNo) {
		try {
			if (trainRepository.findAll().parallelStream().filter(p -> p.getTrain_no().equals(trainNo))
					.collect(Collectors.toList()).isEmpty()) {
				throw new NoTrainExistException(noTrainsExistException);
			}

		} catch (NoTrainExistException e) {
			e.getMessage();
		}
		return (trainRepository.findAll().parallelStream().filter(p -> p.getTrain_no().equals(trainNo))
				.collect(Collectors.toList()).get(0));
	}

	@Override
	public String updateData(List<Train> trains) {
		trainRepository.saveAll(trains);
		return "Data Saved Sucessfully";
	}



	@Override
	public Train getTrainByTrainNo(String train_no) {
		return trainRepository.findAll().parallelStream().filter(p -> p.getTrain_no().equals(train_no))
				.collect(Collectors.toList()).get(0);
	}

	@Override
	public boolean trainExistByTrainNo(String train_no) {
		if (trainRepository.findAll().parallelStream().filter(p -> p.getTrain_no().equals(train_no))
				.collect(Collectors.toList()).isEmpty()) {
			return false;
		} else {
			return true;
		}

	}


	@Override
	public String deleteAllTrains(String confirmation) {
		if (confirmation.equalsIgnoreCase("No"))
			return "Deletion Unsucessful";
		trainRepository.deleteAll();
		return "All Train Data is deleted Successfully";

	}

	@Override
	public String deleteTrain(String train_no, String confirmation) {
		if (confirmation.equalsIgnoreCase("No"))
			return "Deletion Unsucessful";
		try {
			if (trainRepository.findAll().parallelStream().noneMatch(p -> train_no.equalsIgnoreCase(p.getTrain_no())))
				throw new InvalidTrainNoException("Invalid Train no.");
		} catch (InvalidTrainNoException e) {
			return e.getMessage();

		}

		trainRepository.delete(trainRepository.findAll().parallelStream().filter(p -> p.getTrain_no().equals(train_no))
				.collect(Collectors.toList()).get(0));
		return "Train Data is deleted Sucessfully";
	}

	@Override
	public String addTrain(Train train) {
		try {
			if (isNumeric(train.getTrain_no())) {
				throw new InvalidTrainNoException(invalidTrainNoException);
			}
		} catch (InvalidTrainNoException e) {
			return e.getMessage();
		}
		trainRepository.save(train);
		return "All Train Data is deleted Sucessfully";

	}

	public boolean isNumeric(String train_no) {
		try {
			Double.parseDouble(train_no);
		} catch (NumberFormatException e) {
			return false;

		}
		return true;
	}



	@Override
	public String addListOfTrains(List<Train> trains) {
		trainRepository.saveAll(trains);
		return "Added All Trains";
	}

}
