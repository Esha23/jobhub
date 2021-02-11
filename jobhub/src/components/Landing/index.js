import React from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";
import { useHistory } from "react-router-dom";
import SignInPage from '../SignIn';

function LandingPage () {
  let history = useHistory();

  function handleSigninRoute () {
    history.push('/signin');
  };

  return(
    <Router>
      <button onClick ={handleSigninRoute}>GET STARTED</button>
        <Route path="/signin" component={SignInPage} />
    </Router>
  );

};
 
export default LandingPage;