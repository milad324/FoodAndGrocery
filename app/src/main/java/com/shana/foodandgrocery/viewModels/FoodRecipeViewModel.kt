package com.shana.foodandgrocery.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shana.foodandgrocery.data.Repository
import com.shana.foodandgrocery.models.ExtendedIngredient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FoodRecipeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val repository: Repository
) : ViewModel() {
    private var recipeId: Int? = savedStateHandle[RECIPE_ID_SAVED_STATE_KEY]
    var recipe = repository.local.getRecipeById(recipeId ?: 1).asLiveData()

    val selectedIngredients = mutableStateListOf<ExtendedIngredient>()
    fun handleSelectIngredient(extendedIngredient: ExtendedIngredient) {
        if (selectedIngredients.contains(extendedIngredient)) {
            selectedIngredients.remove(extendedIngredient)
        } else {
            selectedIngredients.add(extendedIngredient)
        }
    }

    fun handleRemoveIngredient(extendedIngredient: ExtendedIngredient) {
        selectedIngredients.remove(extendedIngredient)
    }

    companion object {
        private const val RECIPE_ID_SAVED_STATE_KEY = "recipeId"
    }
}