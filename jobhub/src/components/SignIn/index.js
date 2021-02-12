import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import { compose } from 'recompose';
 
import { SignUpLink } from '../SignUp';
import { PasswordForgetLink } from '../PasswordForget';
import { withFirebase } from '../Firebase';
import * as ROUTES from '../../constants/routes';
import { httpClient } from '../../utils/HTTPBaseClient';
import { fetchUserSuccess } from '../../redux/slices/user';
import { connect, useDispatch } from 'react-redux';
import firebase from "firebase/app";
import "firebase/auth";

import Button from '@material-ui/core/Button';
 
const SignInPage = (props) => (
  <div style={{ textAlign: "center" }}>
    <h1>SignIn</h1>
    <SignInForm fetchUserSuccess={props.fetchUserSuccess}/>
    <PasswordForgetLink />
    <SignUpLink />
  </div>
);
 
const INITIAL_STATE = {
  email: '',
  password: '',
  error: null,
};
var userDetails = {
  id: '',
  email: ''
};

function mapStateToPropos(state) {
  console.log(state);
  return {
    users: state.users
  };  
}
 
class SignInFormBase extends Component {
  constructor(props) {
    super(props);
 
    this.state = { ...INITIAL_STATE };
  }
 
  componentDidMount() {
    console.log("hey there! signin cdm")
    firebase.auth().onAuthStateChanged(async (user) => {
      let that = this;
      if (user) {
        userDetails.id = user.uid;
        let response = await httpClient.getData(ROUTES.GET_USER_TYPE + user.uid);
        that.props.fetchUserSuccess(response)
        if (response.type === 'Applicant')
          that.props.history.push(ROUTES.APPLICANT_HOME);
        else if (response.type === 'Recruiter')
          that.props.history.push(ROUTES.RECRUITER_HOME);
      }
      else {
        console.log("User does not exist");
      }
    });
  };

  onSubmit = event => {
    const { email, password } = this.state;
 
    this.props.firebase
      .doSignInWithEmailAndPassword(email, password)
      .then(() => {
        this.setState({ ...INITIAL_STATE });
      })
      .catch(error => {
        this.setState({ error });
      });
 
    event.preventDefault();
  };
 
  onChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };
 
  render() {
    const { email, password, error } = this.state;
 
    const isInvalid = password === '' || email === '';
 
    return (
      <form onSubmit={this.onSubmit}>
        <input
          name="email"
          value={email}
          onChange={this.onChange}
          type="text"
          placeholder="Email Address"
          style={{
            height: 30,
            width: 250,
            paddingLeft: 10,
            marginBottom: 10,
            borderColor: "#5d575e"
          }}
        /><br />
        <input
          name="password"
          value={password}
          onChange={this.onChange}
          type="password"
          placeholder="Password"
          style={{
            height: 30,
            width: 250,
            paddingLeft: 10,
            marginBottom: 10,
            borderColor: "#5d575e"
          }}
        /><br />
        <Button size="medium" variant="contained" color="primary" disabled={isInvalid} type="submit"  >Sign In</Button>
        {error && <p>{error.message}</p>}
      </form>
    );
  }
}
 
const SignInForm = compose(
  withRouter,
  withFirebase,
)(SignInFormBase);

const dispatchToProps = {
  fetchUserSuccess
}
 
export default connect(mapStateToPropos, dispatchToProps)(SignInPage);
 
export { SignInForm };