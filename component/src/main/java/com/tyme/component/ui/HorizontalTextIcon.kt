package com.tyme.component.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HorizontalTextIcon(
    modifier: Modifier = Modifier,
    alignment: Alignment.Vertical = Alignment.CenterVertically,
    icon: @Composable (() -> Unit),
    text: @Composable (() -> Unit),
) {

    Row(
        modifier = modifier,
        verticalAlignment = alignment
    ) {
        icon()
        text()
    }

}