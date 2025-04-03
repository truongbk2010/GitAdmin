package com.tyme.component.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun VerticalTextIcon(
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    icon: @Composable (() -> Unit),
    text: @Composable (() -> Unit),
) {
    Column(
        modifier = modifier,
        horizontalAlignment = alignment
    ) {
        icon()
        text()
    }
}




