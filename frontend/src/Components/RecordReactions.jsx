import React, { useState } from 'react';


function RecordReactions() {
    const [reactionType, setReactionType] = useState('');
    const [description, setDescription] = useState('');
    const [date, setDate] = useState('');
    const [loggedReactions, setLoggedReactions] = useState([]);
    const [showValidationMessage, setShowValidationMessage] = useState(false);
    const [searchTerm, setSearchTerm] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        if (reactionType && description && date) {
            const newReaction = { reactionType, description, date };
            setLoggedReactions([...loggedReactions, newReaction]);
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

    const filteredReactions = loggedReactions.filter(reaction =>
        reaction.reactionType.toLowerCase().includes(searchTerm.toLowerCase()) ||
        reaction.description.toLowerCase().includes(searchTerm.toLowerCase()) ||
        reaction.date.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div>
            <h1>Record Allergic Reaction</h1>
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
                <input
                    type="text"
                    id="description"
                    name="description"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    required
                />
                <label htmlFor="reactionDate">Date:</label>
                <input
                    type="date"
                    id="reactionDate"
                    name="reactionDate"
                    value={date}
                    onChange={(e) => setDate(e.target.value)}
                    required
                />
                <button className="reaction-btn" type="submit">Record Reaction</button>
                {showValidationMessage && (
                    <p className="validation-message">*Please make sure all fields are completed</p>
                )}
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
                {filteredReactions.map((reaction, index) => (
                    <div key={index} className="reaction-note">
                        <p><strong>Type:</strong> {reaction.reactionType}</p>
                        <p><strong>Description:</strong> {reaction.description}</p>
                        <p><strong>Date:</strong> {reaction.date}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default RecordReactions;


