package com.shana.foodandgrocery.ui.screens


import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shana.foodandgrocery.FoodAndGroceryState
import com.shana.foodandgrocery.NavGraph
import com.shana.foodandgrocery.config.Screen
import com.shana.foodandgrocery.data.networkMonitoring.NetworkMonitor
import com.shana.foodandgrocery.rememberFoodAndGroceryState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodAndGroceryApp(
    networkMonitor: NetworkMonitor,
    appState: FoodAndGroceryState = rememberFoodAndGroceryState(networkMonitor = networkMonitor)
) {
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomBar(navController = navController)
    }) { contentPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            color = MaterialTheme.colorScheme.background,

            ) {
            NavGraph(navController = navController)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        Screen.MainScreen,
        Screen.SearchFilterScreen,
        Screen.FavoriteScreen,
        Screen.ShoppingScreen,
        Screen.Planner
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Screen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = screen.title
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        label = {
            Text(
                text = screen.title,
                style = MaterialTheme.typography.bodySmall,
                color = if (currentDestination?.hierarchy?.any {
                        it.route == screen.route
                    } == true) LocalContentColor.current else LocalContentColor.current.copy(
                    alpha = ContentAlpha.disabled
                )
            )
        }
    )
}