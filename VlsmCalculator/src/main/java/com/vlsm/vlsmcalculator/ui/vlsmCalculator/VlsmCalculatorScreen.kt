package com.vlsm.vlsmcalculator.ui.vlsmCalculator

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
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
        IpAddressView(text = state.ipAddress)
        HostNumbersView(data = state.hostNumbers, onValueChanged = state::updateHostNumbers)
    }
}

@Composable
private fun IpAddressView(
    text: MutableState<String>
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = text.value,
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
            text.value = it
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done,
            autoCorrect = false,
        ),
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
        ),
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
    )
}

@Composable
private fun HostNumbersView(
    data: SnapshotStateList<Int?>,
    onValueChanged: (String, Int) -> Unit,
) {
    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        columns = GridCells.Fixed(3),
    ) {
        itemsIndexed(data) { index, value ->
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 0.5.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(10.dp),
                    )
                    .height(46.dp),
                value = value?.toString() ?: "",
                placeholder = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Host number $index",
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        maxLines = 1,
                    )
                },
                singleLine = true,
                onValueChange = {
                    onValueChanged(it, index)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                    autoCorrect = false,
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.Unspecified,
                    unfocusedBorderColor = Color.Unspecified,
                ),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = 14.sp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    VlsmCalculatorScreen()
}