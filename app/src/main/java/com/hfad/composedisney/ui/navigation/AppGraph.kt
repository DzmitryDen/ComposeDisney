package com.hfad.composedisney.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hfad.composedisney.ui.screens.details.DetailsScreen
import com.hfad.composedisney.ui.screens.homeScreen.HomeScreen

private const val ID = "id"

@Composable
fun AppGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = ScreenRoute.Home.route) {
        composable(ScreenRoute.Home.route) {
            HomeScreen(navController)
        }
        composable(
            ScreenRoute.Details.route,
            arguments = listOf(navArgument(ID) { type = NavType.IntType })
        ) { backStack ->
            backStack.arguments?.getInt(ID)?.let { id ->
                DetailsScreen(navController, id)
            }
        }
    }
}