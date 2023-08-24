package com.shana.foodandgrocery.ui.screens.planner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shana.foodandgrocery.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlannerViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    var plannerList = repository.local.readPlanner().asLiveData()
}