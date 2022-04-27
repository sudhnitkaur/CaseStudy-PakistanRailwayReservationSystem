package com.eureka.discovery.service;

import java.util.List;


import com.eureka.discovery.model.Train;


public interface TrainService {
	
	 // *------------------- Basic Data Display Functionality -------------------*
    public List<Train> displayAllTrains();

    public Train displayTrain(String trainNo);

    public String updateData(List<Train> trains);

    public String addListOfTrains(List<Train> trains);
    
    public Train getTrainByTrainNo(String train_no);

    public boolean trainExistByTrainNo(String train_no);

    public String deleteAllTrains(String confirmation);

    public String deleteTrain(String train_no,String confirmation);

    public String addTrain(Train train);
    // *-----------------------------------------------------------------------------*

}
