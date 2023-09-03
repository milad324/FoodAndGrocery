package com.shana.foodandgrocery.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shana.foodandgrocery.data.database.entitis.ExtendedIngredientEntity

@Dao
interface ExtendedIngredientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExtendedIngredient(extendedIngredient: ExtendedIngredientEntity): Long

    @Query("DELETE FROM extended_ingredient_table")
    suspend fun deleteAllExtendIngredient()
}