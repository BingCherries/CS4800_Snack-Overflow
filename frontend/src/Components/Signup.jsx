import React from "react";
import { Link } from "react-router-dom";
import styles from "../CSS/Signup.module.css"; // Import CSS module

const Signup = () => {
  return (
    <div className={styles.signupform}>
      <h3 className={styles["signup-title"]}>Sign Up</h3>
      <h1 className={styles["welcome_safepaws"]}>Welcome to SafePaws!</h1>
      <p className={styles["signup-line"]}>Track your pet's allergies with ease</p>
      <div className={styles["form-group"]}>
        <form className="addUserForm">
          <div className={styles["inputGroup"]}>
            <label htmlFor="email">Email:</label><br></br>
            <input
              className={styles["email-signup-form"]}
              type="email"
              id="email"
              name="email"
              autoComplete="off"
              placeholder="Email-Address"
            />
            <br></br>
          <br></br>
            <label htmlFor="password">Password:</label><br></br>
            <input
              className={styles["password-signup-form"]}
              type="password"
              id="password"
              name="password"
              autoComplete="off"
              placeholder="Password"
            />

            <div className={styles["have-account"]}>
              <p>
                Already Have an Account?
                <Link to="/login" className="btn btn-success">
                  Login
                </Link>
              </p>
            </div>

            <div className={styles["btn-location"]}>
              <button className={styles["signup-btn"]} type="submit">
                Sign Up
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Signup;
