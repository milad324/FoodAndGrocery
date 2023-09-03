package com.shana.foodandgrocery.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.shana.foodandgrocery.data.database.entitis.ExtendedIngredientEntity
import com.shana.foodandgrocery.data.database.entitis.FavoriteRecipeEntity
import com.shana.foodandgrocery.data.database.entitis.PlannerEntity
import com.shana.foodandgrocery.data.database.entitis.RecipeExtendedIngredientCrossRefEntity
import com.shana.foodandgrocery.data.database.entitis.RecipeWithExtendedIngredients
import com.shana.foodandgrocery.data.database.entitis.RecipesEntity
import com.shana.foodandgrocery.data.database.entitis.ShoppingItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipesEntity: RecipesEntity)

    @Upsert
    suspend fun upsertRecipes(recipesEntities: List<RecipesEntity>)

    @Upsert
    suspend fun upsertRecipeExtendedIngredientCrossRefs(recipeExtendedIngredientCrossRefs: List<RecipeExtendedIngredientCrossRefEntity>)

    @Transaction
    @Query("SELECT * FROM recipes_table WHERE recipeId=:recipeId")
    fun getIngredientOfRecipe(recipeId: Long): Flow<RecipeWithExtendedIngredients>

    @Query("SELECT * FROM recipes_table ORDER BY recipeId ASC")
    fun readRecipes(): PagingSource<Int, RecipesEntity>


    @Query("DELETE FROM recipes_table")
    suspend fun deleteAllRecipes()


    @Query("DELETE FROM recipe_extended_ingredient_cross_ref_table")
    suspend fun deleteAllRecipeExtendedIngredient()

    @Query("SELECT COUNT(recipeId) from recipes_table")
    suspend fun getRecipeCount(): Int

    @Query("SELECT * FROM recipes_table WHERE recipeId=:id")
    fun getRecipeById(id: Int): Flow<RecipesEntity>


}