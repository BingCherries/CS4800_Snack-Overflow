import React, { useState } from 'react';
import RecordReaction from './RecordReactions';
import IngredientsOverview from './IngredientsOverview';
import ReactionsOverview from './ReactionsOverview';
import AllergenOverview from './AllergenOverview';
import GeneratedReports from './GeneratedReports';
import styles from '../CSS/Dashboard.module.css';

const Dashboard = () => {
  const [selectedModal, setSelectedModal] = useState(null);

  const handleViewClick = (modalType) => {
    setSelectedModal(modalType);
  };

  const closeModal = () => {
    setSelectedModal(null);
  };

  return (
    <div className={styles.dashboardContainer}>
      <h1 className={styles.dashboardTitle}>Dashboard</h1>

      <div className={styles.section}>
        <h2 className={styles.sectionTitle}>Ingredients Overview</h2>
        <IngredientsOverview onViewClick={() => handleViewClick('ingredients')} />
      </div>

      <div className={styles.section}>
        <h2 className={styles.sectionTitle}>Reactions Overview</h2>
        <ReactionsOverview onViewClick={() => handleViewClick('reactions')} />
      </div>

      <div className={styles.section}>
        <h2 className={styles.sectionTitle}>Identified Allergens</h2>
        <AllergenOverview onViewClick={() => handleViewClick('allergens')} />
      </div>

      <div className={styles.section}>
        <h2 className={styles.sectionTitle}>Generated Reports</h2>
        <GeneratedReports />
      </div>

      {selectedModal === 'ingredients' && (
        <Modal onClose={closeModal}>
          <IngredientsOverview />
        </Modal>
      )}

      {selectedModal === 'reactions' && (
        <Modal onClose={closeModal}>
          <ReactionsOverview />
        </Modal>
      )}

      {selectedModal === 'allergens' && (
        <Modal onClose={closeModal}>
          <AllergenOverview />
        </Modal>
      )}
    </div>
  );
};

const Modal = ({ onClose, children }) => (
  <div className={styles.modal}>
    <div className={styles.modalContent}>
      <button onClick={onClose} className={styles.closeButton}>Close</button>
      {children}
    </div>
  </div>
);

export default Dashboard;
