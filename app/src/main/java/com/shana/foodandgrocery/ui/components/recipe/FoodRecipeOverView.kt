package com.shana.foodandgrocery.ui.components.recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.text.parseAsHtml
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shana.foodandgrocery.R
import com.shana.foodandgrocery.data.mappers.toFavoriteRecipeEntity
import com.shana.foodandgrocery.viewModels.FoodRecipeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodRecipeOverview(recipeViewModel: FoodRecipeViewModel = hiltViewModel()) {
    var recipe = recipeViewModel.recipe.observeAsState().value
    var isFavorite = recipeViewModel.isFavorite.observeAsState().value
    recipe?.let {
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = recipe.title,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    IconButton(onClick = { recipeViewModel.handleFavoriteRecipe(recipe.toFavoriteRecipeEntity()) }) {
                        if(isFavorite==true){
                            Icon(
                                painter = painterResource(id = R.drawable.ic_filled_star),
                                contentDescription = "",
                                tint = Color.Yellow

                            )
                        }else{
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_star_border_24),
                                contentDescription = ""
                            )
                        }

                    }
                })
        }) { contentPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(contentPadding)
            ) {
                Box(modifier = Modifier.height(250.dp)) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(recipe.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = recipe.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(end = 16.dp, bottom = 16.dp),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Row {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_like),
                                    contentDescription = stringResource(R.string.like)

                                )
                                Text(text = recipe.aggregateLikes.toString())
                            }
                            Spacer(modifier = Modifier.size(16.dp))
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_clock),
                                    contentDescription = stringResource(R.string.ready_to_minutes)
                                )
                                Text(text = recipe.readyInMinutes.toString())
                            }
                            Spacer(modifier = Modifier.size(16.dp))
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
                                Text(
                                    text = stringResource(R.string.vegan),
                                    color = color
                                )
                            }
                        }
                    }

                }
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = recipe.summary.parseAsHtml().toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

    }

}