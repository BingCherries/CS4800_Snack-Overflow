import React from "react";
import AboutBackground from "../Images/about-background.png";


export default function About() {
  return (
    <div className="about-section-container">
      <div className="about-background-image-container">
        <img src={AboutBackground} alt="" />
      </div>
      <h1 className="about-title">About Us</h1>
   
        <p className="about-paragraphs">
          Welcome to SafePaws! We are dedicated to helping pet owners identify and manage their pets' food allergies. 
          Our platform allows you to log ingredients, track allergic reactions, and pinpoint common allergens to keep your furry 
          friends healthy and happy. With SafePaws, you can ensure your pet’s well-being with ease and confidence. Join us in creating a safer, allergy-free environment for your beloved pets.
        </p>

    </div>
  );
};


