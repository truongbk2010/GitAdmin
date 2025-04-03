package com.tyme.component.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tyme.component.theme.GitAdminTheme

@Composable
fun LoadMoreErrorItem(
    text: String,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        FilledTonalButton(
            modifier = modifier.height(48.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
            onClick = { onRetry.invoke() },
        ) {
            Text(
                text = text,
            )
        }
    }
}

@Preview
@Composable
private fun LoadMoreErrorItemPreview() {
    GitAdminTheme {
        LoadMoreErrorItem(
            text = "Retry",
            onRetry = {},
        )
    }
}
