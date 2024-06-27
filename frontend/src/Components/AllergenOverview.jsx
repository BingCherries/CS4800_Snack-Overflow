import React from 'react';
import styles from '../CSS/AllergenOverview.module.css';

const AllergenOverview = ({ onViewClick }) => {
  return (
      <div className={styles.container}>
          <button onClick={onViewClick} className={styles.viewButton}>View</button>
          {/*
          <button onClick={onViewClick}>View All Allergens</button>
          <ul>
              {allergens.map((allergen, index) => (
                  <li key={index}>{allergen}</li>
              ))}
          </ul>
          */}
      </div>
  );
};

export default AllergenOverview;
