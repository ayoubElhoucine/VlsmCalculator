package com.vlsm.vlsmcalculator.ui.vlsmCalculator

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vlsm.vlsmcalculator.ui.components.ScreenLayout

@Composable
internal fun VlsmCalculatorScreen(
    state: VlsmCalculatorState = rememberVlsmCalculatorState()
) {
    ScreenLayout {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp),
            value = state.ipAddress.value,
            placeholder = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "IP address",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                )
            },
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
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
            ),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    VlsmCalculatorScreen()
}