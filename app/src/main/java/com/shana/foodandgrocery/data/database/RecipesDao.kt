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
import com.shana.foodandgrocery.data.database.entitis.FavoritesEntity
import com.shana.foodandgrocery.data.database.entitis.RecipeExtendedIngredientCrossRefEntity
import com.shana.foodandgrocery.data.database.entitis.RecipeWithExtendedIngredients
import com.shana.foodandgrocery.data.database.entitis.RecipesEntity
import com.shana.foodandgrocery.data.mappers.toExtendedIngredientEntity
import com.shana.foodandgrocery.data.mappers.toRecipeEntity
import com.shana.foodandgrocery.data.network.dto.newDto.RecipeDto
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
    fun getIngredientOfRecipe(recipeId: Int): Flow<RecipeWithExtendedIngredients>

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

    suspend fun insertRecipeDto(recipesDto: List<RecipeDto>) {
        var recipes = mutableListOf<RecipesEntity>()
        var RecipeExtendedIngredientCrossRefs =
            mutableListOf<RecipeExtendedIngredientCrossRefEntity>()
        recipesDto.forEach { item ->
            recipes.add(item.toRecipeEntity())
            item.extendedIngredientDtos.forEach { ing ->
                var id = insertExtendedIngredient(ing.toExtendedIngredientEntity())
                RecipeExtendedIngredientCrossRefs.add(
                    RecipeExtendedIngredientCrossRefEntity(
                        recipeId = item.id,
                        id = id.toInt()
                    )
                )
            }
        }
        upsertRecipes(recipes)
        upsertRecipeExtendedIngredientCrossRefs(RecipeExtendedIngredientCrossRefs)

    }

}