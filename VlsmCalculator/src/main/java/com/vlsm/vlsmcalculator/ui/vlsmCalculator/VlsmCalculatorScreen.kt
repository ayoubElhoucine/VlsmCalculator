package com.vlsm.vlsmcalculator.ui.vlsmCalculator

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vlsm.vlsmcalculator.ui.components.ScreenLayout

@Composable
internal fun VlsmCalculatorScreen(
    state: VlsmCalculatorState = rememberVlsmCalculatorState()
) {
    ScreenLayout {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp),
            value = state.ipAddress.value,
            placeholder = { Text(text = "IP address") },
            maxLines = 1,
            singleLine = true,
            onValueChange = {
                state.ipAddress.value = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done,
                autoCorrect = false,
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Person, contentDescription = null)
            }
        )
    }
}