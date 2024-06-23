import React, { useState } from 'react';


function LogIngredients() {
    const [ingredient, setIngredient] = useState('');
    const [date, setDate] = useState('');
    const [loggedIngredients, setLoggedIngredients] = useState([]);
    const [editIndex, setEditIndex] = useState(null);
    const [searchTerm, setSearchTerm] = useState('');
    const [showDeleteConfirmation, setShowDeleteConfirmation] = useState(null);
    const [showEditModal, setShowEditModal] = useState(false);
    const [showToast, setShowToast] = useState(false);

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
        setShowToast(true);
        setTimeout(() => setShowToast(false), 500);
    };

    const handleDelete = (index) => {
        setShowDeleteConfirmation(index);
    };

    const confirmDelete = (index) => {
        setLoggedIngredients(loggedIngredients.filter((_, i) => i !== index));
        setShowDeleteConfirmation(null);
    };

    const cancelDelete = () => {
        setShowDeleteConfirmation(null);
    };

    return (
        <div>
            <h1>Log Ingredients</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="ingredient">Ingredient:</label>
                <input
                    type="text"
                    id="ingredient"
                    name="ingredient"
                    value={ingredient}
                    onChange={(e) => setIngredient(e.target.value)}
                    required
                />
                <label htmlFor="date">Date:</label>
                <input
                    type="date"
                    id="date"
                    name="date"
                    value={date}
                    onChange={(e) => setDate(e.target.value)}
                    required
                />
                <button type="submit">Log Ingredient</button>
            </form>

            <h2>Search Ingredients:</h2>
            <input
                type="text"
                placeholder="Search ingredients..."
                value={searchTerm}
                onChange={handleSearch}
            />

            <h2>Logged Ingredients:</h2>
            <div className="ingredients-container">
                {filteredIngredients.map((log, index) => (
                    <div key={index} className="ingredient-note">
                        <p><strong>Ingredient:</strong> {log.ingredient}</p>
                        <p><strong>Date:</strong> {log.date}</p>
                        <button onClick={() => openEditModal(index)}>Edit</button>
                        <button onClick={() => handleDelete(index)}>Delete</button>
                    </div>
                ))}
            </div>

            {showDeleteConfirmation !== null && (
                <div className="delete-confirmation">
                    <p>Are you sure you want to delete this note?</p>
                    <button onClick={() => confirmDelete(showDeleteConfirmation)}>Yes</button>
                    <button onClick={cancelDelete}>No</button>
                </div>
            )}

            {showEditModal && (
                <div className="edit-modal">
                    <div className="edit-modal-content">
                        <h2>Edit Ingredient</h2>
                        <form onSubmit={handleEditSubmit}>
                            <label htmlFor="editIngredient">Ingredient:</label>
                            <input
                                type="text"
                                id="editIngredient"
                                name="editIngredient"
                                value={ingredient}
                                onChange={(e) => setIngredient(e.target.value)}
                                required
                            />
                            <label htmlFor="editDate">Date:</label>
                            <input
                                type="date"
                                id="editDate"
                                name="editDate"
                                value={date}
                                onChange={(e) => setDate(e.target.value)}
                                required
                            />
                            <button type="submit">Save Changes</button>
                            <button type="button" onClick={closeEditModal}>Cancel</button>
                        </form>
                    </div>
                </div>
            )}

            {showToast && (
                <div className="toast">
                    <p className='toast-msg'>Changes Saved</p>
                </div>
            )}
        </div>
    );
}

export default LogIngredients;
