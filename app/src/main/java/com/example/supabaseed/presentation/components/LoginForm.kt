package com.example.supabaseed.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.example.supabaseed.R
import com.example.supabaseed.presentation.AuthUiState

@Composable
fun LoginForm(
    authUiState: AuthUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordVisibilityChanged: (Boolean) -> Unit,
    onLoginClicked: () -> Unit,
) {
    Column {
        OutlinedTextField(
            value = authUiState.email.fieldValue,
            onValueChange = { onEmailChanged(it) },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            label = { Text(text = stringResource(id = R.string.email_lbl)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            isError = !authUiState.email.isFieldValid,
            supportingText = if (!authUiState.email.isFieldValid) {
                { Text(text = authUiState.email.errorMessage) }
            } else {
                null
            })
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_xl)))
        OutlinedTextField(
            value = authUiState.password.fieldValue,
            onValueChange = { onPasswordChanged(it) },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            trailingIcon = {
                IconButton(onClick = { onPasswordVisibilityChanged(!authUiState.password.passwordVisible) }) {
                    Icon(
                        painter = painterResource(id = if (authUiState.password.passwordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visible),
                        contentDescription = null
                    )
                }
            },
            label = { Text(text = stringResource(id = R.string.password_lbl)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (authUiState.password.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = !authUiState.password.isFieldValid,
            supportingText = if (!authUiState.password.isFieldValid) {
                { Text(text = authUiState.password.errorMessage) }
            } else {
                null
            },
            modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_sm)))
        Row(
            horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.forgot_password_lbl),
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.End,
                modifier = Modifier.clickable { })
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_xl)))
        Button(
            onClick = { onLoginClicked() },
            contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.padding_lg)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.padding_lg))
        ) {
            Text(
                text = stringResource(id = R.string.sign_in_lbl),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
            )
        }
    }
}