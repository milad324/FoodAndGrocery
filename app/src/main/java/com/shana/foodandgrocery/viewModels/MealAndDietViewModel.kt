package com.shana.foodandgrocery.viewModels

import androidx.compose.runtime.mutableStateOf
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
    val selectedMealAndDiet = dataStoreRepository.readMealAndDietType.asLiveData()
    var mealTypeChipState = mutableStateOf(selectedMealAndDiet.value?.selectedMealType)
    var dietTypeChipState =mutableStateOf(selectedMealAndDiet.value?.selectedDietType)



    fun saveMealAndDietType() {
        viewModelScope.launch {
            dataStoreRepository.saveMealAndDietType(
                mealTypeChipState.value,
                dietTypeChipState.value
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

