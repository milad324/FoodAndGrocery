package com.shana.foodandgrocery.ui.components.recipe

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.shana.foodandgrocery.models.ExtendedIngredient
import com.shana.foodandgrocery.util.Constants.Companion.BASE_IMAGE_URL


@Composable
fun IngredientItemView(
    ingredient: ExtendedIngredient,
    isSelected: Boolean,
    handleSelect: (ExtendedIngredient) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { handleSelect(ingredient) }
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.onPrimary,


            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
                )
        ) {

            SubcomposeAsyncImage(
                model = BASE_IMAGE_URL + ingredient.image,
                contentDescription = ingredient.name,
                modifier = Modifier.size(130.dp),
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    CircularProgressIndicator()
                } else {
                    SubcomposeAsyncImageContent()
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .height(130.dp)
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = ingredient.name!!, style = MaterialTheme.typography.titleLarge)
                Text(
                    text = ingredient.amount!!.toString() + " " + ingredient.unit!!,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(text = ingredient.consistency!!, style = MaterialTheme.typography.bodySmall)
                Text(text = ingredient.original!!, style = MaterialTheme.typography.bodySmall)
            }
        }
    }

}