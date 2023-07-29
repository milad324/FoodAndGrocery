package com.shana.foodandgrocery.data.network

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.shana.foodandgrocery.data.Repository
import com.shana.foodandgrocery.data.database.RecipesDatabase
import com.shana.foodandgrocery.data.database.entitis.RecipesEntity
import com.shana.foodandgrocery.data.mappers.toRecipeEntity
import com.shana.foodandgrocery.util.Constants
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class RecipeRemoteMediator(
    val recipesDatabase: RecipesDatabase,
    val foodRecipesApi: FoodRecipesApi
) : RemoteMediator<Int, RecipesEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RecipesEntity>
    ): MediatorResult {
        return try {
            var recipeCount = recipesDatabase.dao.getRecipeCount()
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    (recipeCount / state.config.pageSize) + 1
                }
            }
            val queries = applyQueries(loadKey, state.config.pageSize)
            val result = foodRecipesApi.getRecipes(queries)
            recipesDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    recipesDatabase.dao.deleteAllRecipes()
                }
                val foodEntity = result.recipeDtos
                recipesDatabase.dao.insertRecipeDto(foodEntity)
            }

            MediatorResult.Success(endOfPaginationReached = result.recipeDtos.isEmpty())

        } catch (e: IOException) {
            Log.d("milad", e.toString())
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            Log.d("milad", e.toString())
            MediatorResult.Error(e)
        } catch (e: Exception) {
            Log.d("milad", e.toString())
            MediatorResult.Error(e)
        }
    }
}


fun applyQueries(page: Int, perPage: Int): HashMap<String, String> {
    val queries: HashMap<String, String> = HashMap()
    queries[Constants.QUERY_NUMBER] = perPage.toString()
    queries[Constants.QUERY_OFFSET] = ((page - 1) * perPage).toString()
    queries[Constants.QUERY_API_KEY] = "43ec35ab36ba47af89235ed924740d74"
    queries[Constants.QUERY_TYPE] = Constants.DEFAULT_MEAL_TYPE
    queries[Constants.QUERY_DIET] = Constants.DEFAULT_DIET_TYPE
    queries[Constants.QUERY_ADD_RECIPE_INFORMATION] = "true"
    queries[Constants.QUERY_FILL_INGREDIENTS] = "true"
    return queries
}