package com.shana.foodandgrocery.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.shana.foodandgrocery.data.database.entitis.ShoppingItemEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ShoppingItemDao {
    @Upsert
    suspend fun upsertShoppingList(shoppingList: List<ShoppingItemEntity>)

    @Query("SELECT * FROM shopping_list_table")
    fun readShoppingList(): Flow<List<ShoppingItemEntity>>


    @Delete
    suspend fun deleteShoppingItem(shoppingItemEntity:ShoppingItemEntity)
}