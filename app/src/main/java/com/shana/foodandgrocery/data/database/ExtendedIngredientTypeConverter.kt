package com.shana.foodandgrocery.data.database

import androidx.room.TypeConverter
import com.shana.foodandgrocery.models.ExtendedIngredient
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.Type

class ExtendedIngredientTypeConverter {
companion object{
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val type: Type =
        Types.newParameterizedType(List::class.java, ExtendedIngredient::class.java)
    private val adapter: JsonAdapter<List<ExtendedIngredient>> = moshi.adapter(type)
}
    @TypeConverter
    fun extendedIngredientEntityToString(extendedIngredientEntities: List<ExtendedIngredient>): String {
        return adapter.toJson(extendedIngredientEntities)
    }

    @TypeConverter
    fun stringToExtendedIngredientEntity(data: String): List<ExtendedIngredient> {
        return adapter.fromJson(data)!!
    }
}