package com.example.supabaseed.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.supabaseed.R

@Composable
fun AuthSwitcher(
    isRegister: Boolean,
    onRegisterClicked: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.padding_sm)
        )
    ) {
        Text(
            text = if (isRegister) stringResource(id = R.string.have_account_lbl) else stringResource(
                id = R.string.no_account_lbl
            )
        )
        Text(text = if (isRegister) stringResource(id = R.string.sign_in_lbl) else stringResource(id = R.string.sign_up_lbl),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { onRegisterClicked() })
    }
}