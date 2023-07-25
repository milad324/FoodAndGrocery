@file:OptIn(ExperimentalFoundationApi::class)

package com.shana.foodandgrocery.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.shana.foodandgrocery.models.Recipe
import com.shana.foodandgrocery.ui.components.recipe.InstructionView
import com.shana.foodandgrocery.ui.components.recipe.FoodRecipeOverview
import com.shana.foodandgrocery.ui.components.recipe.IngredientItemView
import com.shana.foodandgrocery.viewModels.MainViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ShowRecipe(recipeId: String?) {
    var mainViewModel = hiltViewModel<MainViewModel>()
    var recipe = remember {
        mutableStateOf<Recipe?>(null)
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
                        FoodRecipeOverview(recipe = recipe.value!!)
                    }
                }

                1 -> {
                    if (recipe.value != null) {
                        LazyColumn() {
                            recipe.value!!.extendedIngredients.forEach { item ->
                                item {
                                    IngredientItemView(ingredient = item)
                                }
                            }

                        }

                    }
                }

                2 -> {
                    if (recipe.value != null) {
                        InstructionView(recipe = recipe.value!!)
                    }
                }
            }

        }
    }
}



