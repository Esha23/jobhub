import React, { useState }from 'react';
import { withAuthorization } from '../Session';
import ToolbarComponent from "../Toolbar/index";

function RecruiterHome () {

  return (
    <div>
      <div className="App" style={{position:"fixed",top:0,left:0,width:"100%",zIndex:1}}>
        <ToolbarComponent />
      </div>
      <div style={{display:"flex"}}>
        <h1>Recruiter Home</h1>
      </div>
    </div>
  );
}

const condition = authUser => !!authUser;

export default withAuthorization(condition)(RecruiterHome);
