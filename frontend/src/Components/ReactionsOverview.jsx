import React from 'react';
import styles from '../CSS/ReactionsOverview.module.css';

const ReactionsOverview = ({ onViewClick }) => {
  return (
    <div className={styles.container}>
      <button onClick={onViewClick} className={styles.viewButton}>View</button>
    </div>
  );
};

export default ReactionsOverview;
