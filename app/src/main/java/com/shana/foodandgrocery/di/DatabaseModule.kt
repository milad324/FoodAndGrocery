package com.shana.foodandgrocery.di

import android.content.Context
import com.shana.foodandgrocery.data.database.RecipesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RecipesDatabase = RecipesDatabase.getInstance(context)

    @Provides
    fun provideDao(database: RecipesDatabase) = database.dao

}