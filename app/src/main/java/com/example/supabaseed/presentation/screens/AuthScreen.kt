@file:OptIn(ExperimentalAnimationApi::class)

package com.example.supabaseed.presentation.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.supabaseed.BuildConfig
import com.example.supabaseed.R
import com.example.supabaseed.presentation.AuthUiState
import com.example.supabaseed.presentation.AuthViewModel
import com.example.supabaseed.presentation.FieldType
import com.example.supabaseed.presentation.components.*

@Composable
fun AuthScreen(
    authViewModel: AuthViewModel,
    authUiState: AuthUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween, modifier = modifier
    ) {
        AuthHeaderSection(
            isRegister = authUiState.isRegister, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_xxxl)))
        AuthBodySection(
            authUiState,
            authViewModel,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        )
    }

}

@Composable
private fun AuthHeaderSection(
    isRegister: Boolean, modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        Text(
            text = stringResource(id = R.string.welcome_title),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(ButtonDefaults.IconSpacing))
        Text(
            text = if (isRegister) stringResource(id = R.string.sign_up_title) else stringResource(
                id = R.string.sign_in_title
            ),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun AuthBodySection(
    authUiState: AuthUiState,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.padding_xxxl)
        ),
        modifier = modifier
    ) {
        AuthForm(
            authUiState = authUiState,
            authViewModel = authViewModel,
        )
        OtherLoginDivider()
        SocialLoginButtons(
            googleClientKey = BuildConfig.GOOGLE_CLIENT_KEY,
            signInWithGoogle = { authViewModel.signInWithGoogleTokenID(it) },
            signInWithFacebook = { authViewModel.signInWithFacebook() },
        )
        AuthSwitcher(
            authUiState.isRegister
        ) { authViewModel.updateIsRegOrLogin() }

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AuthForm(
    authUiState: AuthUiState,
    authViewModel: AuthViewModel,
) {
    AnimatedContent(
        targetState = authUiState.isRegister, label = "AuthFormContentAnim"
    ) { isRegister ->
        when (isRegister) {
            false -> LoginForm(authUiState = authUiState,
                onEmailChanged = { authViewModel.updateAuthTextField(it, FieldType.EMAIL) },
                onPasswordChanged = { authViewModel.updateAuthTextField(it, FieldType.PASSWORD) },
                onPasswordVisibilityChanged = {
                    authViewModel.updatePasswordVisibility(
                        isVisible = it,
                        fieldType = FieldType.PASSWORD,
                    )
                },
                onLoginClicked = { authViewModel.signInWithEmail() })


            true -> RegistrationForm(authUiState = authUiState,
                onEmailChanged = { authViewModel.updateAuthTextField(it, FieldType.EMAIL) },
                onPasswordChanged = { authViewModel.updateAuthTextField(it, FieldType.PASSWORD) },
                onPasswordVisibilityChanged = {
                    authViewModel.updatePasswordVisibility(
                        isVisible = it,
                        fieldType = FieldType.PASSWORD,
                    )
                },
                onConfirmPasswordChanged = {
                    authViewModel.updateAuthTextField(
                        it, FieldType.CONF_PASSWORD
                    )
                },
                onConfirmPasswordVisibilityChanged = {
                    authViewModel.updatePasswordVisibility(
                        isVisible = it,
                        fieldType = FieldType.CONF_PASSWORD,
                    )
                },
                onRegClicked = { authViewModel.signUpWithEmail() })
        }
    }
}




