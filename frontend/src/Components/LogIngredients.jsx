import React, { useState } from 'react';


function LogIngredients() {
    const [ingredient, setIngredient] = useState('');
    const [date, setDate] = useState('');
    const [loggedItems, setLoggedItems] = useState([]);
    const [searchQuery, setSearchQuery] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        if (ingredient && date) {
            const newItem = { ingredient, date };
            setLoggedItems([...loggedItems, newItem]);
            setIngredient('');
            setDate('');
        }
    };

    const handleSearch = (event) => {
        setSearchQuery(event.target.value);
    };

    const filteredItems = loggedItems.filter(item =>
        item.ingredient.toLowerCase().includes(searchQuery.toLowerCase())
    );

    return (
        <div>
            <h1>Log New Ingredients</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="ingredientName">Ingredient Name:</label>
                <input
                    type="text"
                    id="ingredientName"
                    name="ingredientName"
                    value={ingredient}
                    onChange={(e) => setIngredient(e.target.value)}
                    required
                />
                <label htmlFor="ingredientDate">Date:</label>
                <input
                    type="date"
                    id="ingredientDate"
                    name="ingredientDate"
                    value={date}
                    onChange={(e) => setDate(e.target.value)}
                    required
                />
                <button type="submit">Log Ingredient</button>
            </form>
            
            <h2>Logged Ingredients:</h2>
            <div>
            <input
                type="text"
                placeholder="Search ingredients..."
                value={searchQuery}
                onChange={handleSearch}
                className="search-bar"
            />
            </div>
            <div className="notes-container">
                {filteredItems.map((item, index) => (
                    <div key={index} className="note">
                        <p><strong>Ingredient:</strong> {item.ingredient}</p>
                        <p><strong>Date:</strong> {item.date}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default LogIngredients;
