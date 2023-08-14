package com.shana.foodandgrocery.ui.components.recipe

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.shana.foodandgrocery.R
import com.shana.foodandgrocery.models.ExtendedIngredient
import com.shana.foodandgrocery.util.Constants


@Composable
fun selectedShoppingItem(
    ingredient: ExtendedIngredient,
    handleRemove: (ExtendedIngredient) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 16.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.onPrimary,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            SubcomposeAsyncImage(
                model = Constants.BASE_IMAGE_URL + ingredient.image,
                contentDescription = ingredient.name,
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
                text = ingredient.original!!,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            IconButton(
                onClick = { handleRemove(ingredient) },
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = stringResource(R.string.remove_ingredient),
                    tint = MaterialTheme.colorScheme.error
                )
            }


        }
    }
}


@Preview
@Composable
fun selectedShoppingItemView() {
    selectedShoppingItem(
        ingredient = ExtendedIngredient(
            name = "garlic",
            amount = 1.0,
            consistency = "SOLID",
            image = "garlic.png",
            original = "1 tablespoon Garlic",
            unit = "",
            id = 10L,
            aisle = "alies"
        ), handleRemove = {})
}