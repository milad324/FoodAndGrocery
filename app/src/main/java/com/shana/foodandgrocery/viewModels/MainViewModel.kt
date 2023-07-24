package com.shana.foodandgrocery.viewModels

import android.app.Application

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.shana.foodandgrocery.data.Repository
import com.shana.foodandgrocery.data.database.entitis.RecipesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val application: Application,
    private val repository: Repository,
    pager: Pager<Int, RecipesEntity>

) : ViewModel() {
    val recipesEntityFlow = pager.flow.map { pagingData ->
        pagingData.map {
            it.toRecipeModel()
        }
    }.cachedIn(viewModelScope)

    suspend fun getRecipeById(id: String): RecipesEntity {
        return repository.local.getRecipeById(id.toInt())
    }

}