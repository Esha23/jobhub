import React, { useState, useEffect }from 'react';
import { withAuthorization } from '../Session';
import ToolbarComponent from "../Toolbar/index";
import Modal from '@material-ui/core/Modal';
import { Button } from '@material-ui/core';
import ApplicantRequirementDetails from '../ApplicantRequiremtDetails';
import * as ROUTES from '../../constants/routes';
import { httpClient } from '../../utils/HTTPBaseClient';
import { getCurrentUser } from '../../redux/selectors/userSelectors';
import {connect, useDispatch, useSelector} from 'react-redux';
import JobTile from '../JobTile';

function RecruiterHome () {

  const userObject = useSelector(getCurrentUser);
  // var recruiterDataJobList ={};

  const [openModalPostJob, setOpenModalPostJob] = React.useState(false);
  const [recruiterDataJobList, setrecruiterDataJobList] = React.useState([]);

  const handleOpenModalPostJob = () => {
    setOpenModalPostJob(true);
  };
  const handleCloseModalPostJob = () => {
    setOpenModalPostJob(false);
  };

  useEffect(async() => {
    console.log(userObject);
    let response = await httpClient.getData(ROUTES.GET_RECRUITER_DATA+userObject.id);
    // recruiterDataJobList = response;
    setrecruiterDataJobList(response);
    console.log("get rec res :",response)
    console.log("rec data:",recruiterDataJobList)
    Object.values(recruiterDataJobList).map(val => {
      console.log(val)
    })
  },[]);

  return(
    <div>
      <div>
        <div className="App" style={{position:"fixed",top:0,left:0,width:"100%",zIndex:1}}>
          <ToolbarComponent />
        </div>
        <div style={{ marginTop:"6%"}}>
          <h1>Recruiter Home</h1>
          <hr />
          <Button size="small" variant="contained" color="primary" onClick={handleOpenModalPostJob}>POST JOB</Button>
        </div>
      </div>
      <div>
        {(recruiterDataJobList.length == 0) ?
          <p style={{ aligin: "center" }}>No Jobs Posted... Click on Post Job to post!</p> : Object.values(recruiterDataJobList).map((RecruiterData, index) => (
            <JobTile RecruiterData={RecruiterData} />
          ))
        }
      </div>


      <Modal
          open={openModalPostJob}
          onClose={handleCloseModalPostJob}
          aria-labelledby="simple-modal-title"
          aria-describedby="simple-modal-description" >
            <div style={{columns: 2, backgroundColor: "white", margin:"10% 20% 10% 20%", padding:"2% 2% 2% 2%"}}>
              <ApplicantRequirementDetails onClose={() => handleCloseModalPostJob} />
            </div>
      </Modal>
    </div>
  );
}

const condition = authUser => !!authUser;

export default withAuthorization(condition)(RecruiterHome);
