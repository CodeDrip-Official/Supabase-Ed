package com.example.supabaseed.presentation.screens

import androidx.compose.animation.AnimatedContent
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
import com.example.supabaseed.R
import com.example.supabaseed.presentation.components.AuthSwitcher
import com.example.supabaseed.presentation.components.LoginForm
import com.example.supabaseed.presentation.components.OtherLoginDivider
import com.example.supabaseed.presentation.components.RegistrationForm
import com.example.supabaseed.presentation.components.SocialLoginButtons

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween, modifier = modifier
    ) {
        AuthHeaderSection(
            isRegister = true, // TODO(Integrate with ViewModel)
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_xxxl)))
        AuthBodySection(
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
    modifier: Modifier = Modifier,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.padding_xxxl)
        ),
        modifier = modifier
    ) {
        AuthForm()
        OtherLoginDivider()
        SocialLoginButtons(
            signInWithGoogle = { },
            signInWithFacebook = { },
        )
        AuthSwitcher(true) { }

    }
}

@Composable
private fun AuthForm(
) {
    AnimatedContent(
        targetState = true, label = "AuthFormContentAnim"
    ) { isRegister ->
        when (isRegister) {
            false -> LoginForm(
                onEmailChanged = { },
                onPasswordChanged = { },
                onPasswordVisibilityChanged = {
                },
                onLoginClicked = { })


            true -> RegistrationForm(
                onEmailChanged = { },
                onPasswordChanged = { },
                onPasswordVisibilityChanged = {
                },
                onConfirmPasswordChanged = {
                },
                onConfirmPasswordVisibilityChanged = {
                },
                onRegClicked = { })
        }
    }
}




