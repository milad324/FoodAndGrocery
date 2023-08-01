package com.shana.foodandgrocery.di

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.shana.foodandgrocery.data.DataStoreRepository
import com.shana.foodandgrocery.data.Repository
import com.shana.foodandgrocery.data.database.entitis.RecipesEntity
import com.shana.foodandgrocery.data.network.RecipeRemoteMediator
import com.shana.foodandgrocery.util.Constants.Companion.DEFAULT_RECIPES_NUMBER
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PagerModule {
    @OptIn(ExperimentalPagingApi::class)
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    @Provides
    @Singleton
    fun provideFoodRecipePager(
        repository: Repository,
        dataStoreRepository: DataStoreRepository
    ): Pager<Int, RecipesEntity> {
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_RECIPES_NUMBER, initialLoadSize = 50),
            remoteMediator = RecipeRemoteMediator(repository, dataStoreRepository),
            pagingSourceFactory = {
                repository.local.readRecipes()
            }
        )
    }
}