package com.shana.foodandgrocery.ui.screens.shopping


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.shana.foodandgrocery.R
import com.shana.foodandgrocery.data.database.entitis.ShoppingItemEntity
import com.shana.foodandgrocery.util.Constants

@Composable
fun ShoppingScreen(shoppingViewModel: ShoppingViewModel = hiltViewModel()) {
    val shoppingGroupItems = shoppingViewModel.shoppingGroupItem.observeAsState()
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            shoppingGroupItems.value?.forEach {
                item {
                    ShoppingItemView(it) {
                        shoppingViewModel.deleteShoppingItem(it)
                    }
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(8.dp, 4.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.onPrimary,
        )
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            SubcomposeAsyncImage(
                model = Constants.BASE_IMAGE_URL + shoppingItemEntity.image,
                contentDescription = shoppingItemEntity.name,
                modifier = Modifier.size(80.dp),
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    CircularProgressIndicator()
                } else {
                    SubcomposeAsyncImageContent()
                }
            }
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

}