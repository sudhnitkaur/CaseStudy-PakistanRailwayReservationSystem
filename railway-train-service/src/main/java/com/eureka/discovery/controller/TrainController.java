package com.eureka.discovery.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eureka.discovery.model.Train;
import com.eureka.discovery.service.TrainServiceImpl;

@RestController
@RequestMapping("/trains")
public class TrainController {

	@Autowired
	private TrainServiceImpl trainServiceImpl;
	

	@GetMapping("/public/welcome")
	public String welcome() {
		return "Welcome to Pakistan Railway Reservation portal";
	}

	
	@GetMapping("/admin/welcome")
	public String welcomeAdmin() {
		return "Welcome to Pakistan Railway Reservation portal -----> ADMIN";
	}
	
	@PutMapping("/admin/updateAllTrains")
	public String updateAllTrains(@RequestBody List<Train> list) {
		return trainServiceImpl.updateData(list);
	}

	@GetMapping("/admin/displayAllTrains")
	public List<Train> displayAllTrains() {
		return trainServiceImpl.displayAllTrains();
	}

	@GetMapping("/public/displaytrains/{trainsNo}")
	public Train displayTrains(@PathVariable String trainsNo) {
		return trainServiceImpl.displayTrain(trainsNo);
	}

	@GetMapping("/public/getTrainByTrainNo/{train_no}")
	public Train getTrainByTrainNo(@PathVariable String train_no) {
		return trainServiceImpl.getTrainByTrainNo(train_no);
	}

	@PostMapping("/admin/addTrain")
	public String addTrain(@RequestBody Train train) {
		return trainServiceImpl.addTrain(train);
	}

	@DeleteMapping("/admin/deleteTrain/{train_no}:{confirmation}")
	public String deleteTrain(@PathVariable String train_no, @PathVariable String confirmation) {
		return trainServiceImpl.deleteTrain(train_no, confirmation);
	}

	@DeleteMapping("admin/deleteAllTrains/{confirmation}")
	public String deleteAllTrains(String confirmation) {
		return trainServiceImpl.deleteAllTrains(confirmation);
	}

	@GetMapping("/public/trainExistByTrainNo/{train_no}")
	public boolean trainExistByTrainNo(@PathVariable String train_no) {
		return trainServiceImpl.trainExistByTrainNo(train_no);
	}

	@PostMapping("/admin/addListOfTrains")
	public String addListOfTrains(@RequestBody List<Train> trains) {
		return trainServiceImpl.addListOfTrains(trains);
		
	}

}
