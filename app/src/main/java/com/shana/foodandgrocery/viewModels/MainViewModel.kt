package com.shana.foodandgrocery.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.shana.foodandgrocery.data.database.entitis.RecipesEntity
import com.shana.foodandgrocery.data.mappers.toRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    pager: Pager<Int, RecipesEntity>

) : ViewModel() {
    val recipesFlow = pager.flow.map { pagingData ->
        pagingData.map {
            it.toRecipe()
        }
    }.cachedIn(viewModelScope)


}