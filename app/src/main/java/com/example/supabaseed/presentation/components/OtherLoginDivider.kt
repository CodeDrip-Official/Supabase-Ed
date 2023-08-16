package com.example.supabaseed.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.supabaseed.R

@Composable
fun OtherLoginDivider() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_sm))
    ) {
        Divider(modifier = Modifier.weight(.5F))
        Text(
            stringResource(id = R.string.or_lbl), modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.padding_xl)
            )
        )
        Divider(modifier = Modifier.weight(.5F))
    }
}