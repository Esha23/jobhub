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
 
function HomePage () {

  const userObject = useSelector(getCurrentUser);
  var applicantData ={};

  const [openModalApplyForJob, setModalOpenApplyForJob] = React.useState(false);

  const handleOpenModalApplyForJob = () => {
    setModalOpenApplyForJob(true);
  };
  const handleCloseModalApplyForJob = () => {
    setModalOpenApplyForJob(false);
  };

  useEffect(async() => {
    console.log(userObject);
    let response = await httpClient.getData(ROUTES.GET_APPLICANT_DATA+userObject.id);
    applicantData = response;
    Object.values(applicantData).map(val => {
      console.log(val.gender)
    })
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
        </div>
              
          {Object.values(applicantData).map(val => (
            <>
              <label>Contact : {val.contact}</label>
              <label>Gender : {val.gender}</label>
              <label>Experience : {val.experience}</label>
              <label>Education : {val.education}</label>
              <label>Location : {val.location}</label>
            </>
          ))}
        
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