package com.example.supabaseed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.supabaseed.presentation.screens.AuthScreen
import com.example.supabaseed.presentation.AuthViewModel
import com.example.supabaseed.presentation.components.ErrorDialog
import com.example.supabaseed.presentation.components.LoadingDialog
import com.example.supabaseed.presentation.screens.LogoutScreen
import com.example.supabaseed.ui.theme.SupabaseEdTheme
import dagger.hilt.android.AndroidEntryPoint
import io.github.jan.supabase.gotrue.SessionStatus

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel.handleDeeplink(intent)

        setContent {
            val authUiState by authViewModel.authUiState.collectAsState()
            val sessionStatus by authUiState.sessionStatus.collectAsState()

            SupabaseEdTheme {
                Scaffold() { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        when (sessionStatus) {
                            is SessionStatus.Authenticated -> {
                                LogoutScreen {
                                    authViewModel.signOut()
                                }
                            }

                            else -> {
                                AuthScreen(
                                    authViewModel = authViewModel,
                                    authUiState = authUiState,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(dimensionResource(id = R.dimen.padding_xl))
                                )
                                
                                if (sessionStatus == SessionStatus.LoadingFromStorage) {
                                    LoadingDialog()
                                }
                                
                                if (authUiState.errorMessage != null) {
                                    ErrorDialog(
                                        errorMessage = authUiState.errorMessage!!) {
                                        authViewModel.resetErrorMessage()
                                    }
                                }
                                
                            }
                        }
                    }

                }
            }
        }
    }
}