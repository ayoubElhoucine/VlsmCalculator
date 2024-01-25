package com.vlsm.vlsmcalculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun ScreenLayout (
    title: String? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        title?.let {
            Text(
                text = it,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
        }
        content()
    }
}