package com.mvince.compose.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mvince.compose.ui.details.DetailsScreen
import com.mvince.compose.ui.finalscore.FinalScoreScreen
import com.mvince.compose.ui.game.GameScreen
import com.mvince.compose.ui.home.HomeScreen
import com.mvince.compose.ui.signin.SignInScreen
import com.mvince.compose.ui.signup.SignUpScreen
import com.mvince.compose.ui.users.UsersScreen

@Composable
fun ComposeApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.GAME
    ) {
        composable(Route.SIGN_IN) {
            SignInScreen(navController = navController)
        }
        composable(Route.SIGN_UP) {
            SignUpScreen(navController = navController)
        }
        composable(Route.HOME) {
            HomeScreen()
        }
        composable(Route.GAME) {
            GameScreen(navController = navController)
        }
        composable(Route.FINAL_SCORE) {
            FinalScoreScreen()
        }
        composable(
            route = "${Route.DETAIL}/{${Argument.USERNAME}}",
            arguments = listOf(
                navArgument(Argument.USERNAME) {
                    type = NavType.StringType
                }
            ),
        ) {
            DetailsScreen(navController)
        }
    }
}

object Route {
    const val USER = "user"
    const val DETAIL = "detail"
    const val SIGN_UP = "signup"
    const val SIGN_IN = "signin"
    const val GAME = "game"
    const val HOME = "home"
    const val FINAL_SCORE = "finalscore"
}

object Argument {
    const val USERNAME = "username"
}