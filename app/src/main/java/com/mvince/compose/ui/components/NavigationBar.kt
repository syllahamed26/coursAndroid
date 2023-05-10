package com.mvince.compose.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mvince.compose.R
import com.mvince.compose.ui.Route

@Composable
fun NavigationBar(navController: NavController) {
    val bottomNavItems = listOf(
        NavigationBarContent(
            name = "Home",
            route = Route.HOME,
            icon = painterResource(id = R.drawable.ic_home),
        ),
        NavigationBarContent(
            name = "Game",
            route = Route.GAME,
            icon = painterResource(id = R.drawable.ic_game),
        ),
        NavigationBarContent(
            name = "Login",
            route = Route.SIGN_IN,
            icon = painterResource(id = R.drawable.ic_login),
        ),
    )

    NavigationBar() {
        var selectedItem by remember { mutableStateOf(0) }

        bottomNavItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.name) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route)
                },
            )
        }
    }
}

data class NavigationBarContent(
    val name: String,
    val route: String,
    val icon: Painter,
)