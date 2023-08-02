package com.example.supabaseed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.supabaseed.presentation.auth.AuthScreen
import com.example.supabaseed.ui.theme.SupabaseEdTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SupabaseEdTheme {
                // A surface container using the 'background' color from the theme
                Scaffold() { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        AuthScreen(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(dimensionResource(id = R.dimen.padding_xl))
                        )
                    }
                }
            }
        }
    }
}