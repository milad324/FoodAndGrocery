package com.shana.foodandgrocery.ui.components.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shana.foodandgrocery.R
import com.shana.foodandgrocery.models.Recipe
import com.shana.foodandgrocery.util.TimeUtil.Companion.toMillis
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AddToPlanner(recipe: Recipe) {
    val dateTime = LocalDateTime.now()
    val mealType =
        arrayOf("breakfast", "brunch", "elevenses", "lunch", "tea", "supper", "dinner")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(mealType[0]) }
    var datePickState = remember {
        DatePickerState(
            yearRange = (2023..2024),
            initialSelectedDateMillis = dateTime.toMillis(),
            initialDisplayMode = DisplayMode.Input,
            initialDisplayedMonthMillis = null
        )
    }
    Column(modifier = Modifier.fillMaxWidth()) {

        DatePicker(state = datePickState, title = null)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },

            ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),

                readOnly = true,
                value = selectedText,
                onValueChange = { },
                label = { Text(stringResource(R.string.meal)) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = MaterialTheme.colorScheme.background),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                mealType.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedText = selectionOption
                            expanded = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(text = stringResource(R.string.add_to_planner))
        }
    }
}