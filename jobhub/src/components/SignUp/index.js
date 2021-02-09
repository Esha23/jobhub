import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { compose } from 'recompose';
 
import { withFirebase } from '../Firebase';
import firebase from "firebase/app";
import "firebase/auth";
import axios from 'axios';
import * as ROUTES from '../../constants/routes';

 
const SignUpPage = () => (
  <div>
    <h1>SignUp</h1>
    <SignUpForm />
  </div>
);

const INITIAL_STATE = {
  username: '',
  email: '',
  passwordOne: '',
  passwordTwo: '',
  error: null,
};

var userDetails = {
  id: '',
  name: '',
  email: '',
  type: '',
  mailDetails:{
    subject: null,
    body: null
  }
};
 
class SignUpFormBase extends Component {
  constructor(props) {
    super(props);

    this.state = { ...INITIAL_STATE };
    this.handleChange = this.handleChange.bind(this);
  }
 
  onSubmit = event => {
    const { username, email, passwordOne, type } = this.state;
    
    this.props.firebase
      .doCreateUserWithEmailAndPassword(email, passwordOne)
      .then(authUser => {
        this.setState({ ...INITIAL_STATE });
        if(type === "Recruiter") {
          this.props.history.push(ROUTES.RECRUITER_HOME);
          console.log("route to recruiter home");
        }
        else if(type === "Applicant") {
          this.props.history.push(ROUTES.APPLICANT_HOME);
          console.log("route to applicant home")
        }
      })
      .catch(error => {
        this.setState({ error });
      });

      firebase.auth().onAuthStateChanged((user) => {
        if (user) {
          userDetails.id = user.uid;
          userDetails.email = user.email;
          userDetails.type = this.state.type;
          userDetails.name = username;
          var data = JSON.stringify(userDetails);
          console.log("userdetails:",data);
          axios.post('http://localhost:8040/user/addUser', data, {
            "headers":{
              Action: "apllication/json",
              "content-type": "application/json",
              "Access-Control-Allow-Origin": "*",
            },
          })
          .then(function(response) {
            console.log(response);
          })    
          .catch(function(error) {
            console.log(error);
          })
        } 
        else {
          console.log("User does not exist");
        }
      });
 
      event.preventDefault();
 
  }

  handleChange(event) {
    this.setState({type: event.target.value});
  }
 
  onChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };
 
  render() {
    const {
      username,
      email,
      passwordOne,
      passwordTwo,
      error,
    } = this.state;

    const isInvalid =
      passwordOne !== passwordTwo ||
      passwordOne === '' ||
      email === '' ||
      username === '';

    return (
      <form onSubmit={this.onSubmit}>
        <input
          name="username"
          value={username}
          onChange={this.onChange}
          type="text"
          placeholder="Full Name"
          style={{
            height:30,
            width:250,
            paddingLeft:10,
            marginBottom:10,
            borderColor:"#5d575e"

          }}
        /><br />
        <input
          name="email"
          value={email}
          onChange={this.onChange}
          type="text"
          placeholder="Email Address"
          style={{
            height:30,
            width:250,
            paddingLeft:10,
            marginBottom:10,
            borderColor:"#5d575e"

          }}
        /><br />
        <input
          name="passwordOne"
          value={passwordOne}
          onChange={this.onChange}
          type="password"
          placeholder="Password"
          style={{
            height:30,
            width:250,
            paddingLeft:10,
            marginBottom:10,
            borderColor:"#5d575e"

          }}
        /><br />
        <input
          name="passwordTwo"
          value={passwordTwo}
          onChange={this.onChange}
          type="password"
          placeholder="Confirm Password"
          style={{
            height:30,
            width:250,
            paddingLeft:10,
            marginBottom:10,
            borderColor:"#5d575e"

          }}
        /><br />
        <select style={{
            height:30,
            width:267,
            paddingLeft:10,
            marginBottom:10,
            color: "grey"
          }} 
          defaultValue=""
          onChange={this.handleChange}
          >
          <option selected value="select UserType">Select User Type</option>
          <option value="Applicant" style={{color: "black"}}>Applicant</option>
          <option value="Recruiter" style={{color: "black"}}>Recruiter</option>
        </select>
        <br />
        <button disabled={isInvalid} type="submit">
          Sign Up
        </button>
 
        {error && <p>{error.message}</p>}
      </form>
    );
  }
}
 
const SignUpLink = () => (
  <p>
    Don't have an account? <Link to={ROUTES.SIGN_UP}>Sign Up</Link>
  </p>
);

const SignUpForm = compose(
  withRouter,
  withFirebase,
)(SignUpFormBase);

export default SignUpPage;

export { SignUpForm, SignUpLink };