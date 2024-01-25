package com.vlsm.vlsmcalculator.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
internal fun MyButton(
    onClick: () -> Unit,
    enabled: Boolean = true,
    color: Color = Color.DarkGray,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            disabledContainerColor = Color.LightGray,
        )
    ) {
        content()
    }
}