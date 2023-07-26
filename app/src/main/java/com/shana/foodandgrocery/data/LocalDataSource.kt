package com.shana.foodandgrocery.data

import androidx.paging.PagingSource
import com.shana.foodandgrocery.data.database.RecipesDao
import com.shana.foodandgrocery.data.database.entitis.FavoritesEntity
import com.shana.foodandgrocery.data.database.entitis.RecipesEntity
import com.shana.foodandgrocery.data.mappers.toRecipe
import com.shana.foodandgrocery.models.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {

    fun readRecipes(): PagingSource<Int, RecipesEntity> {
        return recipesDao.readRecipes()
    }

    fun readFavoriteRecipes(): PagingSource<Int, FavoritesEntity> {
        return recipesDao.readFavoriteRecipes()
    }

    suspend fun insertRecipes(recipesEntities: List<RecipesEntity>) {
        recipesDao.upsertRecipes(recipesEntities)
    }

    suspend fun insertRecipe(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipe(recipesEntity)
    }

    suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity) {
        recipesDao.insertFavoriteRecipe(favoritesEntity)
    }


    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) {
        recipesDao.deleteFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteAllFavoriteRecipes() {
        recipesDao.deleteAllFavoriteRecipes()
    }

    suspend fun deleteRecipes() {
        return recipesDao.deleteAllRecipes()
    }

    fun getRecipeById(id: Int): Flow<Recipe> {
        return recipesDao.getIngredientOfRecipe(id).map { it.toRecipe() }
    }

}