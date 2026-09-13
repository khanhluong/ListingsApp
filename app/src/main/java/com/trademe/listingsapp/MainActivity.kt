package com.trademe.listingsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.trademe.listingsapp.presentation.navigation.NavGraph
import com.trademe.listingsapp.ui.theme.ListingsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListingsAppTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}