package com.shana.foodandgrocery


import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope

import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.shana.foodandgrocery.config.MainScreen
import com.shana.foodandgrocery.data.networkMonitoring.NetworkMonitor
import com.shana.foodandgrocery.ui.screens.favorite.navigation.navigateFavorite
import com.shana.foodandgrocery.ui.screens.home.navigation.navigateHome
import com.shana.foodandgrocery.ui.screens.planner.navigation.navigatePlanner
import com.shana.foodandgrocery.ui.screens.search.navigation.navigateSearch
import com.shana.foodandgrocery.ui.screens.shopping.navigation.navigateShopping
import com.shana.foodandgrocery.util.Constants.Companion.START_DESTINATION
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


@Composable
fun rememberFoodAndGroceryState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    networkMonitor: NetworkMonitor
): FoodAndGroceryState {
    return remember(
        navController,
        coroutineScope,
        networkMonitor
    ) {
        FoodAndGroceryState(navController, coroutineScope, networkMonitor)
    }
}

@Stable
class FoodAndGroceryState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    networkMonitor: NetworkMonitor,
    val startDestination: String = START_DESTINATION
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination
    val currentTopLevelDestination: MainScreen?
        @Composable get() = when (currentDestination?.route) {
            MainScreen.MainMainScreen.route -> MainScreen.MainMainScreen
            MainScreen.ShoppingMainScreen.route -> MainScreen.ShoppingMainScreen
            MainScreen.FavoriteMainScreen.route -> MainScreen.FavoriteMainScreen
            MainScreen.Planner.route -> MainScreen.Planner
            MainScreen.SearchFilterMainScreen.route -> MainScreen.SearchFilterMainScreen
            else -> null
        }
    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    /**
     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
     * route.
     */
    val topLevelDestinations: List<MainScreen> = MainScreen.values().asList()
    fun navigateToTopLevelDestination(topLevelDestination: MainScreen) {
        trace("Navigation: ${topLevelDestination}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
            when (topLevelDestination) {
                MainScreen.MainMainScreen -> navController.navigateHome(topLevelNavOptions)
                MainScreen.ShoppingMainScreen -> navController.navigateShopping(topLevelNavOptions)
                MainScreen.FavoriteMainScreen -> navController.navigateFavorite(topLevelNavOptions)
                MainScreen.Planner -> navController.navigatePlanner(topLevelNavOptions)
                MainScreen.SearchFilterMainScreen -> navController.navigateSearch(topLevelNavOptions)
                //must be move
                // Screen.ShowRecipe -> navController.navigateSearch(topLevelNavOptions)
            }
        }
    }

}