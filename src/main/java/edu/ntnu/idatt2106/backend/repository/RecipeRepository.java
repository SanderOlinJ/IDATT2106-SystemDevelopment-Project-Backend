package edu.ntnu.idatt2106.backend.repository;


import edu.ntnu.idatt2106.backend.model.item.Item;
import edu.ntnu.idatt2106.backend.model.recipe.Recipe;
import edu.ntnu.idatt2106.backend.model.recipe.RecipeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
/*    @Query("SELECT r FROM Recipe r " +
            "JOIN r.recipeItems ri " +
            "JOIN ri.item i " +
            "JOIN i.fridgeItems fi " +
            "GROUP BY r.id " +
            "ORDER BY COUNT(fi) DESC")
    List<Recipe> findAllByFridgeItems();*/

   /* @Query("SELECT r FROM Recipe r JOIN r.recipeItems ri JOIN ri.item i WHERE i.id IN (SELECT fi.item.id FROM FridgeItem fi WHERE fi.expirationDate > :today) GROUP BY r.id ORDER BY COUNT(i.id) DESC")
    List<Recipe> findAllOrderByFridgeItemDate(@Param("today") LocalDate today);*/

}
