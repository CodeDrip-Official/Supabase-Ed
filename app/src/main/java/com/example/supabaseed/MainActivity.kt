package com.example.supabaseed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.supabaseed.presentation.screens.AuthScreen
import com.example.supabaseed.ui.theme.SupabaseEdTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SupabaseEdTheme {
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