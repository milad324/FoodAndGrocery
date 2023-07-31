package com.shana.foodandgrocery.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shana.foodandgrocery.R
import com.shana.foodandgrocery.ui.theme.Purple80
import com.shana.foodandgrocery.ui.theme.PurpleGrey40
import com.shana.foodandgrocery.viewModels.FoodRecipeViewModel
import com.shana.foodandgrocery.viewModels.MealAndDietViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchScreen(
    mealAndDietViewModel: MealAndDietViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(R.string.meal_type),
            style = MaterialTheme.typography.headlineLarge
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            mealAndDietViewModel.mealTypeChips.forEach {
                SuggestionChipEachRow(chip = it, it == mealAndDietViewModel.mealTypeChipState) { chip ->
                    mealAndDietViewModel.mealTypeChipState = chip
                }
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = stringResource(R.string.diet_type),
            style = MaterialTheme.typography.headlineLarge
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            mealAndDietViewModel.dietTypeChips.forEach {
                SuggestionChipEachRow(chip = it, it == mealAndDietViewModel.dietTypeChipState) { chip ->
                    mealAndDietViewModel.dietTypeChipState = chip
                }
            }
        }

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
            Button(onClick = {
                mealAndDietViewModel.saveMealAndDietType()
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Apply")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestionChipEachRow(
    chip: String,
    selected: Boolean,
    onChipState: (String) -> Unit
) {

    SuggestionChip(onClick = {
        if (!selected)
            onChipState(chip)
        else
            onChipState("")
    }, label = {
        Text(text = chip)
    },
        border = SuggestionChipDefaults.suggestionChipBorder(
            borderWidth = 1.dp,
            borderColor = if (selected) Color.Transparent else PurpleGrey40
        ),
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = if (selected) Purple80 else Color.Transparent
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(end = 8.dp)
    )

}

@Preview
@Composable

fun SearchScreenPreview() {
    SearchScreen()
}