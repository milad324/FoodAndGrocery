package com.shana.foodandgrocery.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shana.foodandgrocery.data.database.entitis.FavoritesEntity
import com.shana.foodandgrocery.data.database.entitis.RecipesEntity
import com.shana.foodandgrocery.util.Constants.Companion.DATABASE_NAME

@Database(
    entities = [RecipesEntity::class, FavoritesEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase : RoomDatabase() {
    abstract val dao: RecipesDao

    companion object {
        var instance: RecipesDatabase? = null
        fun getInstance(context: Context): RecipesDatabase {
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        RecipesDatabase::class.java,
                        DATABASE_NAME
                    ).fallbackToDestructiveMigration().build()
                }
                return instance as RecipesDatabase
            }
        }
    }
}