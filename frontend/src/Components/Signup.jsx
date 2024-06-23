import React from "react";
import { Link } from "react-router-dom";

const Signup = () => {
  return (
    <div className="signupform">
      <h3 className="signup-title">Sign Up</h3>
      <h1 className="welcome_safepaws">Welcome to SafePaws!</h1>
      <p className="signup-line"> Track your pet's allergies with ease</p>
      <div className="form-group">
      <form className="addUserForm">
        <div className="inputGroup">
          <label htmlFor="email">Email:</label>
          <input
            className="email-signup-form"
            type="email"
            id="email"
            name="email"
            autoComplete="off"
            placeholder="Email-Address"
          />
          <label htmlFor="Password">Password:</label>
          <input
            className="password-signup-form"
            type="password"
            id="password"
            name="password"
            autoComplete="off"
            placeholder="Password"
          />

        <div className="have-account">
        <p>Already Have an Account?
        <Link to="/login" type="submit" class="btn btn-success">
          Login
        </Link>
         </p>
        </div>

          <div className="btn-location">
          <button class="login-btn" type="submit">Sign Up</button>
          </div>
        </div>
      </form>
    
      </div>
    </div>
  );
};
export default Signup;