import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const navigate = useNavigate();

  const validateForm = () => {
    let valid = true;
    if (!email) {
      setEmailError("*Email required");
      valid = false;
    } else {
      setEmailError("");
    }

    if (!password) {
      setPasswordError("*Password required");
      valid = false;
    } else {
      setPasswordError("");
    }

    return valid;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validateForm()) {
      // Redirect to dashboard
      navigate("/dashboard");
    }
  };

  return (
    <div className="loginform">
      <h3 className="login-title">Login</h3>
      <h1 className="welcome-login">Welcome back!</h1>
      <div className="form-group-login">
        <form className="addUserForm" onSubmit={handleSubmit}>
          <div className="inputGrouplogin">

            <label htmlFor="email">Email:</label>
            <input
              className="email-login-form"
              type="email"
              id="email"
              name="email"
              autoComplete="off"
              placeholder="Email-Address"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            {emailError && <span className="error">{emailError}</span>}
            
            <label htmlFor="Password">Password:</label>
            <input
              className="password-login-form"
              type="password"
              id="password"
              name="password"
              autoComplete="off"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {passwordError && <span className="error">{passwordError}</span>}

            <div className="no-account">
              <p>
                Don't have an Account?
                <Link to="/signup" type="submit" className="btn btn-success">
                  Sign Up
                </Link>
              </p>
            </div>

            <div className="btn-location">
              <button className="login-btn" type="submit">
                Login
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
