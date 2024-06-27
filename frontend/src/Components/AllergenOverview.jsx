import React from 'react';
import styles from '../CSS/AllergenOverview.module.css';

const AllergenOverview = ({ commonAllergens = [], onViewClick }) => {
  return (
      <div className={styles.container}>
          {/*<button onClick={onViewClick} className={styles.viewButton}>View</button>*/}
          <div className={styles.commonAllergensList}>
              {commonAllergens.map((allergen, index) => (
                  <div key={index} className={styles.commonAllergensItem}>{allergen}</div>
              ))}
          </div>

      </div>
  );
};

export default AllergenOverview;
