import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import AdminService from '../Services/AdminService'


const TrainList = () => {
    const [Trains, setTrains] = useState([])
    
    useEffect(() => {
        getAllTrains();
    }, [])

    const getAllTrains = () => {
        AdminService.getAllTrains().then((response) => {
            setTrains(response.data)
            console.log(response.date);
        }).catch(error => {
            console.log(error);
        })
    }

    const deleteTrain = (trainNo) => {
        AdminService.deleteTrain(trainNo).then((response) => {
            alert("Your train has been Deleted");
            getAllTrains();
        }).catch(error => {
            console.log(error);
        })
    }

    return (
        <div className="container">
            <br></br><br></br>
            <h2 className="text-center">List Of Trains</h2>
            <Link to="/addTrain" className="btn btn-success" >Add Train</Link>
            <br></br><br></br>
            <table className="table table-bordered table-striped">
                <thead>
                    <th>Train No </th>
                    <th>StartPoint </th>
                    <th>EndPoint </th>
                    <th>DeptTime </th>
                    <th>Duration </th>
                    <th>NoOfSeats </th>
                    <th>SleeperClass </th>
                    <th>SecondSleeper </th>
                    <th>FirstClass </th>
                    <th>TrainDate </th>
                    <th>Actions</th>
                </thead>
                <tbody>
                    {
                        Trains.map(
                            TrainDetails =>
                                <tr key={TrainDetails.Id}>
                                    <td>{TrainDetails.trainNo} </td>
                                    <td>{TrainDetails.startPoint} </td>
                                    <td>{TrainDetails.endPoint}</td>
                                    <td>{TrainDetails.deptTime}</td>
                                    <td>{TrainDetails.duration}</td>
                                    <td>{TrainDetails.noOfSeats}</td>
                                    <td>{TrainDetails.sleeperClass}</td>
                                    <td>{TrainDetails.SecondClass}</td>
                                    <td>{TrainDetails.firstClass}</td>
                                    <td>{TrainDetails.trainDate}</td>
                                    <td> <Link className="btn btn-info" to={`/edit-train/${TrainDetails.trainNo}`}> Update </Link>
                                        <button className="btn btn-danger" onClick={() => deleteTrain(TrainDetails.trainNo)}
                                            style={{ marginLeft: "10px" }} >Delete </button></td>


                                </tr>
                        )
                    }
                </tbody>

            </table>

        </div>
    )
}

export default TrainList
