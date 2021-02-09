import React from 'react';
 
import { withAuthorization } from '../Session';
import ToolbarComponent from "../Toolbar/index";
 
const HomePage = () => (
  <div>
      <div className="App" style={{position:"fixed",top:0,left:0,width:"100%",zIndex:1}}>
        <ToolbarComponent />
      </div>
      <div style={{display:"flex"}}>
        <h1>Applicant Home</h1>
      </div>
    </div>
);
 
const condition = authUser => !!authUser;
 
export default withAuthorization(condition)(HomePage);