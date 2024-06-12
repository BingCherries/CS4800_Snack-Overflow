package com.example.petallergytracker.Repository;

import com.example.petallergytracker.Models.AllergicReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AllergicReactionRepository extends JpaRepository<AllergicReaction, Long> {

    /**
     * Finds the count of allergic reactions per ingredient, ordered by the count in descending order.
     * @return A list of objects containing ingredient names and reaction counts.
     */
    @Query("SELECT r.ingredient.name, COUNT(r) as reactionCount FROM AllergicReaction r GROUP BY r.ingredient.name ORDER BY reactionCount DESC")
    List<Object[]> countReactionsByIngredient();
}
