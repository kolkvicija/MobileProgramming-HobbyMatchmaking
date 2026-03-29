package com.example.hobbymatchmakingapp.presentation.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hobbymatchmakingapp.presentation.ui.screens.home.components.HobbyItem
import com.example.hobbymatchmakingapp.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToProfile: () -> Unit,
    onNavigateToAdd: () -> Unit
) {

    val hobbies by viewModel.hobbies.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = { onNavigateToAdd() }) {
            Text("Add Hobby")
        }
        Button(onClick = { onNavigateToProfile() }) {
            Text("Go to Profile")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Hobby Matchmaking",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (hobbies.isEmpty()) {
            Text("No hobbies available")
        } else {
            hobbies.forEach {
                HobbyItem(it)
            }
        }
    }
}