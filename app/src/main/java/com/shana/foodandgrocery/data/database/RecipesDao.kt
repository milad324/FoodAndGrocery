package com.shana.foodandgrocery.data.database

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
import com.shana.foodandgrocery.data.database.entitis.RecipeExtendedIngredientCrossRefEntity
import com.shana.foodandgrocery.data.database.entitis.RecipeWithExtendedIngredients
import com.shana.foodandgrocery.data.database.entitis.RecipesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipesEntity: RecipesEntity)

    @Upsert
    suspend fun upsertRecipes(recipesEntities: List<RecipesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExtendedIngredient(extendedIngredient: ExtendedIngredientEntity): Long

    @Upsert
    suspend fun upsertRecipeExtendedIngredientCrossRefs(RecipeExtendedIngredientCrossRefs: List<RecipeExtendedIngredientCrossRefEntity>)

    @Transaction
    @Query("SELECT * FROM recipes_table WHERE recipeId=:recipeId")
    fun getIngredientOfRecipe(recipeId: Long): Flow<RecipeWithExtendedIngredients>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favoriteRecipeEntity: FavoriteRecipeEntity)

    @Delete
    suspend fun deleteFavoriteRecipe(favoriteRecipeEntity: FavoriteRecipeEntity)

    @Query("SELECT EXISTS(SELECT * FROM favorite_recipes_table WHERE recipeId = :id)")
    fun checkRecipeIsFavorite(id: Long):Flow<Boolean>

    @Query("SELECT * FROM recipes_table ORDER BY recipeId ASC")
    fun readRecipes(): PagingSource<Int, RecipesEntity>

    @Query("SELECT * FROM favorite_recipes_table ORDER BY recipeId ASC")
    fun readFavoriteRecipes(): PagingSource<Int, FavoriteRecipeEntity>



    @Query("DELETE FROM favorite_recipes_table")
    suspend fun deleteAllFavoriteRecipes()

    @Query("DELETE FROM recipes_table")
    suspend fun deleteAllRecipes()

    @Query("DELETE FROM extended_ingredient_table")
    suspend fun deleteAllExtendIngredient()


    @Query("DELETE FROM recipe_extended_ingredient_cross_ref_table")
    suspend fun deleteAllRecipeExtendedIngredient()

    @Query("SELECT COUNT(recipeId) from recipes_table")
    suspend fun getRecipeCount(): Int


    @Query("SELECT * FROM recipes_table WHERE recipeId=:id")
    fun getRecipeById(id: Int): Flow<RecipesEntity>



}