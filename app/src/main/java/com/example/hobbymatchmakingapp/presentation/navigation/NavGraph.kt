package com.example.hobbymatchmakingapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hobbymatchmakingapp.presentation.ui.screens.add_hobby.AddHobbyScreen
import com.example.hobbymatchmakingapp.presentation.ui.screens.details.HobbyDetailScreen
import com.example.hobbymatchmakingapp.presentation.ui.screens.edit.EditHobbyScreen
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

        // 🏠 HOME
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToProfile = {
                    navController.navigate("profile")
                },
                onNavigateToAdd = {
                    navController.navigate("add_hobby")
                },
                onNavigateToDetail = { hobbyId ->
                    navController.navigate("details/$hobbyId")
                }
            )
        }

        // 👤 PROFILE
        composable("profile") {
            ProfileScreen()
        }

        // ➕ ADD HOBBY
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

        // 📄 DETAILS SCREEN
        composable("details/{hobbyId}") { backStackEntry ->

            val hobbyId = backStackEntry.arguments
                ?.getString("hobbyId")
                ?.toIntOrNull()

            val hobby = viewModel.hobbies
                .collectAsState()
                .value
                .find { it.id == hobbyId }

            if (hobby != null) {
                HobbyDetailScreen(
                    hobby = hobby,
                    onBack = {
                        navController.popBackStack()
                    },
                    onEdit = {
                        navController.navigate("edit/${hobby.id}")
                    }
                )
            }
        }

        // ✏️ EDIT SCREEN
        composable("edit/{hobbyId}") { backStackEntry ->

            val hobbyId = backStackEntry.arguments
                ?.getString("hobbyId")
                ?.toIntOrNull()

            val hobby = viewModel.hobbies
                .collectAsState()
                .value
                .find { it.id == hobbyId }

            if (hobby != null) {
                EditHobbyScreen(
                    hobby = hobby,
                    onSave = { name, category ->
                        viewModel.updateHobby(hobby.id, name, category)
                    },
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}