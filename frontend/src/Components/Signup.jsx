import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import toast, { Toaster } from "react-hot-toast";
import styles from "../CSS/Signup.module.css"; 
import loginblob from "../Images/loginblob.png"; 
import loginblobright from "../Images/loginblobright.png"; 
import manAndDog from "../Images/manwithdog.png";

const Signup = () => {
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
      toast.promise(
        new Promise((resolve) => {
          setTimeout(() => {
            resolve();
          }, 2000);
        }),
        {
          loading: 'Signing in...',
          success: () => {
            navigate("/dashboard");
            return 'Signed in successfully!';
          },
          error: 'Error signing in',
        }
      );
    }
  };

  return (
    <div className={styles.signupform}>
      <Toaster />
      <img src={manAndDog} alt="" className={styles["mananddog"]} />
      <img src={loginblob} alt="Login Blob" className={styles["login-blob"]} />
      <img src={loginblobright} alt="Login Blob Right" className={styles["right-blob"]} />
      <h3 className={styles["signup-title"]}>Sign Up</h3>
      <h1 className={styles["welcome_safepaws"]}>Welcome to SafePaws!</h1>
      <p className={styles["signup-line"]}>Track your pet's allergies with ease</p>
      <div className={styles["form-group"]}>
        <form className="addUserForm" onSubmit={handleSubmit}>
          <div className={styles["inputGroup"]}>
            <label htmlFor="email">Email:</label>
            <input
              className={styles["email-signup-form"]}
              type="email"
              id="email"
              name="email"
              autoComplete="off"
              placeholder="Email-Address"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            {emailError && <span className={styles.error}>{emailError}</span>}
            <br></br>
            <label htmlFor="password">Password:</label>
            <input
              className={styles["password-signup-form"]}
              type="password"
              id="password"
              name="password"
              autoComplete="off"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {passwordError && <span className={styles.error}>{passwordError}</span>}

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
