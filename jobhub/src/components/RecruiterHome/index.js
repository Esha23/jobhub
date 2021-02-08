import React, { useState }from 'react';
import { withAuthorization } from '../Session';

function RecruiterHome () {

    return (
        <div>
      <div>
        <div>
          <h1>recruiter home</h1>
        </div>
      </div>
    </div>
    );
}

const condition = authUser => !!authUser;

export default withAuthorization(condition)(RecruiterHome);
