package com.example.hobbymatchmakingapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
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

    val viewModel: HomeViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        // 🏠 HOME
        composable("home") {

            val hobbies = viewModel.hobbies.collectAsState().value

            HomeScreen(
                viewModel = viewModel,
                onNavigateToProfile = {
                    navController.navigate("profile")
                },
                onNavigateToAdd = {
                    navController.navigate("add_hobby")
                },
                onNavigateToDetail = { hobbyId ->

                    val hobby = hobbies.find { it.id == hobbyId }

                    hobby?.let {
                        navController.navigate("details/${it.id}/${it.name}")
                    }
                }
            )
        }

        // 👤 PROFILE
        composable("profile") {
            ProfileScreen(viewModel = viewModel)
        }

        // ➕ ADD
        composable("add_hobby") {
            AddHobbyScreen(
                onAddClick = { name, category ->
                    viewModel.addHobby(name, category)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // 📄 DETAILS (2 arguments)
        composable("details/{hobbyId}/{hobbyName}") { backStackEntry ->

            val hobbies = viewModel.hobbies.collectAsState().value

            val hobbyId = backStackEntry.arguments
                ?.getString("hobbyId")
                ?.toIntOrNull()

            val hobbyName = backStackEntry.arguments
                ?.getString("hobbyName") ?: ""

            val hobby = hobbies.find { it.id == hobbyId }

            if (hobby != null) {
                HobbyDetailScreen(
                    hobby = hobby,
                    hobbyName = hobbyName,
                    onBack = {
                        navController.popBackStack()
                    },
                    onEdit = {
                        navController.navigate("edit/${hobby.id}")
                    }
                )
            }
        }

        // ✏️ EDIT
        composable("edit/{hobbyId}") { backStackEntry ->

            val hobbies = viewModel.hobbies.collectAsState().value

            val hobbyId = backStackEntry.arguments
                ?.getString("hobbyId")
                ?.toIntOrNull()

            val hobby = hobbies.find { it.id == hobbyId }

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