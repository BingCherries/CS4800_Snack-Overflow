import React, { useState } from 'react';
import axios from '../api';

const AllergyForm = () => {
    const [foodName, setFoodName] = useState('');
    const [ingredients, setIngredients] = useState('');
    const [symptoms, setSymptoms] = useState('');
    const [severity, setSeverity] = useState(1);

    const handleSubmit = async (event) => {
        event.preventDefault();

        const data = {
            food: {
                name: foodName,
                ingredients: ingredients.split(',').map(ing => ing.trim())
            },
            symptoms: symptoms,
            severity: severity
        };

        try {
            const response = await axios.post('/allergy', data);
            console.log('Allergic reaction created:', response.data);
        } catch (error) {
            console.error('There was an error creating the allergic reaction!', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Food Name:</label>
                <input type="text" value={foodName} onChange={(e) => setFoodName(e.target.value)} />
            </div>
            <div>
                <label>Ingredients (comma separated):</label>
                <input type="text" value={ingredients} onChange={(e) => setIngredients(e.target.value)} />
            </div>
            <div>
                <label>Symptoms:</label>
                <input type="text" value={symptoms} onChange={(e) => setSymptoms(e.target.value)} />
            </div>
            <div>
                <label>Severity:</label>
                <input type="number" value={severity} onChange={(e) => setSeverity(e.target.value)} min="1" max="10" />
            </div>
            <button type="submit">Submit</button>
        </form>
    );
};

export default AllergyForm;
