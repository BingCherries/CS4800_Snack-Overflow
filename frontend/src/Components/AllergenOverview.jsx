import React from 'react';
import styles from '../CSS/AllergenOverview.module.css';

const AllergenOverview = ({ onViewClick }) => {
  return (
    <div className={styles.container}>
      <p>Summary of identified allergens...</p>
      <button onClick={onViewClick} className={styles.viewButton}>View</button>
    </div>
  );
};

export default AllergenOverview;
