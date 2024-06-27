package com.example.petallergytracker.Models;

import java.util.List;

public class DashboardData {
        private List<String> foods;
        private List<String> reactions;
        private List<String> commonAllergens;

        public DashboardData(List<String> foods, List<String> reactions, List<String> commonAllergens) {
            this.foods = foods;
            this.reactions = reactions;
            this.commonAllergens = commonAllergens;
        }

        // Getters
        public List<String> getFoods() {
            return foods;
        }

        public List<String> getReactions() {
            return reactions;
        }

        public List<String> getCommonAllergens() {
            return commonAllergens;
        }
}
