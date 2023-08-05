package com.shana.foodandgrocery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.shana.foodandgrocery.ui.screens.MainScreen
import com.shana.foodandgrocery.ui.theme.FoodAndGroceryTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAndGroceryTheme {
                // A surface container using the 'background' color from the theme
                   MainScreen()
            }
        }
    }
}

