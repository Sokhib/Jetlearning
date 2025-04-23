package com.example.jetlearning

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

/**
 * Navigation routes for the application
 */
sealed class Screen(val route: String) {
    object Form : Screen("form_screen")
    object Details : Screen("details_screen")
}

/**
 * Navigation graph for the application
 */
@Composable
fun NavGraph(navController: NavHostController) {
    // Create a shared ViewModel instance
    val userViewModel: UserViewModel = viewModel()

    // Animation duration
    val animDuration = 300

    NavHost(
        navController = navController,
        startDestination = Screen.Form.route
    ) {
        // Form Screen
        composable(
            route = Screen.Form.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animDuration)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(animDuration)
                )
            }
        ) {
            FormScreen(
                viewModel = userViewModel,
                onNavigateToDetails = {
                    navController.navigate(Screen.Details.route)
                }
            )
        }

        // Details Screen
        composable(
            route = Screen.Details.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(animDuration)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animDuration)
                )
            }
        ) {
            DetailsScreen(
                viewModel = userViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}