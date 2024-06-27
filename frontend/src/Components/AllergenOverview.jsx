import React from 'react';
import styles from '../CSS/AllergenOverview.module.css';

const AllergenOverview = ({ commonAllergens = [], onViewClick }) => {
  return (
      <div className={styles.container}>
          {/*
          <button onClick={onViewClick} className={styles.viewButton}>View</button>

          <button onClick={onViewClick}>View All Allergens</button>
          <ul>
              {allergens.map((allergen, index) => (
                  <li key={index}>{allergen}</li>
              ))}
          </ul>
          */}
          <div className={styles.commonAllergensList}>
              <h3>Common Allergens:</h3>
              {commonAllergens.map((allergen, index) => (
                  <div key={index} className={styles.commonAllergensItem}>{allergen}</div>
              ))}
          </div>
      </div>
  );
};

export default AllergenOverview;
