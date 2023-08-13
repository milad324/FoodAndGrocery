package com.shana.foodandgrocery.ui.components.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shana.foodandgrocery.R
import com.shana.foodandgrocery.config.Screen
import com.shana.foodandgrocery.models.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodRecipeView(recipe: Recipe, onRecipeClick: (Long) -> Unit, showAsGrid: Boolean = false) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 0.dp, shape = RoundedCornerShape(16.dp), clip = true)
            .padding(4.dp),
        shape = if (showAsGrid) RoundedCornerShape(
            topStart = 16.dp,
            bottomEnd = 16.dp
        ) else RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.primary
        ),
        onClick = {
            onRecipeClick(recipe.recipeId)
        }
    ) {
        if (showAsGrid) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(recipe.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = recipe.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    horizontalArrangement = Arrangement.SpaceAround,

                    ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_like),
                            contentDescription = stringResource(R.string.like)

                        )
                        Text(text = recipe.aggregateLikes.toString())
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_clock),
                            contentDescription = stringResource(R.string.ready_to_minutes)
                        )
                        Text(text = recipe.readyInMinutes.toString())
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var color = MaterialTheme.colorScheme.onErrorContainer
                        if (recipe.vegan)
                            color = Color.Green
                        Icon(
                            painter = painterResource(id = R.drawable.ic_vegan),
                            contentDescription = stringResource(R.string.vegan),
                            tint = color
                        )
                        Text(text = stringResource(R.string.vegan), color = color)
                    }
                }
            }
            Text(
                text = recipe.title,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2, textAlign = TextAlign.Center, modifier = Modifier.padding(4.dp)
            )
        } else {
            Row() {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(recipe.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = recipe.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.width(150.dp)
                )
                Column() {
                    Text(
                        text = recipe.title, modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(color = Color.Transparent),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,

                        ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_like),
                                contentDescription = stringResource(R.string.like)

                            )
                            Text(text = recipe.aggregateLikes.toString())
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_clock),
                                contentDescription = stringResource(R.string.ready_to_minutes)
                            )
                            Text(text = recipe.readyInMinutes.toString())
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            var color = MaterialTheme.colorScheme.onErrorContainer
                            if (recipe.vegan)
                                color = Color.Green
                            Icon(
                                painter = painterResource(id = R.drawable.ic_vegan),
                                contentDescription = stringResource(R.string.vegan),
                                tint = color
                            )
                            Text(text = stringResource(R.string.vegan), color = color)
                        }
                    }
                }

            }
        }


    }
}