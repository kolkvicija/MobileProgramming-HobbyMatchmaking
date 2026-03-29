package com.example.hobbymatchmakingapp.presentation.ui.screens.add_hobby

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddHobbyScreen(
    onAddClick: (String, String) -> Unit,
    onBack: () -> Unit
) {

    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    val isValid = name.isNotBlank() && category.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Add Hobby",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Hobby Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onAddClick(name, category)
                onBack()
            },
            enabled = isValid
        ) {
            Text("Add Hobby")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { onBack() }) {
            Text("Back")
        }

        if (!isValid) {
            Text(
                text = "Please fill all fields",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}