package com.example.hobbymatchmakingapp.presentation.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hobbymatchmakingapp.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToProfile: () -> Unit,
    onNavigateToAdd: () -> Unit,
    onNavigateToDetail: (Int) -> Unit 
) {

    val hobbies by viewModel.hobbies.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

       
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { onNavigateToProfile() }) {
                Text("Profile")
            }

            Button(onClick = { onNavigateToAdd() }) {
                Text("Add Hobby")
            }
        }

        
        Text(
            text = "Hobby Matchmaking",
            style = MaterialTheme.typography.headlineMedium
        )

        
        if (hobbies.isEmpty()) {
            Text("No hobbies available")
        } else {

            hobbies.forEach { hobby ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column {
                            Text(
                                text = hobby.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = hobby.category,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                       
                        Button(onClick = {
                            onNavigateToDetail(hobby.id)
                        }) {
                            Text("View")
                        }
                    }
                }
            }
        }
    }
}