package com.shana.foodandgrocery.ui.screens.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.shana.foodandgrocery.data.Repository
import com.shana.foodandgrocery.data.database.entitis.ShoppingItemEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val shoppingGroupItem = repository.local.readShoppingList().asLiveData()

    fun deleteShoppingItem(shoppingItem: ShoppingItemEntity) {
        viewModelScope.launch {
            repository.local.deleteShoppingItem(shoppingItem)
        }
    }
}