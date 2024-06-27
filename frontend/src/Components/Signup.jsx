import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import toast from "react-hot-toast";
import styles from "../CSS/Signup.module.css"; 
import loginblob from "../Images/loginblob.png"; 
import loginblobright from "../Images/loginblobright.png"; 
import manAndDog from "../Images/manwithdog.png";

const Signup = () => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [usernameError, setUsernameError] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const navigate = useNavigate();

  const validateForm = () => {
    let valid = true;

    // Username validation
    if (!username) {
      setUsernameError("*Username required");
      valid = false;
    } else if (username.length < 2) {
      setUsernameError("*Username must be at least 2 characters");
      valid = false;
    } else {
      setUsernameError("");
    }

    // Email validation
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!email) {
      setEmailError("*Email required");
      valid = false;
    } else if (!emailRegex.test(email)) {
      setEmailError("*Invalid email format");
      valid = false;
    } else {
      setEmailError("");
    }

    // Password validation
    const passwordRegex = /^(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}$/;
    if (!password) {
      setPasswordError("*Password required");
      valid = false;
    } else if (!passwordRegex.test(password)) {
      setPasswordError("*Password must be at least 8 characters long and include at least one uppercase letter and one number");
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
            toast.success('Welcome back!');
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
      <img src={manAndDog} alt="" className={styles["mananddog"]} />
      <img src={loginblob} alt="Login Blob" className={styles["login-blob"]} />
      <img src={loginblobright} alt="Login Blob Right" className={styles["right-blob"]} />
      <h3 className={styles["signup-title"]}>Sign Up</h3>
      <h1 className={styles["welcome_safepaws"]}>Welcome to SafePaws!</h1>
      <p className={styles["signup-line"]}>Track your pet's allergies with ease</p>
      <div className={styles["form-group"]}>
        <form className="addUserForm" onSubmit={handleSubmit}>
          <div className={styles["inputGroup"]}>
            <label htmlFor="username">Username:</label>
            <input
              className={styles["username-signup-form"]}
              type="text"
              id="username"
              name="username"
              autoComplete="off"
              placeholder="Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
            {usernameError && <span className={styles.error}>{usernameError}</span>}
            <br></br>
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
