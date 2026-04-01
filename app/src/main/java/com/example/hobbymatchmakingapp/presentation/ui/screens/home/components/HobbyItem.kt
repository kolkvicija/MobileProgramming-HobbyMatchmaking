package com.example.hobbymatchmakingapp.presentation.ui.screens.home.components
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hobbymatchmakingapp.presentation.util.Hobby

@Composable
fun HobbyItem(hobby: Hobby) {
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "${hobby.name} (${hobby.category})",
            modifier = Modifier.padding(16.dp)
        )
    }
}