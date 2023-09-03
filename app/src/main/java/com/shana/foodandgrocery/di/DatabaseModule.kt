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
    fun provideRecipesDao(database: RecipesDatabase) = database.recipesDao
    @Provides
    fun provideShoppingItemDao(database: RecipesDatabase) = database.shoppingItemDao
    @Provides
    fun providePlannerDao(database: RecipesDatabase) = database.plannerDao
    @Provides
    fun provideFavoriteRecipeDao(database: RecipesDatabase) = database.favoriteRecipeDao
    @Provides
    fun provideExtendedIngredientDao(database: RecipesDatabase) = database.extendedIngredientDao


}