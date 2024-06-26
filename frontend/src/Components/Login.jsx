import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import styles from "../CSS/login.module.css"; // Import CSS module

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
    <div className={styles.loginform}>
      <h3 className={styles["login-title"]}>Login</h3>
      <h1 className={styles["welcome-login"]}>Welcome back!</h1>
      <div className={styles["form-group-login"]}>
        <form className="addUserForm" onSubmit={handleSubmit}>
          <div className={styles["inputGrouplogin"]}>
            <label htmlFor="email">Email:</label>
            <input
              className={styles["email-login-form"]}
              type="email"
              id="email"
              name="email"
              autoComplete="off"
              placeholder="Email-Address"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            {emailError && <span className={styles.error}>{emailError}</span>}

            <label htmlFor="password">Password:</label>
            <input
              className={styles["password-login-form"]}
              type="password"
              id="password"
              name="password"
              autoComplete="off"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {passwordError && (
              <span className={styles.error}>{passwordError}</span>
            )}

            <div className={styles["no-account"]}>
              <p>
                Don't have an Account?
                <Link to="/signup" className="btn btn-success">
                  Sign Up
                </Link>
              </p>
            </div>

            <div className={styles["btn-location"]}>
              <button className={styles["login-btn"]} type="submit">
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
