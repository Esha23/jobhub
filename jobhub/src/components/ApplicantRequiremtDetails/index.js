import React, { useState,useEffect } from 'react';
import * as ROUTES from '../../constants/routes';
import { httpClient } from '../../utils/HTTPBaseClient';
import Button from '@material-ui/core/Button';
import Modal from '@material-ui/core/Modal';
import 'react-phone-number-input/style.css';
import PhoneInput from 'react-phone-number-input';

import { getCurrentUser } from '../../redux/selectors/userSelectors';
import {connect, useDispatch, useSelector} from 'react-redux';
import { Category } from '@material-ui/icons';

// function mapStateToPropos(state) {
//     return {
//       users: state.users
//     };
//   }

function ApplicantRequirementDetails(props) {
    const { onClose = {} } = props

    const userObject = useSelector(getCurrentUser);
    console.log(userObject);

    useEffect(async() => {
        let response = await httpClient.getData(ROUTES.GET_CATEGORY_LIST);
        setCategoryList(response);
        global.setIsSelected();
    },[]);

    global.setIsSelected = () => {
        Object.values(categoryList).map(val => {
            // console.log(val.name);
            val.isSelected=false;
            // console.log(val);
        });
    }

    const handleToggle = async({ target }) => {
        Object.values(categoryList).map(val => {
            if(val.name == target.name){
                val.isSelected = !val.isSelected;
            }
        });
        await setCategoryList(categoryList);
        console.log("updated categoryList:",categoryList);
    }

    const [contact, setContact] = useState('');
    const [gender, setGender] = useState("");
    const [experience, setExperience] = useState(0);
    const [education, setEducation] = useState("");
    const [location, setLocation] = useState("");
    const [salary, setSalary] = useState(0);
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [category, setCategory] = React.useState("");
    const [categoryList, setCategoryList] = React.useState({});

    const applicantData = {
        applicant_id: userObject.id,
        contact: '',
        gender: '',
        experience: '',
        education: '',
        location: '',
        salary:0
    }

    const recruiterData = {
        recruiter_id: userObject.id,
        category_id: 0,
        contact: '',
        gender: '',
        experience: '',
        education: '',
        location: '',
        salary: 0,
        title: '',
        description: '',
        job_status: ''
    }

    const onSubmit = async() => {
        // console.log((userObject.type === "Applicant"));
        console.log(userObject.type);
        if(userObject.type === "Applicant"){
            applicantData.contact = contact;
            applicantData.gender = gender;
            applicantData.experience = experience;
            applicantData.education = education;
            applicantData.location = location;
            applicantData.salary = salary;
            console.log("applicantdata:",applicantData)
            let response = await httpClient.postData(ROUTES.ADD_APPLICANT_DATA,applicantData);
        }
        else if(userObject.type === "Recruiter"){
            let category_id = 0;
            Object.values(categoryList).map(val => {
                if(val.name == category){
                    category_id = val.id;
                }
            });
            recruiterData.category_id = category_id;
            recruiterData.contact = contact;
            recruiterData.gender = gender;
            recruiterData.experience = experience;
            recruiterData.education = education;
            recruiterData.location = location;
            recruiterData.salary = salary;
            recruiterData.title = title;
            recruiterData.description = description;
            console.log("recruiterData:",recruiterData)
            let response = await httpClient.postData(ROUTES.ADD_RECRUITER_DATA,recruiterData);
        }
        window.location.reload();
        onClose();
    }


  
    return (
        <div>
            <div>
                <PhoneInput
                    placeholder="Enter phone number"
                    value={contact}
                    onChange={setContact}/>
                <br />
                <div onChange={e => setGender(e.target.value)}>
                    <input type="radio" value="Male" name="gender" /> Male
                    <input type="radio" value="Female" name="gender" /> Female
                    <input type="radio" value="Other" name="gender" /> Other
                </div>
                {(true) ?
                <>
                    <select style={{
                            height:30,
                            width:267,
                            paddingLeft:10,
                            marginBottom:10,
                            color: "grey"
                        }} 
                        defaultValue=""
                        onChange={e => setCategory(e.target.value)}
                        >
                            {Object.values(categoryList).map(val => (
                                <option value={val.name} style={{color: "black"}}>{val.name}</option>
                            ))}
                    </select>
                </>
                : <></>}
                <br />

                <input
                    type="text"
                    placeholder="job title"
                    name="title"
                    value={title}
                    onChange={e => setTitle(e.target.value)}
                    style={{
                        height: 30,
                        width: "90%",
                        paddingLeft: 10,
                        marginBottom: 20,
                        borderColor: "#5d575e"
                    }}
                />
                <br />

                <input
                    type="textarea"
                    placeholder="description"
                    name="description"
                    value={description}
                    onChange={e => setDescription(e.target.value)}
                    style={{
                        height: 30,
                        width: "90%",
                        paddingLeft: 10,
                        marginBottom: 20,
                        borderColor: "#5d575e"
                    }}
                />
                <br />

                <input
                    type="text"
                    placeholder="Total Experience (in years)"
                    name="experience"
                    value={experience}
                    onChange={e => setExperience(e.target.value)}
                    style={{
                        height: 30,
                        width: "90%",
                        paddingLeft: 10,
                        marginBottom: 20,
                        borderColor: "#5d575e"
                    }}
                />
                <br />

                <select style={{
                        height:30,
                        width:267,
                        paddingLeft:10,
                        marginBottom:10,
                        color: "grey"
                    }} 
                    defaultValue=""
                    onChange={e => setEducation(e.target.value)}
                    >
                    <option selected value="select highest education">Select Education</option>
                    <option value="Less than 10th" style={{color: "black"}}>Less than 10th</option>
                    <option value="10th to 12th" style={{color: "black"}}>10th to 12th</option>
                    <option value="bachelor's" style={{color: "black"}}>bachelor's</option>
                    <option value="master's" style={{color: "black"}}>master's</option>
                    <option value="diploma" style={{color: "black"}}>diploma</option>
                </select>
                <br />

                <select style={{
                        height:30,
                        width:267,
                        paddingLeft:10,
                        marginBottom:10,
                        color: "grey"
                    }} 
                    defaultValue=""
                    onChange={e => setLocation(e.target.value)}
                    >
                    <option selected value="select location">Select Location</option>
                    <option value="Lucknow" style={{color: "black"}}>Lucknow</option>
                    <option value="Delhi" style={{color: "black"}}>Delhi</option>
                    <option value="Bangalore" style={{color: "black"}}>Bangalore</option>
                    <option value="Chennai" style={{color: "black"}}>Chennai</option>
                </select>
                <br />

                <input
                    type="text"
                    placeholder="Approximate Salary"
                    name="salary"
                    value={salary}
                    onChange={e => setSalary(e.target.value)}
                    style={{
                        height: 30,
                        width: "90%",
                        paddingLeft: 10,
                        marginBottom: 20,
                        borderColor: "#5d575e"
                    }}
                />
                <br />
                <Button size="small" variant="contained" color="primary" onClick={onSubmit} style={{ float: "left" }}>Submit</Button>
                <Button size="small" variant="contained" color="primary" onClick={onClose()} style={{ float: "right" }}>Close</Button>
            </div>    
        </div>
    );
  }

export default ApplicantRequirementDetails;