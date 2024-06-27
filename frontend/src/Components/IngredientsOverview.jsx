import React from 'react';
import styles from '../CSS/IngredientsOverview.module.css';

{/*const IngredientsOverview = ({ onViewClick }) => {*/}
const IngredientsOverview = ({ foods = [], onViewClick }) => {
  return (
      <div className={styles.container}>
          {/*<button onClick={onViewClick} className={styles.viewButton}>View</button>*/}
          <div className={styles.foodList}>
              {foods.map((food, index) => (
                  <div key={index} className={styles.foodItem}>{food}</div>
              ))}
          </div>
      </div>
  );
};

export default IngredientsOverview;
