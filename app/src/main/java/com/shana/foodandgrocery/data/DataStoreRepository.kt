package com.shana.foodandgrocery.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.shana.foodandgrocery.util.Constants
import com.shana.foodandgrocery.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.shana.foodandgrocery.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.shana.foodandgrocery.util.Constants.Companion.PREFERENCES_DIET_TYPE
import com.shana.foodandgrocery.util.Constants.Companion.PREFERENCES_MEAL_TYPE
import com.shana.foodandgrocery.util.Constants.Companion.PREFERENCES_NAME
import com.shana.foodandgrocery.util.Constants.Companion.PREFERENCES_QUERY_SEARCH
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

@Singleton
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {
    private object PreferenceKeys {
        val selectedMealType = stringPreferencesKey(PREFERENCES_MEAL_TYPE)
        val selectedDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
        val selectedQuery = stringPreferencesKey(PREFERENCES_QUERY_SEARCH)
    }

    suspend fun saveMealAndDietType(
        mealType: String,
        dietType: String,
        selectedQuery: String
    ) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.selectedMealType] = mealType
            preferences[PreferenceKeys.selectedDietType] = dietType
            preferences[PreferenceKeys.selectedQuery] = selectedQuery
        }
    }

    val readMealAndDietType: Flow<MealAndDietType>
        get() = context.dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val selectedMealType = preferences[PreferenceKeys.selectedMealType] ?: DEFAULT_MEAL_TYPE
            val selectedDietType = preferences[PreferenceKeys.selectedDietType] ?: DEFAULT_DIET_TYPE
            val selectedQuery = preferences[PreferenceKeys.selectedQuery] ?: ""

            MealAndDietType(
                selectedMealType,
                selectedDietType,
                selectedQuery
            )
        }
    suspend fun applyQueries(
        page: Int,
        perPage: Int,
    ): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[Constants.QUERY_SEARCH] = getMyCustomData(PreferenceKeys.selectedQuery)
        queries[Constants.QUERY_TYPE] = getMyCustomData(PreferenceKeys.selectedMealType)
        queries[Constants.QUERY_DIET] = getMyCustomData(PreferenceKeys.selectedDietType)
        queries[Constants.QUERY_NUMBER] = perPage.toString()
        queries[Constants.QUERY_OFFSET] = ((page - 1) * perPage).toString()
        queries[Constants.QUERY_API_KEY] = "43ec35ab36ba47af89235ed924740d74"
        //queries[Constants.QUERY_API_KEY] = "a2b92c218629402a8cb478e61e331114"
       // queries[Constants.QUERY_API_KEY] = "d11026d278134398b1bf50c0f7cad410"
        queries[Constants.QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[Constants.QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }

    private suspend fun getMyCustomData(key: Preferences.Key<String>): String {
        val preferences = context.dataStore.data.first()
        return preferences[key] ?: ""
    }
}


data class MealAndDietType(
    val selectedMealType: String,
    val selectedDietType: String,
    val selectedQuery: String,
)