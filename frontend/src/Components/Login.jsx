import React from "react";
import { Link } from "react-router-dom";

const Login = () => {
  return (
    <div className="loginform">
      <h3 className="login-title">Login</h3>
      <h1 className="welcome-login">Welcome back!</h1>
      <div className="form-group-login">
      <form className="addUserForm">
        <div className="inputGrouplogin">
          <label htmlFor="email">Email:</label>
          <input 
            className="email-login-form"
            type="email"
            id="email"
            name="email"
            autoComplete="off"
            placeholder="Email-Address" 
     
          />
          
          <label htmlFor="Password">Password:</label>
          <input
            className="password-login-form"
            type="password"
            id="password"
            name="password"
            autoComplete="off"
            placeholder="Password"
          />

        <div className="no-account">
        <p>Don't have Account?
        <Link to="/signup" type="submit" class="btn btn-success">
          Sign Up
        </Link>
         </p>
        </div>

          <div className="btn-location">
          <button class="login-btn" type="submit">Login</button>
          </div>
        </div>
      </form>
    
      </div>
    </div>
  );
};

export default Login;