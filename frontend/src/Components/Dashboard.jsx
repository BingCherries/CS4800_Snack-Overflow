import React, { useState, useEffect  } from 'react';
import axios from 'axios';
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
import dashTop from '../Images/dashTop.png';
import CloseIcon from '@mui/icons-material/Close';

const Dashboard = () => {
  const [selectedModal, setSelectedModal] = useState(null);
  const [dashboardData, setDashboardData] = useState({
    foods: [],
    reactions: [],
    commonAllergens: []
  });
  const [reportData, setReportData] = useState([]);


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

  const handleGenerateReport = async () => {
    try {
      const response = await axios.get('http://localhost:8080/allergyreport');
      console.log('Report Data:', response.data);
      const parsedData = parseReportData(response.data);
      console.log('Parsed Report Data:', parsedData);
      setReportData(parsedData || []);
      setSelectedModal('report');
    } catch (error) {
      console.error('Error generating report:', error);
    }
  };

  const parseReportData = (data) => {
    const regex = /Food:\s*([^,]+),\s*Ingredients:\s*\[([^\]]+)\],\s*Symptom:\s*([^,]+),\s*Severity:\s*(\d+)/g;
    const result = [];
    let match;
    while ((match = regex.exec(data)) !== null) {
      result.push({
        food: {
          name: match[1].trim(),
          ingredients: match[2].split(',').map(ingredient => ingredient.trim())
        },
        symptoms: match[3].trim(),
        severity: parseInt(match[4], 10)
      });
    }
    return result;
  };
  const closeModal = () => {
    setSelectedModal(null);
  };

  return (
    <div className={styles.dashboardContainer}>
      <h1 className={styles.dashboardTitle}>Dashboard</h1>
      <img src={dashTop} alt="Dashboard Top" className={styles.dashTopImage} />
      <div className={styles.topSection}>
        <div className={styles.sectionIngre}>
          <img src={petfood} className={styles.sectionImage} alt="Pet Food" />
          <h2 className={styles.sectionTitle}>Ingredients Overview</h2>
          <button className={styles.viewButton} onClick={() => handleViewClick('ingredients')}>View</button>
        </div>

        <div className={styles.sectionAllergen}>
          <img src={note} className={styles.sectionImage} alt="Note" />
          <h2 className={styles.sectionTitle}>Reactions Overview</h2>
          <button className={styles.viewButton} onClick={() => handleViewClick('reactions')}>View</button>
        </div>

        <div className={styles.sectionMiddle}>
          <img src={allergy} className={styles.sectionImage} alt="Allergy" />
          <h2 className={styles.sectionTitle}>Identified Allergens</h2>
          <button className={styles.viewButton} onClick={() => handleViewClick('allergens')}>View</button>
        </div>
      </div>

      <div className={styles.bottomSection}>
        <div className={styles.generatedReportsSection}>
          <div className={styles.generatedReportsContent}>
            <h2 className={styles.sectionTitle}>Generated Report</h2>
            <button className={styles.viewButton} onClick={handleGenerateReport}>Generate Report</button>
            <GeneratedReports/>
            <h2 className={styles.sectionTitle}>Generated Report</h2>
            <button className={styles.viewButton} onClick={handleGenerateReport}>Generate Report</button>
            <GeneratedReports/>
          </div>
          <div className={styles.doggo}>
            <img src={doggo} alt="Dog"/>
            <img src={doggo} alt="Dog"/>
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
          <ReactionsOverview
              foods={dashboardData.foods}
              reactions={dashboardData.reactions}
              commonAllergens={dashboardData.commonAllergens}
          />
          <ReactionsOverview
              foods={dashboardData.foods}
              reactions={dashboardData.reactions}
              commonAllergens={dashboardData.commonAllergens}
          />
        </Modal>
      )}

      {selectedModal === 'allergens' && (
        <Modal onClose={closeModal}>
          {/*<AllergenOverview />*/}
          <AllergenOverview commonAllergens={dashboardData.commonAllergens} />
          <AllergenOverview commonAllergens={dashboardData.commonAllergens} />
        </Modal>
      )}

      {selectedModal === 'report' && (
          <Modal onClose={closeModal}>
            <h2>Allergy Report</h2>
            <table className={styles.reportTable}>
              <thead>
              <tr>
                <th>Food</th>
                <th>Ingredients</th>
                <th>Symptoms</th>
                <th>Severity</th>
              </tr>
              </thead>
              <tbody>
              {Array.isArray(reportData) && reportData.length > 0 ? (
                  reportData.map((report, index) => (
                      <tr key={index}>
                        <td>{report.food?.name || 'N/A'}</td>
                        <td>{report.food?.ingredients?.join(', ') || 'N/A'}</td>
                        <td>{report.symptoms}</td>
                        <td>{report.severity}</td>
                      </tr>
                  ))
              ) : (
                  <tr>
                    <td colSpan="4">No data available</td>
                  </tr>
              )}

              </tbody>
            </table>
          </Modal>
      )}


      {selectedModal === 'report' && (
          <Modal onClose={closeModal}>
            <h2>Allergy Report</h2>
            <table className={styles.reportTable}>
              <thead>
              <tr>
                <th>Food</th>
                <th>Ingredients</th>
                <th>Symptoms</th>
                <th>Severity</th>
              </tr>
              </thead>
              <tbody>
              {Array.isArray(reportData) && reportData.length > 0 ? (
                  reportData.map((report, index) => (
                      <tr key={index}>
                        <td>{report.food?.name || 'N/A'}</td>
                        <td>{report.food?.ingredients?.join(', ') || 'N/A'}</td>
                        <td>{report.symptoms}</td>
                        <td>{report.severity}</td>
                      </tr>
                  ))
              ) : (
                  <tr>
                    <td colSpan="4">No data available</td>
                  </tr>
              )}

              </tbody>
            </table>
          </Modal>
      )}

    </div>
  );
};

const Modal = ({onClose, children}) => (
    <div className={styles.modal}>
      <div className={styles.modalContent}>
        <button onClick={onClose} className={styles.closeButton}><CloseIcon/></button>
        {children}
      </div>
    </div>
);

export default Dashboard;
