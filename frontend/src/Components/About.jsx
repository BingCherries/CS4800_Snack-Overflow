import React from "react";
import styles from '../CSS/about.module.css';
import catTowern from  "../Images/cat-tower.png";
import yarn from  "../Images/yarn.png";
import treats from  "../Images/treats.png";
import header from  "../Images/header.png";
export default function About() {
  return (
    <div className={styles["about-section-container"]}>
      <h1 className={styles["about-title"]}>About Us</h1>
   
        <p className={styles["about-paragraph"]}>
          Welcome to SafePaws! We are dedicated to helping pet owners identify and manage their pets' food allergies. 
          Our platform allows you to log ingredients, track allergic reactions, and pinpoint common allergens to keep your furry 
          friends healthy and happy. With SafePaws, you can ensure your pet’s well-being with ease and confidence. Join us in creating a safer, allergy-free environment for your beloved pets.
        </p>
        <div className={styles["pet-tower"]}>
          <img src={catTowern} alt="" />
        </div>
        <div className={styles["yarn"]}>
          <img src={yarn} alt="" />
        </div>
        <div className={styles["treats"]}>
          <img src={treats} alt="" />
        </div>
        <div className={styles["header"]}>
          <img src={header} alt="" />
        </div>
    </div>
  );
};


