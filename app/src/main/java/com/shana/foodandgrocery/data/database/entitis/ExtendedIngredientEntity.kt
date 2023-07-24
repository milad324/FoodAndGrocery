package com.shana.foodandgrocery.data.database.entitis

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExtendedIngredientEntity(
    val aisle: String?,
    val amount: Double?,
    val consistency: String?,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String?,
    val name: String?,
    val nameClean: String?,
    val original: String?,
    val originalName: String?,
    val unit: String?
) : Parcelable
