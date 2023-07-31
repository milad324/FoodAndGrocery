package com.shana.foodandgrocery.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.shana.foodandgrocery.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.shana.foodandgrocery.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.shana.foodandgrocery.util.Constants.Companion.PREFERENCES_DIET_TYPE
import com.shana.foodandgrocery.util.Constants.Companion.PREFERENCES_MEAL_TYPE
import com.shana.foodandgrocery.util.Constants.Companion.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)
@Singleton
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context)  {
    private object PreferenceKeys {
        val selectedMealType = stringPreferencesKey(PREFERENCES_MEAL_TYPE)
        val selectedDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
    }
    suspend fun saveMealAndDietType(
        mealType: String,
        dietType: String,
    ) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.selectedMealType] = mealType
            preferences[PreferenceKeys.selectedDietType] = dietType
        }
    }


    val readMealAndDietType: Flow<MealAndDietType>
        get() = context.dataStore.data.catch {
            exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
            val selectedMealType = preferences[PreferenceKeys.selectedMealType] ?: DEFAULT_MEAL_TYPE
            val selectedDietType = preferences[PreferenceKeys.selectedDietType] ?: DEFAULT_DIET_TYPE
            MealAndDietType(
                selectedMealType,
                selectedDietType,
            )
        }
}

data class MealAndDietType(
    val selectedMealType: String,
    val selectedDietType: String,
)