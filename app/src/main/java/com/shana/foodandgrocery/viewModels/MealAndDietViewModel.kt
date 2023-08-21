package com.shana.foodandgrocery.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.shana.foodandgrocery.data.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MealAndDietViewModel @Inject constructor(private var dataStoreRepository: DataStoreRepository) :
    ViewModel() {
    var mealTypeChipState = mutableStateOf("")
    var dietTypeChipState = mutableStateOf("")
    var query =  mutableStateOf("")

    val job = viewModelScope.launch {
        dataStoreRepository.readMealAndDietType.collect {
            mealTypeChipState.value = it.selectedMealType
            dietTypeChipState.value = it.selectedDietType
            query.value =it.selectedQuery
        }
    }
    fun saveMealAndDietType() {
        viewModelScope.launch {
            dataStoreRepository.saveMealAndDietType(
                mealTypeChipState.value,
                dietTypeChipState.value,
                query.value
            )
        }
    }
    val mealTypeChips = listOf(
        "main course",
        "side dish",
        "dessert",
        "appetizer",
        "salad",
        "bread",
        "breakfast",
        "soup",
        "beverage",
        "sauce",
        "marinade",
        "fingerfood",
        "snack",
        "drink",
    )
    val dietTypeChips =
        listOf(
            "Gluten Free",
            "Ketogenic",
            "Vegetarian",
            "Lacto-Vegetarian",
            "Ovo-Vegetarian",
            "Vegan",
            "Pescetarian",
            "Paleo",
            "Primal",
            "Low FODMAP",
            "Whole30",
        )
}

