@file:OptIn(ExperimentalFoundationApi::class)

package com.shana.foodandgrocery.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.shana.foodandgrocery.R
import com.shana.foodandgrocery.data.database.entitis.RecipesEntity
import com.shana.foodandgrocery.viewModels.MainViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ShowRecipe(recipeId: String?) {
    var mainViewModel = hiltViewModel<MainViewModel>()
    var recipe = remember {
        mutableStateOf<RecipesEntity?>(null)
    }
    val tabData = listOf("OVERVIEW", "INGREDIENTS", "INSTRUCTIONS")
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
    )
    LaunchedEffect(key1 = recipe) {
        mainViewModel.getRecipeById(recipeId!!).let {
            recipe.value = it
        }
    }
    Column() {
        TabRow(
            selectedTabIndex = (pagerState.currentPage),
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp), containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            tabData.forEachIndexed { index, data ->
                val selected = pagerState.currentPage == index
                Tab(
                    selected = selected,
                    onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                    modifier = Modifier,
                    enabled = true,
                    interactionSource = MutableInteractionSource(),
                    selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSecondary,
                    content = {
                        Text(
                            text = data, fontWeight = if (selected) {
                                FontWeight.Bold
                            } else {
                                FontWeight.Normal
                            }
                        )
                    }
                )
            }
        }
        HorizontalPager(state = pagerState, count = tabData.size) { page ->
            when (page) {
                0 -> {
                    if (recipe.value != null) {
                        Column() {
                            Box(modifier = Modifier.height(250.dp)) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(recipe.value!!.image)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = recipe.value!!.title,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxSize()
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(end = 16.dp, bottom = 16.dp),
                                    contentAlignment = androidx.compose.ui.Alignment.BottomEnd
                                ) {
                                    Row {
                                        Column(
                                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_like),
                                                contentDescription = stringResource(R.string.like)

                                            )
                                            Text(text = recipe.value!!.aggregateLikes.toString())
                                        }
                                        Spacer(modifier = Modifier.size(16.dp))
                                        Column(
                                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_clock),
                                                contentDescription = stringResource(R.string.ready_to_minutes)
                                            )
                                            Text(text = recipe.value!!.readyInMinutes.toString())
                                        }
                                        Spacer(modifier = Modifier.size(16.dp))
                                        Column(
                                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                                        ) {
                                            var color = MaterialTheme.colorScheme.onErrorContainer
                                            if (recipe.value!!.vegan)
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
                                text = recipe.value!!.title,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = recipe.value!!.summary,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                    } else {
                        CircularProgressIndicator()
                    }
                }

                1 -> {}
                2 -> {}
            }

        }
    }
}



