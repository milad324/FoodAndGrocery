package com.shana.foodandgrocery.data.database.entitis

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shana.foodandgrocery.util.Constants.Companion.EXTENDED_INGREDIENT_TABLE
import kotlinx.parcelize.Parcelize


@Entity(tableName = EXTENDED_INGREDIENT_TABLE)
data class ExtendedIngredientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val idEntity:Long,
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
