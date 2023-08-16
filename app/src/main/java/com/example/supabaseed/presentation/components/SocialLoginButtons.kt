package com.example.supabaseed.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.supabaseed.R

@Composable
fun SocialLoginButtons(
    signInWithGoogle: () -> Unit,
    signInWithFacebook: () -> Unit,
) {
    Button(
        onClick = { signInWithGoogle() },
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
        onClick = { signInWithFacebook() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2374f2), contentColor = Color.White
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