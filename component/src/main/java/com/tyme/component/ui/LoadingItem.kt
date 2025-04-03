package com.tyme.component.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tyme.component.theme.GitAdminTheme

@Composable
fun LoadingItem(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier =
                Modifier
                    .size(50.dp),
        )
    }
}

@Preview
@Composable
private fun LoadingItemPreview() {
    GitAdminTheme {
        LoadingItem()
    }
}
