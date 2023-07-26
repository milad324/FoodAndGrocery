package com.shana.foodandgrocery.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shana.foodandgrocery.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FoodRecipeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val repository: Repository
) : ViewModel() {
    private var recipeId: Int? = savedStateHandle[RECIPE_ID_SAVED_STATE_KEY]
    var recipe = repository.local.getRecipeById(recipeId ?: 1).asLiveData()

    companion object {
        private const val RECIPE_ID_SAVED_STATE_KEY = "recipeId"
    }
}