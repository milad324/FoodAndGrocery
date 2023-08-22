package com.shana.foodandgrocery.data.database.entitis

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shana.foodandgrocery.util.Constants.Companion.PLANNER_TABLE


@Entity(tableName = PLANNER_TABLE)
data class PlannerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var recipeName: String,
    var recipeId: Long,
    var mealType: String,
    var cookDate: Long,
    var img: String
)