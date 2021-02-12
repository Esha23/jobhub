import { Button } from '@material-ui/core';
import React,{ useEffect } from 'react';
import CategoryList from '../CategoryList';
import * as ROUTES from '../../constants/routes';
import { httpClient } from '../../utils/HTTPBaseClient';
import { getCurrentUser } from '../../redux/selectors/userSelectors';
import {connect, useDispatch, useSelector} from 'react-redux';
 
import { withAuthorization } from '../Session';
import ToolbarComponent from "../Toolbar/index";
import Modal from '@material-ui/core/Modal';
import JobTile from '../JobTile';
 
function HomePage () {

  const userObject = useSelector(getCurrentUser);
  // var applicantData =[];
  // var recruiterDataList = [];
  // var recData = {};

  const [openModalApplyForJob, setModalOpenApplyForJob] = React.useState(false);
  const [recruiterDataList, setrecruiterDataList] = React.useState([]);
  const [applicantData, setapplicantData] = React.useState([]);

  const handleOpenModalApplyForJob = () => {
    setModalOpenApplyForJob(true);
  };
  const handleCloseModalApplyForJob = () => {
    setModalOpenApplyForJob(false);
  };

  useEffect(async() => {
    console.log(userObject);
    let response = await httpClient.getData(ROUTES.GET_APPLICANT_DATA+userObject.id);
    // applicantData = response;
    setapplicantData(response);
    Object.values(applicantData).map(val => {
      console.log(val)
    })
    let response1 = await httpClient.getData(ROUTES.GET_JOB_RECOMMENDATION_LIST+userObject.id);
    console.log(response1);
    // recruiterDataList = response1;
    setrecruiterDataList(response1);
    // recData = recruiterDataList[0];
    console.log("rec data res1:", recruiterDataList);
    console.log("rec adat list length: ",recruiterDataList.length);
  },[]);

  return(
    <div>
      <div>
        <div className="App" style={{position:"fixed",top:0,left:0,width:"100%",zIndex:1}}>
          <ToolbarComponent />
        </div>
        <div style={{ marginTop:"6%"}}>
          <h1>Applicant Home</h1>
          <hr />
          <Button size="small" variant="contained" color="primary" onClick={handleOpenModalApplyForJob}>APPLY FOR JOB</Button>
          <Button size="small" variant="contained" color="primary"  style={{marginLeft:"1%"}}>Sort By Salary</Button>
          <Button size="small" variant="contained" color="primary" style={{marginLeft:"1%"}} >Sort By Location</Button>
        </div>
          {Object.values(applicantData).map((val,i) => (
            <>
              <br />
              <label><strong>Your Profile :</strong></label>
              <br />
              <br />
              <label>Contact : {val.contact}</label>
              <br />
              <label>Gender : {val.gender}</label>
              <br />
              <label>Experience : {val.experience}</label>
              <br />
              <label>Education : {val.education}</label>
              <br />
              <label>Location : {val.location}</label>
            </>
          ))}
          <hr />
        
      </div>
      <div>
        {(recruiterDataList.length == 0) ?
          <p style={{ aligin: "center" }}>No Matching Jobs.....Apply to job to get matches!</p> : recruiterDataList.map((RecruiterData, index) => (
            <JobTile RecruiterData={RecruiterData} />
          ))
        }
      </div>
      

      <Modal
          open={openModalApplyForJob}
          onClose={handleCloseModalApplyForJob}
          aria-labelledby="simple-modal-title"
          aria-describedby="simple-modal-description" >
            <div style={{columns: 2, backgroundColor: "white", margin:"10% 20% 10% 20%", padding:"2% 2% 2% 2%"}}>
              <CategoryList onClose={() => handleCloseModalApplyForJob}/>
            </div>
      </Modal>
    </div>
  );
};
 
const condition = authUser => !!authUser;
 
export default withAuthorization(condition)(HomePage);