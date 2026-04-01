package com.example.hobbymatchmakingapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hobbymatchmakingapp.presentation.navigation.NavGraph
import com.example.hobbymatchmakingapp.ui.theme.HobbyMatchmakingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HobbyMatchmakingAppTheme {
                NavGraph()



            }
            }
        }
    }