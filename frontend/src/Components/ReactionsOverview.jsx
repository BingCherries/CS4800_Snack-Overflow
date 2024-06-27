import React from 'react';
import styles from '../CSS/ReactionsOverview.module.css';

const ReactionsOverview = ({ foods = [], reactions = [], commonAllergens = [], onViewClick }) => {
  return (
      <div className={styles.container}>
        {/*<button onClick={onViewClick} className={styles.viewButton}>View</button>*/}
          <div className={styles.listContainer}>
              <div className={styles.foods}>
                  <h3>Foods:</h3>
                  <ul>
                      {foods.map((food, index) => (
                          <li key={index}>{food}</li>
                      ))}
                  </ul>
              </div>
              <div className={styles.reactions}>
                  <h3>Reactions:</h3>
                  <ul>
                      {reactions.map((reaction, index) => (
                          <li key={index}>{reaction}</li>
                      ))}
                  </ul>
              </div>
              <div className={styles.commonAllergens}>
                  <h3>Common Allergens:</h3>
                  <ul>
                      {commonAllergens.map((allergen, index) => (
                          <li key={index}>{allergen}</li>
                      ))}
                  </ul>
              </div>
          </div>
      </div>
  );
};

export default ReactionsOverview;
