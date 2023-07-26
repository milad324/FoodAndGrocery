package com.shana.foodandgrocery.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.shana.foodandgrocery.data.database.entitis.FavoritesEntity
import com.shana.foodandgrocery.data.database.entitis.RecipesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipesEntity: RecipesEntity)

    @Upsert
    suspend fun upsertRecipes(recipesEntities: List<RecipesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Query("SELECT * FROM recipes_table ORDER BY recipeId ASC")
    fun readRecipes(): PagingSource<Int, RecipesEntity>

    @Query("SELECT * FROM favorite_recipes_table ORDER BY recipeId ASC")
    fun readFavoriteRecipes(): PagingSource<Int, FavoritesEntity>

    @Delete
    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Query("DELETE FROM favorite_recipes_table")
    suspend fun deleteAllFavoriteRecipes()

    @Query("DELETE FROM recipes_table")
    suspend fun deleteAllRecipes()

    @Query("SELECT COUNT(recipeId) from recipes_table")
    suspend fun getRecipeCount(): Int


    @Query("SELECT * FROM recipes_table WHERE recipeId=:id")
    fun getRecipeById(id: Int): Flow<RecipesEntity>


}