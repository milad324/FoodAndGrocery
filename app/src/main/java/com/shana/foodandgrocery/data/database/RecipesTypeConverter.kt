package com.shana.foodandgrocery.data.database

import androidx.room.TypeConverter
import com.shana.foodandgrocery.data.database.entitis.ExtendedIngredientEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.Type

class RecipesTypeConverter {
companion object{
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val type: Type =
        Types.newParameterizedType(List::class.java, ExtendedIngredientEntity::class.java)
    private val adapter: JsonAdapter<List<ExtendedIngredientEntity>> = moshi.adapter(type)
}


    @TypeConverter
    fun extendedIngredientEntityToString(extendedIngredientEntities: List<ExtendedIngredientEntity>): String {

        return adapter.toJson(extendedIngredientEntities)
    }

    @TypeConverter
    fun stringToExtendedIngredientEntity(data: String): List<ExtendedIngredientEntity> {
        return adapter.fromJson(data)!!
    }
}