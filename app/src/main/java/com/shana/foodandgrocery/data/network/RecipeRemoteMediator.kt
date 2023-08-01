package com.shana.foodandgrocery.data.network

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.shana.foodandgrocery.data.DataStoreRepository
import com.shana.foodandgrocery.data.MealAndDietType
import com.shana.foodandgrocery.data.Repository
import com.shana.foodandgrocery.data.database.entitis.RecipesEntity
import com.shana.foodandgrocery.util.Constants
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.coroutines.suspendCoroutine

@OptIn(ExperimentalPagingApi::class)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)

class RecipeRemoteMediator(
    private val repository: Repository,
    private val dataStoreRepository: DataStoreRepository
) : RemoteMediator<Int, RecipesEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RecipesEntity>
    ): MediatorResult {
        return try {
            Log.d("milad", "called")
            val lastItem = state.lastItemOrNull()
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> null
                LoadType.APPEND -> {
                    (repository.local.getRecipeCount() / state.config.pageSize) + 1
                }
            }
            if (loadType == LoadType.REFRESH) {
                repository.local.clearRecipeWithIngredients()
            }
            if (loadKey != null && lastItem == null) {
                val queries = dataStoreRepository.applyQueries(
                    loadKey,
                    state.config.pageSize
                )
                Log.d("milad", queries.toString())
                val result = repository.remote.getRecipes(queries)
                Log.d("milad", result.toString())
                val foodEntity = result.recipeDtos
                repository.local.insertRecipeDto(foodEntity)
                MediatorResult.Success(endOfPaginationReached = foodEntity.size < state.config.pageSize)
            }
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

