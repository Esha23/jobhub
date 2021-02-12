import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import Chip from '@material-ui/core/Chip';
import LensIcon from '@material-ui/icons/Lens';
import Fab from '@material-ui/core/Fab';
import EditIcon from '@material-ui/icons/Edit';
import TextEllipsis from 'react-text-ellipsis';
import {connect, useDispatch, useSelector} from 'react-redux';
import { getCurrentUser } from '../../redux/selectors/userSelectors';
import { httpClient } from '../../utils/HTTPBaseClient';
import * as ROUTES from '../../constants/routes';

const useStyles = makeStyles({
  root: {
    minWidth: "70%",
    backgroundColor:"#FFFFFF",
    borderRadius:15,
    borderColor:"grey",
    borderWidth:1,
    boxShadow: "3px 3px 5px #9E9E9E",
    margin:20
    
  },
  title: {
    marginTop:10
  },
  paper: {
    position: 'absolute',
    width: 400,
    backgroundColor: "white",
    border: '2px solid #000',
    padding:(2, 4, 3),
  },
  rootdiv:{
    cursor:"pointer"
  },
  buttons:{
    backgroundColor:"#70a2ea",
    marginLeft:10
  },
  floatRight:{
    float:"right"
  }
});

function mapStateToPropos(state) {
  return {
    users: state.users
  };
}

function JobTile(props) {

  var RecruiterData = {...props.RecruiterData};
  const userObject = useSelector(getCurrentUser)
  const classes = useStyles();

  const closeJob = async() => {
    let response = await httpClient.postData(ROUTES.CLOSE_JOB + RecruiterData.job_id);
    window.location.reload();
  }

  const mailHr = async() => {
    await httpClient.getData(ROUTES.MAIL_HR + RecruiterData.recruiter_id + "/" + userObject.id);
    alert("Details sent!")
  }

  return (
    <div>
      <Card className={classes.root} variant="outlined" >
        <div className={classes.rootdiv} style={{columns: 3, padding: "0.5% 0.5% 0.5% 0.5%" }}>
          <CardContent >
              <Chip label={RecruiterData.job_id} />
              <Chip
                  icon={<LensIcon style={{height:15,
                  color : (RecruiterData.jobStatus === "OPEN") ? 'green': 'red',
                  }}
                  />
                }
                  label={RecruiterData.jobStatus}
                  style={{marginLeft:10}}
              />
              <Typography variant="h6" component="h2" className={classes.title}>
                <label>Location :</label>
               
                {RecruiterData.location}
              </Typography>
              <Typography variant="h6" component="h2" className={classes.title}>
                <label>Minimum Educational Qualification :</label>
         
                {RecruiterData.education}
              </Typography>
              <Typography variant="h6" component="h2" className={classes.title}>
                <label>Minimum Experience Required :</label>
              
                {RecruiterData.experience}
              </Typography>
              <Typography variant="h6" component="h2" className={classes.title}>
                <label>Preferred :</label>
           
                {RecruiterData.gender}
              </Typography>
              <Typography variant="h6" component="h2" className={classes.title}>
                <label>HR Contact :</label>
             
                {RecruiterData.contact}
              </Typography>
              <Typography variant="h6" component="h2" className={classes.title}>
                <label>Approx Salary :</label>
                INR
                {RecruiterData.salary}
              </Typography>
              <Typography variant="h6" component="h2" className={classes.title}>
                <label>Job Title :</label>
              
                {RecruiterData.title}
              </Typography>
              <label>
                  <TextEllipsis 
                      lines={2} 
                      tag={'p'} 
                      ellipsisChars={'...'} 
                      tagClass={'className'} 
                      debounceTimeoutOnResize={200} 
                      useJsOnly={true} 
                      style={{cursor:"pointer"}}
                      onResult={(result) => { 
                          if (result === TextEllipsis.RESULT.TRUNCATED)
                              console.log('text get truncated');
                          else 
                              console.log('text does not get truncated');
                          }}>
                      <label>Job Description :</label>
                     
                     {RecruiterData.description}
                  </TextEllipsis>
              </label>
              {(userObject.type === 'Applicant') ?
              <>
                <Button size="small" variant="contained" color="primary" onClick={mailHr} style={{float:"right"}}>MAIL HR</Button>
              </>
              : <>
                <Button size="small" variant="contained" color="primary" onClick={closeJob} style={{float:"right"}}>CLOSE JOB</Button>
              </>}
          </CardContent>
        </div>
      </Card>
    </div>
  );
};
 
export default connect(mapStateToPropos)(JobTile);