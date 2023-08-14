package com.shana.foodandgrocery.data

import androidx.paging.PagingSource
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.shana.foodandgrocery.data.database.RecipesDao
import com.shana.foodandgrocery.data.database.entitis.FavoriteRecipeEntity
import com.shana.foodandgrocery.data.database.entitis.RecipeExtendedIngredientCrossRefEntity
import com.shana.foodandgrocery.data.database.entitis.RecipesEntity
import com.shana.foodandgrocery.data.database.entitis.ShoppingItemEntity
import com.shana.foodandgrocery.data.mappers.toExtendedIngredientEntity
import com.shana.foodandgrocery.data.mappers.toRecipe
import com.shana.foodandgrocery.data.mappers.toRecipeEntity
import com.shana.foodandgrocery.data.network.dto.newDto.RecipeDto
import com.shana.foodandgrocery.models.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    val recipesDao: RecipesDao
) {

    fun readRecipes(): PagingSource<Int, RecipesEntity> {
        return recipesDao.readRecipes()
    }

   suspend fun deleteShoppingItem(shoppingItemEntity: ShoppingItemEntity) {
        return recipesDao.deleteShoppingItem(shoppingItemEntity)
    }

    fun checkRecipeIsFavorite(id: Long): Flow<Boolean> {
        return recipesDao.checkRecipeIsFavorite(id)
    }

    fun readFavoriteRecipes(): Flow<List<Recipe>> {
        return recipesDao.readFavoriteRecipes()
            .map { favoriteRecipeEntities ->
                favoriteRecipeEntities.map {
                    it.toRecipe()
                }
            }
    }

    suspend fun insertRecipes(recipesEntities: List<RecipesEntity>) {
        recipesDao.upsertRecipes(recipesEntities)
    }

    suspend fun insertRecipe(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipe(recipesEntity)
    }

    suspend fun insertFavoriteRecipes(favoriteRecipeEntity: FavoriteRecipeEntity) {
        recipesDao.insertFavoriteRecipe(favoriteRecipeEntity)
    }


    suspend fun deleteFavoriteRecipe(favoriteRecipeEntity: FavoriteRecipeEntity) {
        recipesDao.deleteFavoriteRecipe(favoriteRecipeEntity)
    }

    suspend fun deleteAllFavoriteRecipes() {
        recipesDao.deleteAllFavoriteRecipes()
    }

    suspend fun insertRecipeDto(recipesDto: List<RecipeDto>) {
        var recipes = mutableListOf<RecipesEntity>()
        var RecipeExtendedIngredientCrossRefs =
            mutableListOf<RecipeExtendedIngredientCrossRefEntity>()
        recipesDto.forEach { item ->
            recipes.add(item.toRecipeEntity())
            item.extendedIngredientDtos.forEach { ing ->
                var id = recipesDao.insertExtendedIngredient(ing.toExtendedIngredientEntity())
                RecipeExtendedIngredientCrossRefs.add(
                    RecipeExtendedIngredientCrossRefEntity(
                        recipeId = item.id,
                        id = id
                    )
                )
            }
        }
        recipesDao.upsertRecipes(recipes)
        recipesDao.upsertRecipeExtendedIngredientCrossRefs(RecipeExtendedIngredientCrossRefs)

    }


    suspend fun clearRecipeWithIngredients() {
        recipesDao.deleteAllRecipeExtendedIngredient()
        recipesDao.deleteAllExtendIngredient()
        return recipesDao.deleteAllRecipes()
    }

    fun getRecipeById(id: Long): Flow<Recipe> {
        return recipesDao.getIngredientOfRecipe(id).map { it.toRecipe() }
    }

    suspend fun getRecipeCount(): Int {
        return recipesDao.getRecipeCount()
    }

    suspend fun upsertShoppingList(shoppingList: List<ShoppingItemEntity>) {
        return recipesDao.upsertShoppingList(shoppingList)
    }

    fun readShoppingList(): Flow<List<ShoppingItemEntity>> {
        return recipesDao.readShoppingList()
    }

}