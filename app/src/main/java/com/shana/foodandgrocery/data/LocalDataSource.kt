package com.shana.foodandgrocery.data

import androidx.paging.PagingSource
import com.shana.foodandgrocery.data.database.ExtendedIngredientDao
import com.shana.foodandgrocery.data.database.FavoriteRecipeDao
import com.shana.foodandgrocery.data.database.PlannerDao
import com.shana.foodandgrocery.data.database.RecipesDao
import com.shana.foodandgrocery.data.database.ShoppingItemDao
import com.shana.foodandgrocery.data.database.entitis.FavoriteRecipeEntity
import com.shana.foodandgrocery.data.database.entitis.PlannerEntity
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
    private val recipesDao: RecipesDao,
    private val shoppingItemDao: ShoppingItemDao,
    private val plannerDao: PlannerDao,
    private val favoriteRecipeDao: FavoriteRecipeDao,
    private val extendedIngredientDao: ExtendedIngredientDao
) {

    suspend fun insertPlanner(planner: PlannerEntity) {
        return plannerDao.insertPlanner(planner)
    }

    fun readPlanner(): Flow<List<PlannerEntity>> {
        return plannerDao.readPlanner()
    }

    fun readRecipes(): PagingSource<Int, RecipesEntity> {
        return recipesDao.readRecipes()
    }

    suspend fun deleteShoppingItem(shoppingItemEntity: ShoppingItemEntity) {
        return shoppingItemDao.deleteShoppingItem(shoppingItemEntity)
    }

    fun checkRecipeIsFavorite(id: Long): Flow<Boolean> {
        return favoriteRecipeDao.checkRecipeIsFavorite(id)
    }

    fun readFavoriteRecipes(): Flow<List<Recipe>> {
        return favoriteRecipeDao.readFavoriteRecipes()
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
        favoriteRecipeDao.insertFavoriteRecipe(favoriteRecipeEntity)
    }


    suspend fun deleteFavoriteRecipe(favoriteRecipeEntity: FavoriteRecipeEntity) {
        favoriteRecipeDao.deleteFavoriteRecipe(favoriteRecipeEntity)
    }

    suspend fun deleteAllFavoriteRecipes() {
        favoriteRecipeDao.deleteAllFavoriteRecipes()
    }

    suspend fun insertRecipeDto(recipesDto: List<RecipeDto>) {
        var recipes = mutableListOf<RecipesEntity>()
        var recipeExtendedIngredientCrossRefs =
            mutableListOf<RecipeExtendedIngredientCrossRefEntity>()
        recipesDto.forEach { item ->
            recipes.add(item.toRecipeEntity())
            item.extendedIngredientDtos.forEach { ing ->
                var id =
                    extendedIngredientDao.insertExtendedIngredient(ing.toExtendedIngredientEntity())
                recipeExtendedIngredientCrossRefs.add(
                    RecipeExtendedIngredientCrossRefEntity(
                        recipeId = item.id,
                        id = id
                    )
                )
            }
        }
        recipesDao.upsertRecipes(recipes)
        recipesDao.upsertRecipeExtendedIngredientCrossRefs(recipeExtendedIngredientCrossRefs)

    }

    suspend fun clearRecipeWithIngredients() {
        recipesDao.deleteAllRecipeExtendedIngredient()
        extendedIngredientDao.deleteAllExtendIngredient()
        return recipesDao.deleteAllRecipes()
    }

    fun getRecipeById(id: Long): Flow<Recipe> {
        return recipesDao.getIngredientOfRecipe(id).map { it.toRecipe() }
    }

    suspend fun getRecipeCount(): Int {
        return recipesDao.getRecipeCount()
    }

    suspend fun upsertShoppingList(shoppingList: List<ShoppingItemEntity>) {
        return shoppingItemDao.upsertShoppingList(shoppingList)
    }

    fun readShoppingList(): Flow<List<ShoppingItemEntity>> {
        return shoppingItemDao.readShoppingList()
    }

}