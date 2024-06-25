import React from 'react';
import styles from '../CSS/IngredientsOverview.module.css';

const IngredientsOverview = ({ onViewClick }) => {
  return (
    <div className={styles.container}>
      <p>Summary of ingredients...</p>
      <button onClick={onViewClick} className={styles.viewButton}>View</button>
    </div>
  );
};

export default IngredientsOverview;
