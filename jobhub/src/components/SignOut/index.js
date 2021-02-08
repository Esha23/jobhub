import React from 'react';
 
import { withFirebase } from '../Firebase';

import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import IconButton from "@material-ui/core/IconButton";
 
const SignOutButton = ({ firebase }) => (
    
  <IconButton
    edge="end"
    aria-label="sign out"
    aria-haspopup="false"
    onClick={firebase.doSignOut}
    color="inherit"
  >
    <ExitToAppIcon />
  </IconButton>
  
);
 
export default withFirebase(SignOutButton);