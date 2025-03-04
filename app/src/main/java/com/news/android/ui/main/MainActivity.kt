package com.news.android.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.news.android.presentation.ui.CustomWebView
import com.news.android.presentation.ui.NewsScreen
import com.news.android.ui.theme.NewsApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsApplicationTheme {
                val navController = rememberNavController() // Create navController
                NewsScreenWithInsets(navController) // Pass navController
            }
        }
    }
}
@Composable
fun NewsScreenWithInsets(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        NewsNavHost(navController)
    }
}

@Composable
fun NewsNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "news") {
        composable("news") {
            NewsScreen(navController) // Pass navController to NewsScreen
        }
        composable(
            "webview/{url}",
            arguments = listOf(navArgument("url") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            CustomWebView(url)
        }
    }
}
