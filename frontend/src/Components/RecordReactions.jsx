import React, { useState } from 'react';


function RecordReactions() {
    const [reactionType, setReactionType] = useState('');
    const [description, setDescription] = useState('');
    const [date, setDate] = useState('');
    const [recordedReactions, setRecordedReactions] = useState([]);
    const [editIndex, setEditIndex] = useState(null);
    const [showValidationMessage, setShowValidationMessage] = useState(false);
    const [searchTerm, setSearchTerm] = useState('');
    const [showDeleteConfirmation, setShowDeleteConfirmation] = useState(null);
    const [showEditModal, setShowEditModal] = useState(false);
    const [showToast, setShowToast] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();
        if (reactionType && description && date) {
            setRecordedReactions([...recordedReactions, { reactionType, description, date }]);
            setReactionType('');
            setDescription('');
            setDate('');
            setShowValidationMessage(false);
        } else {
            setShowValidationMessage(true);
        }
    };

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredReactions = recordedReactions.filter(log =>
        log.reactionType.toLowerCase().includes(searchTerm.toLowerCase()) ||
        log.description.toLowerCase().includes(searchTerm.toLowerCase()) ||
        log.date.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const openEditModal = (index) => {
        setEditIndex(index);
        setReactionType(recordedReactions[index].reactionType);
        setDescription(recordedReactions[index].description);
        setDate(recordedReactions[index].date);
        setShowEditModal(true);
    };

    const closeEditModal = () => {
        setShowEditModal(false);
        setEditIndex(null);
        setReactionType('');
        setDescription('');
        setDate('');
    };

    const handleEditSubmit = (event) => {
        event.preventDefault();
        if (reactionType && description && date) {
            const updatedReactions = [...recordedReactions];
            updatedReactions[editIndex] = { reactionType, description, date };
            setRecordedReactions(updatedReactions);
            closeEditModal();
            setShowToast(true);
            setTimeout(() => setShowToast(false), 500);
        } else {
            setShowValidationMessage(true);
        }
    };

    const handleDelete = (index) => {
        setShowDeleteConfirmation(index);
    };

    const confirmDelete = (index) => {
        setRecordedReactions(recordedReactions.filter((_, i) => i !== index));
        setShowDeleteConfirmation(null);
    };

    const cancelDelete = () => {
        setShowDeleteConfirmation(null);
    };

    return (
        <div>
            <h1>Record Allergic Reactions</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="reactionType">Reaction Type:</label>
                <select
                    id="reactionType"
                    name="reactionType"
                    value={reactionType}
                    onChange={(e) => setReactionType(e.target.value)}
                    required
                >
                    <option value="">Select Reaction Type</option>
                    <option value="Itching">Itching</option>
                    <option value="Vomiting">Vomiting</option>
                    <option value="Diarrhea">Diarrhea</option>
                    {/* Add more reaction types as needed */}
                </select>
                <label htmlFor="description">Description:</label>
                <textarea
                    id="description"
                    name="description"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    required
                ></textarea>
                <label htmlFor="date">Date:</label>
                <input
                    type="date"
                    id="date"
                    name="date"
                    value={date}
                    onChange={(e) => setDate(e.target.value)}
                    required
                />
                <button type="submit">Record Reaction</button>
                {showValidationMessage && <p className="validation-message">*Please make sure all fields are completed</p>}
            </form>

            <h2>Search Reactions:</h2>
            <input
                type="text"
                placeholder="Search reactions..."
                value={searchTerm}
                onChange={handleSearch}
            />

            <h2>Recorded Reactions:</h2>
            <div className="reactions-container">
                {filteredReactions.map((log, index) => (
                    <div key={index} className="reaction-note">
                        <p><strong>Reaction Type:</strong> {log.reactionType}</p>
                        <p><strong>Description:</strong> {log.description}</p>
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
                        <h2>Edit Reaction</h2>
                        <form onSubmit={handleEditSubmit}>
                            <label htmlFor="editReactionType">Reaction Type:</label>
                            <select
                                id="editReactionType"
                                name="editReactionType"
                                value={reactionType}
                                onChange={(e) => setReactionType(e.target.value)}
                                required
                            >
                                <option value="">Select Reaction Type</option>
                                <option value="Itching">Itching</option>
                                <option value="Vomiting">Vomiting</option>
                                <option value="Diarrhea">Diarrhea</option>
                                {/* Add more reaction types as needed */}
                            </select>
                            <label htmlFor="editDescription">Description:</label>
                            <textarea
                                id="editDescription"
                                name="editDescription"
                                value={description}
                                onChange={(e) => setDescription(e.target.value)}
                                required
                            ></textarea>
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

export default RecordReactions;
