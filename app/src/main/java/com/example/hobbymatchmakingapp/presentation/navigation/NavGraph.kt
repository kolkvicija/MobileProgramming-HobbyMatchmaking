package com.example.hobbymatchmakingapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hobbymatchmakingapp.presentation.ui.screens.add_hobby.AddHobbyScreen
import com.example.hobbymatchmakingapp.presentation.ui.screens.home.HomeScreen
import com.example.hobbymatchmakingapp.presentation.ui.screens.profile.ProfileScreen
import com.example.hobbymatchmakingapp.presentation.viewmodel.HomeViewModel

@Composable
fun NavGraph() {
    val viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToProfile = {
                    navController.navigate("profile")
                },
                onNavigateToAdd = {
                    navController.navigate("add_hobby")
                }
            )
        }
        composable("profile") {
            ProfileScreen()
        }
        composable("add_hobby") {
            AddHobbyScreen(
                onAddClick = { name: String, category: String ->
                    viewModel.addHobby(name, category)
                },
                onBack = {
                    navController.popBackStack()
                }
            )


        }
    }
}