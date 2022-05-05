import React, { useState, useEffect } from 'react'
import { useHistory, useParams } from 'react-router-dom';
import AdminService from '../Services/AdminService'
import { Link } from 'react-router-dom'

const AddTrain = () => {
    const [trainNo, settrainNo] = useState('')
    const [startPoint, setstartPoint] = useState('')
    const [endPoint, setendPoint] = useState('')
    const [arrivalTime, setarrivalTime] = useState('')
    const [deptTime, setdeptTime] = useState('')
    const [duration, setduration] = useState('')
    const [noOfSeats, setnoOfSeats] = useState('')
    const [sleeperClass, setsleeperClass] = useState('')
    const [SecondClass, setSecondClass] = useState('')
    const [firstClass, setfirstClass] = useState('')
    const [trainDate, settrainDate] = useState('')
    const history = useHistory();
    const { id } = useParams();

    const saveOrUpdateTrain = (e) => {
        e.preventDefault();
        const TrainDetails = { trainNo, startPoint, endPoint, arrivalTime, deptTime, duration, noOfSeats, sleeperClass, SecondClass, firstClass, trainDate }
        if (id) {
            AdminService.upDateTrain(id, TrainDetails).then((response) => {
                var e=JSON.stringify(response.data);
                alert(e);
                history.push('/adminTrainList')
            }).catch(error => {
                console.log(error)
            })
        } else {
            AdminService.addTrain(TrainDetails).then((response) => {
                var e=JSON.stringify(response.data);
                alert(e);
                history.push('/adminTrainList')
            }).catch(error => {
                console.log(error)
            })

        }


    }

    useEffect(() => {
        AdminService.getTrainById(trainNo).then((response) => {
            settrainNo(response.data.trainNo)
            setstartPoint(response.data.startPoint)
            setendPoint(response.data.endPoint)
            setarrivalTime(response.data.arrivalTime)
            setdeptTime(response.data.deptTime)
            setduration(response.data.duration)
            setnoOfSeats(response.data.noOfSeats)
            setsleeperClass(response.data.sleeperClass)
            setSecondClass(response.data.SecondClass)
            setfirstClass(response.data.firstClass)
            settrainDate(response.data.trainDate)

        }).catch(error => {
            console.log(error)
        })

    }, [])

    const title = () => {

        if (id) {
            return <h2 className="text-center">Update Train</h2>
        } else {
            return <h2 className="text-center">Add Train</h2>
        }
    }
    return (
        <div>
            <br></br><br></br>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">

                    <br></br>
                        {
                            title()
                        }
                        <div className="card-body">
                            <form>
                                <div className="form-group mb-2">
                                    <label className="form-label">TrainNo</label>

                                    <input
                                        type="text"
                                        placeholder="Enter train Number"
                                        name="trainNo"
                                        className="form-control"
                                        value={trainNo}
                                        onChange={(e) => settrainNo(e.target.value)}>
                                    </input>

                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">StartPoint</label>

                                    <input
                                        type="text"
                                        placeholder="Enter start point"
                                        name="startPoint"
                                        className="form-control"
                                        value={startPoint}
                                        onChange={(e) => setstartPoint(e.target.value)}>
                                    </input>

                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">EndPoint</label>

                                    <input
                                        type="text"
                                        placeholder="Enter end point"
                                        name="endPoint"
                                        className="form-control"
                                        value={endPoint}
                                        onChange={(e) => setendPoint(e.target.value)}>
                                    </input>

                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">ArrivalTime</label>

                                    <input
                                        type="text"
                                        placeholder="Enter Arrival Time"
                                        name="arrivalTime"
                                        className="form-control"
                                        value={arrivalTime}
                                        onChange={(e) => setarrivalTime(e.target.value)}>
                                    </input>

                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">DeptTime</label>

                                    <input
                                        type="text"
                                        placeholder="Enter Departure Time"
                                        name="deptTime"
                                        className="form-control"
                                        value={deptTime}
                                        onChange={(e) => setdeptTime(e.target.value)}>
                                    </input>

                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">Duration</label>

                                    <input
                                        type="text"
                                        placeholder="Enter Duration"
                                        name="duration"
                                        className="form-control"
                                        value={duration}
                                        onChange={(e) => setduration(e.target.value)}>
                                    </input>

                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">NoOfSeats</label>

                                    <input
                                        type="text"
                                        placeholder="Enter Number of seats"
                                        name="noOfSeats"
                                        className="form-control"
                                        value={noOfSeats}
                                        onChange={(e) => setnoOfSeats(e.target.value)}>
                                    </input>

                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">SleeperClass Fare</label>

                                    <input
                                        type="text"
                                        placeholder="Enter Sleeper Class Fare"
                                        name="sleeperClass"
                                        className="form-control"
                                        value={sleeperClass}
                                        onChange={(e) => setsleeperClass(e.target.value)}>
                                    </input>

                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">SecondClass Fare</label>

                                    <input
                                        type="text"
                                        placeholder="Enter Second Class Fare"
                                        name="SecondClass"
                                        className="form-control"
                                        value={SecondClass}
                                        onChange={(e) => setSecondClass(e.target.value)}>
                                    </input>

                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">FirstClass Fare</label>

                                    <input
                                        type="text"
                                        placeholder="Enter First Class Fare"
                                        name="firstClass"
                                        className="form-control"
                                        value={firstClass}
                                        onChange={(e) => setfirstClass(e.target.value)}>
                                    </input>

                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">TrainDate</label>

                                    <input
                                        type="text"
                                        placeholder="Enter trainDate"
                                        name="trainDate"
                                        className="form-control"
                                        value={trainDate}
                                        onChange={(e) => settrainDate(e.target.value)}>
                                    </input>

                                </div>
                                <button className="btn btn-success" onClick={(e) => saveOrUpdateTrain(e)} >Submit </button>
                                <Link to="/login" className="btn btn-danger"> Cancel </Link>
                            
                            </form>
                            <br></br>
                        </div>

                    </div>

                </div>
            </div>
        </div>
    )
}

export default AddTrain
