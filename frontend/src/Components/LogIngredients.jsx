import React, { useState } from 'react';
import EditIcon from '@mui/icons-material/ModeEdit';
import DeleteIcon from '@mui/icons-material/Delete';
import toast, { Toaster } from 'react-hot-toast';

function LogIngredients() {
    const [ingredient, setIngredient] = useState('');
    const [date, setDate] = useState('');
    const [loggedIngredients, setLoggedIngredients] = useState([]);
    const [editIndex, setEditIndex] = useState(null);
    const [searchTerm, setSearchTerm] = useState('');
    const [showDeleteConfirmation, setShowDeleteConfirmation] = useState(null);
    const [showEditModal, setShowEditModal] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();
        setLoggedIngredients([...loggedIngredients, { ingredient, date }]);
        setIngredient('');
        setDate('');
    };

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredIngredients = loggedIngredients.filter(log =>
        log.ingredient.toLowerCase().includes(searchTerm.toLowerCase()) ||
        log.date.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const openEditModal = (index) => {
        setEditIndex(index);
        setIngredient(loggedIngredients[index].ingredient);
        setDate(loggedIngredients[index].date);
        setShowEditModal(true);
    };

    const closeEditModal = () => {
        setShowEditModal(false);
        setEditIndex(null);
        setIngredient('');
        setDate('');
    };

    const handleEditSubmit = (event) => {
        event.preventDefault();
        const updatedIngredients = [...loggedIngredients];
        updatedIngredients[editIndex] = { ingredient, date };
        setLoggedIngredients(updatedIngredients);
        closeEditModal();
        toast.success('Changes saved successfully!');
    };

    const handleDelete = (index) => {
        setShowDeleteConfirmation(index);
    };

    const confirmDelete = (index) => {
        setLoggedIngredients(loggedIngredients.filter((_, i) => i !== index));
        setShowDeleteConfirmation(null);
        toast.success('Ingredient deleted!');
    };

    const cancelDelete = () => {
        setShowDeleteConfirmation(null);
    };

    return (
        <div className="log-ingredients-container">
            {/* Side Area with User Input Fields */}
            <div className="side-area">
                <h1 className='log_ingre_title'>Log Ingredients</h1>
                <label htmlFor="search_bar">Search Ingredients:</label>
                <input
                    className='ingre_search'
                    type="text"
                    placeholder="Search ingredients..."
                    value={searchTerm}
                    onChange={handleSearch}
                />
                <form onSubmit={handleSubmit}>
                    <label htmlFor="ingredient">Ingredient:</label>
                    <input
                        className="ingredient_form_input"
                        type="text"
                        id="ingredient"
                        name="ingredient"
                        value={ingredient}
                        onChange={(e) => setIngredient(e.target.value)}
                        required
                    /><br></br>
                    <label htmlFor="date">Date:</label><br></br>
                    <input
                        className='ingredient_date_input'
                        type="date"
                        id="date"
                        name="date"
                        value={date}
                        onChange={(e) => setDate(e.target.value)}
                        required
                    />
                    <button className="log_submit_btn" type="submit">Log Ingredient</button>
                </form>
            </div>

            {/* Main Area with Logged Ingredients */}
            <div className="main-area">
                <h2>Logged Ingredients:</h2>
                <div className="ingredients-container">
                    {filteredIngredients.map((log, index) => (
                        <div key={index} className="ingredient-note">
                            <div className="ingredient-header">
                                <p><strong>Ingredient:</strong> {log.ingredient}</p>
                                <p><strong>Date:</strong> {log.date}</p>
                            </div>
                            <div className="button-container">
                                <button className='edit-log' onClick={() => openEditModal(index)}><EditIcon /></button>
                                <button className='delete-log' onClick={() => handleDelete(index)}><DeleteIcon /></button>
                            </div>
                        </div>
                    ))}
                </div>

                {/* Delete Confirmation Dialog */}
                {showDeleteConfirmation !== null && (
                    <div className="delete-confirmation">
                        <p>Are you sure you want to delete this ingredient?</p>
                        <button className='yes-btn' onClick={() => confirmDelete(showDeleteConfirmation)}>Yes</button>
                        <button className='no-btn' onClick={cancelDelete}>No</button>
                    </div>
                )}

                {/* Edit Modal */}
                {showEditModal && (
                    <div className="edit-modal">
                        <div className="edit-modal-content">
                            <h2>Edit Ingredient</h2>
                            <form onSubmit={handleEditSubmit}>
                                <label htmlFor="editIngredient">Ingredient:</label>
                                <br></br>
                                <input
                                    className='edit-ingre-input'
                                    type="text"
                                    id="editIngredient"
                                    name="editIngredient"
                                    value={ingredient}
                                    onChange={(e) => setIngredient(e.target.value)}
                                    required
                                />
                                <br></br>
                                <label htmlFor="editDate">Date:</label>
                                <br></br>
                                <input
                                    className='del-ingre-input'
                                    type="date"
                                    id="editDate"
                                    name="editDate"
                                    value={date}
                                    onChange={(e) => setDate(e.target.value)}
                                    required
                                /><br></br>
                                <button className='save-changees-btn' type="submit">Save Changes</button>
                                <button className='cancel-btn' type="button" onClick={closeEditModal}>Cancel</button>
                            </form>
                        </div>
                    </div>
                )}

                {/* Toast Notification */}
                <Toaster />
            </div>
        </div>
    );
}

export default LogIngredients;

