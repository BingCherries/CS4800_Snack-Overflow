import React, { useState } from 'react';
import toast, { Toaster } from 'react-hot-toast'; // Importing toast for notifications
import styles from '../CSS/RecordReactions.module.css'; // Importing styles
import api from '../services/api';

const RecordReaction = () => {
  // State variables for form inputs, logs, modals, search, and filters
  const [foodName, setFoodName] = useState('');
  const [ingredients, setIngredients] = useState([]);
  const [ingredientInput, setIngredientInput] = useState('');
  const [symptoms, setSymptoms] = useState('');
  const [severity, setSeverity] = useState('');
  const [date, setDate] = useState('');
  const [logs, setLogs] = useState([]);
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
  const [currentEditIndex, setCurrentEditIndex] = useState(null);
  const [searchQuery, setSearchQuery] = useState('');
  const [filter, setFilter] = useState('mostRecent');

  // Function to add ingredient to the list
  const handleAddIngredient = () => {
    if (ingredientInput.trim() !== '') {
      setIngredients([...ingredients, ingredientInput.trim()]);
      setIngredientInput('');
    }
  };

  // Function to remove ingredient from the list
  const handleRemoveIngredient = (index) => {
    const updatedIngredients = [...ingredients];
    updatedIngredients.splice(index, 1);
    setIngredients(updatedIngredients);
  };

  // Function to add a new log entry
  const handleAddLog = () => {
    if (foodName.trim() === '' || symptoms.trim() === '' || severity.trim() === '' || date.trim() === '') {
      alert('Please fill in all fields.');
      return;
    }

    const newLog = {
      food: {
        name: foodName.trim(),
        ingredients: ingredients.map((ing) => ing.trim()),
      },
      symptoms: symptoms.trim(),
      severity: parseInt(severity.trim()),
      date: new Date(date).toISOString(),
    };

    setLogs([...logs, newLog]);
    addAllergyReaction(newLog);
    resetFormFields();
    toast.success('Logged Reaction'); // Success toast notification
  };

  // Function to reset form fields after submission
  const resetFormFields = () => {
    setFoodName('');
    setIngredients([]);
    setIngredientInput('');
    setSymptoms('');
    setSeverity('');
    setDate('');
  };

  // Function to handle editing an existing log entry
  const handleEditLog = (index) => {
    const log = logs[index];
    setFoodName(log.food.name);
    setIngredients(log.food.ingredients);
    setSymptoms(log.symptoms);
    setSeverity(log.severity.toString());
    setDate(new Date(log.date).toISOString().split('T')[0]);
    setCurrentEditIndex(index);
    setIsEditModalOpen(true);
  };

  // Function to update an existing log entry
  const handleUpdateLog = () => {
    if (foodName.trim() === '' || symptoms.trim() === '' || severity.trim() === '' || date.trim() === '') {
      alert('Please fill in all fields.');
      return;
    }

    const updatedLog = {
      food: {
        name: foodName.trim(),
        ingredients: ingredients.map((ing) => ing.trim()),
      },
      symptoms: symptoms.trim(),
      severity: parseInt(severity.trim()),
      date: new Date(date).toISOString(),
    };

    const updatedLogs = [...logs];
    updatedLogs[currentEditIndex] = updatedLog;
    setLogs(updatedLogs);
    resetFormFields();
    setIsEditModalOpen(false);
    toast.success('Changes saved'); // Success toast notification
  };

  // Function to prompt for deleting a log entry
  const handleDeleteLog = (index) => {
    setCurrentEditIndex(index);
    setIsDeleteModalOpen(true);
  };

  // Function to confirm deletion of a log entry
  const confirmDeleteLog = () => {
    const updatedLogs = [...logs];
    updatedLogs.splice(currentEditIndex, 1);
    setLogs(updatedLogs);
    setIsDeleteModalOpen(false);
    toast.success('Note deleted'); // Success toast notification
  };

  // Function to handle search input change
  const handleSearch = (e) => {
    setSearchQuery(e.target.value.toLowerCase());
  };

  // Function to handle filter change
  const handleFilterChange = (e) => {
    setFilter(e.target.value);
  };

  // Filtering logs based on search query and selected filter
  const filteredLogs = logs
    .filter((log) => {
      return (
        log.food.name.toLowerCase().includes(searchQuery) ||
        log.food.ingredients.some((ingredient) => ingredient.toLowerCase().includes(searchQuery)) ||
        log.symptoms.toLowerCase().includes(searchQuery)
      );
    })
    .sort((a, b) => {
      if (filter === 'mostRecent') {
        return new Date(b.date) - new Date(a.date);
      }
      if (filter === 'severityHighLow') {
        return b.severity - a.severity;
      }
      if (filter === 'severityLowHigh') {
        return a.severity - b.severity;
      }
      return 0;
    });

  // HTTP POST request functionality for adding an allergy reaction to the MongoDB
  const addAllergyReaction = async (newLog) => {
    try {
      const response = await api.post('/allergy', newLog);
      console.log('Allergic reaction added:', response.data);
    } catch (err) {
      console.error('Error adding allergic reaction:', err);
      toast.error('Error adding allergic reaction');
    }
  };

  return (
    <div className={styles.recordReactionContainer}>
      <Toaster /> {/* Toast container for notifications */}

      {/* Left panel for logging new reactions */}
      <div className={styles.leftPanel}>
        <div className={styles.section}>
          <h2 className={styles.sectionTitle}>Record Reaction</h2>

          {/* Input fields for logging reaction */}
          <div className={styles.inputArea}>
            <label htmlFor="foodName">Food Name:</label>
            <input
              type="text"
              id="foodName"
              value={foodName}
              onChange={(e) => setFoodName(e.target.value)}
              className={styles.input}
            />
          </div>

          <div className={styles.inputArea}>
            <label htmlFor="ingredients">Ingredients:</label>
            <div className={styles.ingredients}>
              <div className={styles.addIngredient}>
                <input
                  type="text"
                  value={ingredientInput}
                  onChange={(e) => setIngredientInput(e.target.value)}
                  className={styles.input}
                  placeholder="Add ingredient"
                />
                <button type="button" onClick={handleAddIngredient} className={styles.addIngredientButton}>
                  Add
                </button>
              </div>
              {ingredients.map((ing, index) => (
                <div key={index} className={styles.ingredient}>
                  <span>{ing}</span>
                  <button
                    type="button"
                    onClick={() => handleRemoveIngredient(index)}
                    className={styles.removeIngredient}
                  >
                    Remove
                  </button>
                </div>
              ))}
            </div>
          </div>

          <div className={styles.inputArea}>
            <label htmlFor="symptoms">Symptoms:</label>
            <input
              type="text"
              id="symptoms"
              value={symptoms}
              onChange={(e) => setSymptoms(e.target.value)}
              className={styles.input}
            />
          </div>

          <div className={styles.inputArea}>
            <label htmlFor="severity">Severity (0-10):</label>
            <select
              id="severity"
              value={severity}
              onChange={(e) => setSeverity(e.target.value)}
              className={styles.input}
            >
              {[...Array(11).keys()].map((num) => (
                <option key={num} value={num}>
                  {num}
                </option>
              ))}
            </select>
          </div>

          <div className={styles.inputArea}>
            <label htmlFor="date">Date:</label>
            <input
              type="date"
              id="date"
              value={date}
              onChange={(e) => setDate(e.target.value)}
              className={styles.input}
            />
          </div>

          {/* Button area for adding or updating logs */}
          <div className={styles.buttonArea}>
            {currentEditIndex === null ? (
              <button type="button" onClick={handleAddLog} className={styles.addButton}>
                Log Reaction
              </button>
              
            ) : (
              <button type="button" onClick={handleUpdateLog} className={styles.addButton}>
                Save Changes
              </button>
            )}
          </div>
        </div>

        {/* Section for filters */}
        <div className={styles.section}>
          <h2 className={styles.sectionTitle}>Search Logged Reactions</h2>
          <div className={styles.searchArea}>
            <input
              type="text"
              placeholder="Search logs..."
              value={searchQuery}
              onChange={handleSearch}
              className={styles.input}
            />
            <select value={filter} onChange={handleFilterChange} className={styles.drop}>
              <option value="mostRecent">Most Recent</option>
              <option value="severityHighLow">Severity: High to Low</option>
              <option value="severityLowHigh">Severity: Low to High</option>
            </select>
          </div>
        </div>
      </div>

      {/* Right panel for displaying logged reactions */}
      <div className={styles.rightPanel}>
        <div className={styles.section}>
          <h2 className={styles.sectionTitle}>Logged Reactions</h2>
          {filteredLogs.length === 0 ? (
            <p>No reactions logged yet.</p>
          ) : (
            <div className={styles.logList}>
              {filteredLogs.map((log, index) => (
                <div key={index} className={styles.logItem}>
                  <p>
                    <strong>Food Name:</strong> {log.food.name}
                  </p>
                  <p>
                    <strong>Ingredients:</strong> {log.food.ingredients.join(', ')}
                  </p>
                  <p>
                    <strong>Symptoms:</strong> {log.symptoms}
                  </p>
                  <p>
                    <strong>Severity:</strong> {log.severity}
                  </p>
                  <p>
                    <strong>Date:</strong> {new Date(log.date).toLocaleDateString()}
                  </p>
                  <div className={styles.logButtons}>
                    <button type="button" onClick={() => handleEditLog(index)} className={styles.editButton}>
                      Edit
                    </button>
                    <button type="button" onClick={() => handleDeleteLog(index)} className={styles.deleteButton}>
                      Delete
                    </button>
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>
      </div>

      {/* Edit log modal */}
      {isEditModalOpen && (
        <div className={styles.modal}>
          <div className={styles.modalContent}>
            <h2>Edit Log</h2>
            <div className={styles.inputArea}>
              <label htmlFor="editFoodName">Food Name:</label>
              <input
                type="text"
                id="editFoodName"
                value={foodName}
                onChange={(e) => setFoodName(e.target.value)}
                className={styles.input}
              />
            </div>

            <div className={styles.inputArea}>
              <label htmlFor="editIngredients">Ingredients:</label>
              <div className={styles.ingredients}>
                {ingredients.map((ing, index) => (
                  <div key={index} className={styles.ingredient}>
                    <span>{ing}</span>
                    <button type="button" onClick={() => handleRemoveIngredient(index)} className={styles.removeIngredient}>
                      Remove
                    </button>
                  </div>
                ))}
                <div className={styles.addIngredient}>
                  <input
                    type="text"
                    value={ingredientInput}
                    onChange={(e) => setIngredientInput(e.target.value)}
                    className={styles.input}
                    placeholder="Add ingredient"
                  />
                  <button type="button" onClick={handleAddIngredient} className={styles.addIngredientButton}>
                    Add
                  </button>
                </div>
              </div>
            </div>

            <div className={styles.inputArea}>
              <label htmlFor="editSymptoms">Symptoms:</label>
              <input
                type="text"
                id="editSymptoms"
                value={symptoms}
                onChange={(e) => setSymptoms(e.target.value)}
                className={styles.input}
              />
            </div>

            <div className={styles.inputArea}>
              <label htmlFor="editSeverity">Severity (0-10):</label>
              <select
                id="editSeverity"
                value={severity}
                onChange={(e) => setSeverity(e.target.value)}
                className={styles.input}
              >
                {[...Array(11).keys()].map((num) => (
                  <option key={num} value={num}>
                    {num}
                  </option>
                ))}
              </select>
            </div>

            <div className={styles.inputArea}>
              <label htmlFor="editDate">Date:</label>
              <input
                type="date"
                id="editDate"
                value={date}
                onChange={(e) => setDate(e.target.value)}
                className={styles.input}
              />
            </div>

            <div className={styles.buttonArea}>
              <button type="button" onClick={handleUpdateLog} className={styles.addButton}>
                Save Changes
              </button>
              <button type="button" onClick={() => setIsEditModalOpen(false)} className={styles.cancelButton}>
                Cancel
              </button>
            </div>
          </div>
        </div>
      )}

      {/* Delete log confirmation modal */}
      {isDeleteModalOpen && (
        <div className={styles.modal}>
          <div className={styles.modalContent}>
            <h2>Delete Log</h2>
            <p>Are you sure you want to delete this log?</p>
            <div className={styles.buttonArea}>
              <button type="button" onClick={confirmDeleteLog} className={styles.deleteButton}>
                Confirm Delete
              </button>
              <button type="button" onClick={() => setIsDeleteModalOpen(false)} className={styles.cancelButton}>
                Cancel
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default RecordReaction;