package com.example.hobbymatchmakingapp.presentation.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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

    // ✅ STATE
    var selectedCategory by remember { mutableStateOf("All") }
    var searchQuery by remember { mutableStateOf("") }

    val categories = listOf("All", "Sport", "Entertainment", "Lifestyle", "Education")

    // ✅ DERIVED STATE
    val filteredHobbies = hobbies
        .filter {
            selectedCategory == "All" || it.category == selectedCategory
        }
        .filter {
            it.name.contains(searchQuery, ignoreCase = true)
        }

    val hobbyCount = filteredHobbies.size
    val sportCount = hobbies.count { it.category == "Sport" }

    // ✅ UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
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

        // ✅ SEARCH
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search hobbies...") },
            modifier = Modifier.fillMaxWidth()
        )

        // ✅ CATEGORY FILTER
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(categories) { category ->
                Button(onClick = { selectedCategory = category }) {
                    Text(category)
                }
            }
        }

        // ✅ DERIVED STATE DISPLAY
        Text("Showing: $hobbyCount hobbies")
        Text("Sports hobbies: $sportCount")

        if (filteredHobbies.isEmpty()) {
            Text("No hobbies available")
        } else {

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                items(filteredHobbies) { hobby ->

                    Card(modifier = Modifier.fillMaxWidth()) {

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
}