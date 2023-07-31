package com.shana.foodandgrocery.data.network

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.shana.foodandgrocery.data.MealAndDietType
import com.shana.foodandgrocery.data.Repository
import com.shana.foodandgrocery.data.database.entitis.RecipesEntity
import com.shana.foodandgrocery.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)

class RecipeRemoteMediator(
    private val repository: Repository,
    private val dataStoreRepository: Flow<MealAndDietType>
) : RemoteMediator<Int, RecipesEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RecipesEntity>
    ): MediatorResult {
        return try {

            var recipeCount = repository.local.getRecipeCount()
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    (recipeCount / state.config.pageSize) + 1
                }
            }
            if (loadType == LoadType.REFRESH) {
                repository.local.deleteRecipes()
            }
//            dataStoreRepository.collect { item ->
//                val queries =
//                    applyQueries(
//                        loadKey,
//                        state.config.pageSize,
//                        item.selectedDietType,
//                        item.selectedMealType
//                    )
//                Log.d("milad", queries.toString())
//                val result = repository.remote.getRecipes(queries)
//                Log.d("milad", result.toString())
//                val foodEntity = result.recipeDtos
//                repository.local.insertRecipeDto(foodEntity)
//
//                MediatorResult.Success(endOfPaginationReached = result.recipeDtos.isEmpty())
//            }
            val queries =
                    applyQueries(
                        loadKey,
                        state.config.pageSize,
                       Constants.DEFAULT_DIET_TYPE,
                        Constants.DEFAULT_MEAL_TYPE
                    )
                Log.d("milad", queries.toString())
                val result = repository.remote.getRecipes(queries)
                Log.d("milad", result.toString())
                val foodEntity = result.recipeDtos
                repository.local.insertRecipeDto(foodEntity)

                MediatorResult.Success(endOfPaginationReached = result.recipeDtos.isEmpty())


            MediatorResult.Success(endOfPaginationReached = false)

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}

fun applyQueries(
    page: Int,
    perPage: Int,
    selectedDietType: String,
    selectedMealType: String,
): HashMap<String, String> {
    val queries: HashMap<String, String> = HashMap()
    queries[Constants.QUERY_NUMBER] = perPage.toString()
    queries[Constants.QUERY_OFFSET] = ((page - 1) * perPage).toString()
    queries[Constants.QUERY_API_KEY] = "43ec35ab36ba47af89235ed924740d74"
    queries[Constants.QUERY_TYPE] = selectedMealType
    queries[Constants.QUERY_DIET] = selectedDietType
    queries[Constants.QUERY_ADD_RECIPE_INFORMATION] = "true"
    queries[Constants.QUERY_FILL_INGREDIENTS] = "true"
    return queries
}