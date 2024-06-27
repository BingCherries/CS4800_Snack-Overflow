import React, { useState, useEffect  } from 'react';
import axios from 'axios';
import RecordReaction from './RecordReactions';
import IngredientsOverview from './IngredientsOverview';
import ReactionsOverview from './ReactionsOverview';
import AllergenOverview from './AllergenOverview';
import GeneratedReports from './GeneratedReports';
import styles from '../CSS/Dashboard.module.css';
import doggo from '../Images/doggroup.png';
import cato from '../Images/cato.png';
import petfood from '../Images/food.png';
import note from '../Images/note.png';
import allergy from '../Images/allergy.png';

const Dashboard = () => {
  const [selectedModal, setSelectedModal] = useState(null);
  const [dashboardData, setDashboardData] = useState({
    foods: [],
    reactions: [],
    commonAllergens: []
  });

  useEffect(() => {
    const fetchDashboardData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/dashboard');
        setDashboardData(response.data);
      } catch (error) {
        console.error('Error fetching dashboard data:', error);
      }
    };

    fetchDashboardData();
  }, []);

  const handleViewClick = (modalType) => {
    setSelectedModal(modalType);
  };

  const closeModal = () => {
    setSelectedModal(null);
  };

  return (
    <div className={styles.dashboardContainer}>
      <h1 className={styles.dashboardTitle}>Dashboard</h1>

      <div className={styles.topSection}>
        <div className={styles.section}>
          <img src={petfood} className={styles.sectionImage} alt="Pet Food" />
          <h2 className={styles.sectionTitle}>Ingredients Overview</h2>
          <button className={styles.viewButton} onClick={() => handleViewClick('ingredients')}>View</button>
        </div>

        <div className={styles.section}>
          <img src={note} className={styles.sectionImage} alt="Note" />
          <h2 className={styles.sectionTitle}>Reactions Overview</h2>
          <button className={styles.viewButton} onClick={() => handleViewClick('reactions')}>View</button>
        </div>

        <div className={styles.section}>
          <img src={allergy} className={styles.sectionImage} alt="Allergy" />
          <h2 className={styles.sectionTitle}>Identified Allergens</h2>
          <button className={styles.viewButton} onClick={() => handleViewClick('allergens')}>View</button>
        </div>
      </div>

      <div className={styles.bottomSection}>
        <div className={styles.generatedReportsSection}>
          <div className={styles.generatedReportsContent}>
            <h2 className={styles.sectionTitle}>Generated Reports</h2>
            <GeneratedReports />
          </div>
          <div className={styles.doggo}>
            <img src={doggo} alt="Dog" />
          </div>
          <div className={styles.cato}>
            <img src={cato} alt="Cat" />
          </div>
        </div>
      </div>

      {selectedModal === 'ingredients' && (
        <Modal onClose={closeModal}>
          {/*<IngredientsOverview />*/}
          <IngredientsOverview foods={dashboardData.foods} />
        </Modal>
      )}

      {selectedModal === 'reactions' && (
        <Modal onClose={closeModal}>
          {/*<ReactionsOverview />*/}
          <ReactionsOverview reactions={dashboardData.reactions} />
        </Modal>
      )}

      {selectedModal === 'allergens' && (
        <Modal onClose={closeModal}>
          {/*<AllergenOverview />*/}
          <AllergenOverview allergens={dashboardData.commonAllergens} />
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
