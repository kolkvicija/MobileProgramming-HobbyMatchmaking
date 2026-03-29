
package com.example.hobbymatchmakingapp.presentation.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hobbymatchmakingapp.presentation.util.Hobby

@Composable
fun HobbyDetailScreen(
    hobby: Hobby,
    onBack: () -> Unit,
    onEdit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Hobby Details",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Name: ${hobby.name}")
                Text("Category: ${hobby.category}")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onEdit) {
            Text("Edit")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onBack) {
            Text("Back")
        }
    }
}