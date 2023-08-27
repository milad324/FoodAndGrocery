package com.shana.foodandgrocery.ui.screens.planner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.shana.foodandgrocery.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlannerViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    var plannerList = repository.local.readPlanner().asLiveData()

    fun deletePlanner(id: Long) {
        viewModelScope.launch {
            repository.local.deletePlanner(id)
        }
    }
}