package com.app.footballappwithjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.footballappwithjetpack.ui.navigation.Screen
import com.app.footballappwithjetpack.ui.screen.about.AboutScreen
import com.app.footballappwithjetpack.ui.screen.detail.DetailScreen
import com.app.footballappwithjetpack.ui.screen.favorite.FavoriteScreen
import com.app.footballappwithjetpack.ui.screen.home.HomeScreen
import com.app.footballappwithjetpack.ui.theme.FootballAppWithJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootballAppWithJetpackTheme(darkTheme = false) {
                EPLNewsScreen()
            }
        }
    }
}

@Composable
fun EPLNewsScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route){
                HomeScreen(
                    innerPadding = innerPadding,
                    navController = navController,
                    navigateToDetail = { club ->
                        navController.navigate(Screen.DetailClub.createRoute(clubId = club))
                    }
                )
            }
            composable(Screen.About.route){
                AboutScreen(innerPadding = innerPadding)
            }
            composable(Screen.FavoriteClub.route){
                FavoriteScreen(
                    innerPaddingValues = innerPadding,
                    navigateToDetail = { club ->
                        navController.navigate(Screen.DetailClub.createRoute(clubId = club))
                    }
                )
            }
            composable(
                route = Screen.DetailClub.route,
                arguments = listOf(navArgument(Screen.DetailClub.CLUB_ID_ARG){
                    type = NavType.IntType
                })
            ) {
                val id = it.arguments?.getInt(Screen.DetailClub.CLUB_ID_ARG) ?: -1
                DetailScreen(
                    clubId = id,
                    innerPadding = innerPadding,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EPLNewsScreenPreview() {
    FootballAppWithJetpackTheme {
        EPLNewsScreen()
    }
}