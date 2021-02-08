import app from 'firebase/app';
import 'firebase/auth';

const config = {
  apiKey: "AIzaSyCTA6jYU3dlQ1gn5IsaP6s--UeDOlOiiDU",
  authDomain: "jobhub-69e22.firebaseapp.com",
  databaseURL: "https://jobhub-69e22-default-rtdb.firebaseio.com/",
  projectId: "jobhub-69e22",
  storageBucket: "jobhub-69e22.appspot.com",
  messagingSenderId: "263645706992",
};

class Firebase {
  constructor() {
    app.initializeApp(config);

    this.auth = app.auth();
  }

  // *** Auth API ***
 
  doCreateUserWithEmailAndPassword = (email, password) =>
    this.auth.createUserWithEmailAndPassword(email, password);

  doSignInWithEmailAndPassword = (email, password) =>
    this.auth.signInWithEmailAndPassword(email, password);

  doSignOut = () => this.auth.signOut();

  doPasswordReset = email => this.auth.sendPasswordResetEmail(email);
 
  doPasswordUpdate = password =>
    this.auth.currentUser.updatePassword(password);
}

export default Firebase;




// <!-- The core Firebase JS SDK is always required and must be listed first -->
// <script src="https://www.gstatic.com/firebasejs/8.2.6/firebase-app.js"></script>

// <!-- TODO: Add SDKs for Firebase products that you want to use
//      https://firebase.google.com/docs/web/setup#available-libraries -->
// <script src="https://www.gstatic.com/firebasejs/8.2.6/firebase-analytics.js"></script>

// <script>
//   // Your web app's Firebase configuration
//   // For Firebase JS SDK v7.20.0 and later, measurementId is optional
//   var firebaseConfig = {
//     apiKey: "AIzaSyCTA6jYU3dlQ1gn5IsaP6s--UeDOlOiiDU",
//     authDomain: "jobhub-69e22.firebaseapp.com",
//     projectId: "jobhub-69e22",
//     storageBucket: "jobhub-69e22.appspot.com",
//     messagingSenderId: "263645706992",
//     appId: "1:263645706992:web:bbec396ce78651a2922035",
//     measurementId: "G-QS99R8T447"
//   };
//   // Initialize Firebase
//   firebase.initializeApp(firebaseConfig);
//   firebase.analytics();
// </script>