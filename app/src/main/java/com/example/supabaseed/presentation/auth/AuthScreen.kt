package com.example.supabaseed.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.example.supabaseed.R

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        AuthHeaderSection(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)

        )
        AuthBodySection(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2F)
                .background(MaterialTheme.colorScheme.background)
        )
    }
}

@Composable
private fun AuthHeaderSection(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        Text(
            text = "Welcome to SupabaseEd",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(ButtonDefaults.IconSpacing))
        Text(
            text = "Sign In to your Account",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun AuthBodySection(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.padding_xl)
        ),
        modifier = modifier
    ) {
        LoginForm()
        Spacer(modifier = Modifier.weight(1F))
        OtherLoginDivider()
        Spacer(modifier = Modifier.weight(1F))
        SocialLoginButtons()
        Spacer(modifier = Modifier.weight(1F))
        RegisterAccountText()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginForm() {
    Column {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            label = { Text(text = stringResource(id = R.string.email_lbl)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_xl)))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            trailingIcon = { IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.ic_visible), contentDescription = null)
            }},
            label = { Text(text = stringResource(id = R.string.password_lbl)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_sm)))
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.forgot_password_lbl),
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.End,
                modifier = Modifier.clickable { }
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_xl)))
        Button(
            onClick = { /*TODO*/ },
            contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.padding_lg)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.padding_lg))
        ) {
            Text(
                text = stringResource(id = R.string.login_lbl),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
            )
        }
    }
}

@Composable
private fun OtherLoginDivider() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_sm))
    ) {
        Divider(modifier = Modifier.weight(.5F))
        Text(
            stringResource(id = R.string.or_lbl),
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.padding_xl)
            )
        )
        Divider(modifier = Modifier.weight(.5F))
    }
}

@Composable
private fun SocialLoginButtons() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
            contentColor = if (isSystemInDarkTheme()) Color.Black else Color.White
        ),
        contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.padding_lg)),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_google_btn_logo),
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(
            text = stringResource(id = R.string.cont_with_google),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
        )
    }
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2374f2),
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.padding_lg)),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_facebook_btn_logo),
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(
            text = stringResource(id = R.string.cont_with_fb),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
        )
    }
}

@Composable
private fun RegisterAccountText() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.padding_sm)
        )
    ) {
        Text(text = stringResource(id = R.string.no_account_lbl))
        Text(
            text = stringResource(id = R.string.register_lbl),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { }
        )
    }
}



