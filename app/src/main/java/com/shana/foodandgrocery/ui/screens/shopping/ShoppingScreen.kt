package com.shana.foodandgrocery.ui.screens.shopping


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shana.foodandgrocery.R
import com.shana.foodandgrocery.data.database.entitis.ShoppingItemEntity

@Composable
fun ShoppingScreen(shoppingViewModel: ShoppingViewModel = hiltViewModel()) {
    val shoppingGroupItems = shoppingViewModel.shoppingGroupItem.observeAsState()
    LazyColumn() {
        shoppingGroupItems.value?.forEach {
            item {
                ShoppingItemView(it) {
                    shoppingViewModel.deleteShoppingItem(it)
                }
            }
        }

    }
}


@Composable
fun ShoppingItemView(
    shoppingItemEntity: ShoppingItemEntity,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, color = MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = shoppingItemEntity.name ?: "",
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        FilledIconButton(
            onClick = {
                onDeleteClick()
            },
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = stringResource(
                    R.string.delete,
                ),
                tint = MaterialTheme.colorScheme.surface
            )
        }
    }
}