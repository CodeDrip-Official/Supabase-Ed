package com.example.supabaseed.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.supabaseed.R

@Composable
fun LoadingDialog() {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = { },
        icon = { CircularProgressIndicator() },
        title = { Text(text = "Loading") }
    )
}

@Composable
fun ErrorDialog(
    errorMessage: String,
    onDismissClicked: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismissClicked() },
        confirmButton = { },
        icon = { Icon(painterResource(id = R.drawable.ic_error), contentDescription = null) },
        title = { Text(text = "Oops", style = MaterialTheme.typography.headlineSmall,) },
        text = {
            Text(
                errorMessage,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    )
}