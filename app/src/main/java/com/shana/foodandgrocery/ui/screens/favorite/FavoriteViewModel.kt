package com.shana.foodandgrocery.ui.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shana.foodandgrocery.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val favoriteRecipeList = repository.local.readFavoriteRecipes().asLiveData()
}