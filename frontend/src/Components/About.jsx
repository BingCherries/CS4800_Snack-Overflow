import React from "react";
import styles from '../CSS/about.module.css';
import catAbout from "../Images/catabout.png";
import treats from "../Images/treats.png";
import hearts from "../Images/heart.png";

export default function About() {
  return (
    <div className={styles["about-section-container"]}>
      <img src={treats} className={styles["treats-image"]} alt="Treats" />
      <img src={hearts} className={styles["hearts-image"]} alt="Hearts" />
      <div className={styles["text-column"]}>
        <h1 className={styles["about-title"]}>About Us</h1>
        <p className={styles["about-paragraph"]}>
          Welcome to SafePaws! We are dedicated to helping pet owners identify and manage their pets' food allergies. 
          Our platform allows you to log ingredients, track allergic reactions, and pinpoint common allergens to keep your furry 
          friends healthy and happy. With SafePaws, you can ensure your pet’s well-being with ease and confidence. Join us in creating a safer, allergy-free environment for your beloved pets.
        </p>
      </div>
      <div className={styles["image-column"]}>
        <img src={catAbout} className={styles["about-image"]} alt="Cat" />
      </div>
    </div>
  );
}
