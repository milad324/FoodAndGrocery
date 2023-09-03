package com.shana.foodandgrocery.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shana.foodandgrocery.data.database.entitis.FavoriteRecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favoriteRecipeEntity: FavoriteRecipeEntity)

    @Delete
    suspend fun deleteFavoriteRecipe(favoriteRecipeEntity: FavoriteRecipeEntity)

    @Query("SELECT EXISTS(SELECT * FROM favorite_recipes_table WHERE recipeId = :id)")
    fun checkRecipeIsFavorite(id: Long): Flow<Boolean>

    @Query("SELECT * FROM favorite_recipes_table ORDER BY recipeId ASC")
    fun readFavoriteRecipes(): Flow<List<FavoriteRecipeEntity>>

    @Query("DELETE FROM favorite_recipes_table")
    suspend fun deleteAllFavoriteRecipes()

}