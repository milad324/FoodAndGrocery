package com.shana.foodandgrocery.data.database.entitis

import androidx.room.Entity
import com.shana.foodandgrocery.util.Constants.Companion.SHOPPING_LIST_TABLE


@Entity(tableName = SHOPPING_LIST_TABLE, primaryKeys = ["recipeId", "ingredientId"])
data class ShoppingItemEntity(
    val recipeId: Int,
    val recipeName: String,
    val ingredientId: Long,
    val idEntity: Int,
    val aisle: String?,
    val amount: Double?,
    val consistency: String?,
    val image: String?,
    val name: String?,
    val nameClean: String?,
    val original: String?,
    val originalName: String?,
    val unit: String?
)
