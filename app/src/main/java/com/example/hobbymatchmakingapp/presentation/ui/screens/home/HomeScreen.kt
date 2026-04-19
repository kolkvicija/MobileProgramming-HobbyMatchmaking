package com.example.hobbymatchmakingapp.presentation.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    var selectedCategory by remember { mutableStateOf("All") }
    var searchQuery by remember { mutableStateOf("") }

    val categories = listOf("All", "Sport", "Entertainment", "Lifestyle", "Education")

    val filteredHobbies = hobbies
        .filter {
            selectedCategory == "All" || it.category == selectedCategory
        }
        .filter {
            it.name.contains(searchQuery, ignoreCase = true)
        }

    val hobbyCount = filteredHobbies.size
    val sportCount = hobbies.count { it.category == "Sport" }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Hobby Match",
                style = MaterialTheme.typography.headlineMedium
            )

            Row {
                Button(onClick = onNavigateToProfile) {
                    Text("Profile")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onNavigateToAdd) {
                    Text("Add")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search hobbies...") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(categories) { category ->

                val isSelected = category == selectedCategory

                Button(
                    onClick = { selectedCategory = category },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(category)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text("Showing: $hobbyCount hobbies")
        Text("Sports hobbies: $sportCount")

        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            items(filteredHobbies) { hobby ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(6.dp)
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
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = hobby.category,
                                color = MaterialTheme.colorScheme.primary
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