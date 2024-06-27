import React from 'react';
import styles from '../CSS/ReactionsOverview.module.css';

const ReactionsOverview = ({ onViewClick }) => {
  return (
      <div className={styles.container}>
        {/*<button onClick={onViewClick} className={styles.viewButton}>View</button>*/}
        <button onClick={onViewClick}>View All Reactions</button>
        <ul>
          {reactions.map((reaction, index) => (
              <li key={index}>{reaction}</li>
          ))}
        </ul>
      </div>
  );
};

export default ReactionsOverview;
