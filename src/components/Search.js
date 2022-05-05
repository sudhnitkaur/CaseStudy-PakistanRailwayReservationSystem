import React, { useState } from 'react'
import { useHistory } from 'react-router-dom';
import SearchService from '../Services/SearchService'
import { Link } from 'react-router-dom'

const Search = () => {
    const [startPoint, setstartPoint] = useState('')
    const [endPoint, setendPoint] = useState('')
    const [trainDate, settrainDate] = useState('')
    const history = useHistory();
    const [Trains, setTrains] = useState([]);


    const searchTrain = (e) => {
        e.preventDefault();
        SearchService.getTrainById(startPoint, endPoint, trainDate).then((response) => {
            console.log(response)
            setTrains(response.data);
        }).catch(error => {
            console.log(error)
        })
    }

    return (
        <>
            <section className='showcase login'>
                <div className='showcase-overlay'>

                    <form className='form-control'>
                        <input type='text' name='From' id='From' placeholder='Enter Start Station'
                            value={startPoint}
                            onChange={(e) => setstartPoint(e.target.value)} />

                        <input type='text' name='To' id='To' placeholder='Enter Destination'
                            value={endPoint}
                            onChange={(e) => setendPoint(e.target.value)} />

                        <input type='text' name='date' id='date' placeholder='Enter Date'
                            value={trainDate}
                            onChange={(e) => settrainDate(e.target.value)} />

                        <button className="btn btn-info" to='/trainList' onClick={(e) => searchTrain(e)} >Search Trains</button>
                        <div className="container">
                            <br></br>
                            <br></br>
                            <h2 className="text-center">List Of Trains Available</h2>

                            <br></br>
                            <br></br>
                            <div class="row">
                                <div class="table-responsive ">
                                    <table class="table table-striped table-hover table-bordered">
                                    <thead>
                                        <tr>
                                         <th>Train No </th>
                                         <th>StartPoint </th>
                                         <th>EndPoint </th>
                                     <th>DeptTime </th>
                                       <th>Duration </th>
                                        <th>NoOfSeats </th>
                                        <th>SleeperClass </th>
                                        <th>SecondClass </th>
                                        <th>FirstClass </th>
                                        <th>TrainDate </th>                                            <th>Actions</th>
                                        </tr>
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
                                                        <td>{TrainDetails.SecondClass}</td>                                                        <td>{TrainDetails.firstClass}</td>
                                                        <td>{TrainDetails.trainDate}</td>
                                                        
                                                            


                                                        </tr>
                                                )
                                            }
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </section>
        </>
    )
}

export default Search