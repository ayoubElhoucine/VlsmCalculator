package com.vlsm.vlsmcalculator.ui.vlsmCalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vlsm.vlsmcalculator.model.Subnet
import com.vlsm.vlsmcalculator.ui.common.UiState
import com.vlsm.vlsmcalculator.ui.components.MyButton
import com.vlsm.vlsmcalculator.ui.components.ScreenLayout
import kotlinx.coroutines.launch

@Composable
internal fun VlsmCalculatorScreen(
    state: VlsmCalculatorState = rememberVlsmCalculatorState(),
    onDetails: (List<Subnet>) -> Unit,
) {
    val scope = rememberCoroutineScope()
    ScreenLayout {
        Column(
            modifier = Modifier
                .background(Color.Black.copy(0.05f), shape = RoundedCornerShape(10.dp))
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            IpAddressView(text = state.ipAddress)
            HostNumbersView(data = state.hostNumbers, onValueChanged = state::updateHostNumbers)
        }
        MyButton(
            enabled = state.enabled,
            onClick = {
                scope.launch {
                    val result = state.calculate()
                    println(result)
                }
            },
        ) {
            when (state.uiState.value) {
                UiState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.size(30.dp),
                    color = Color.White,
                    strokeWidth = 1.5.dp,
                )
                else -> Text(text = "Calculate")
            }
        }
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
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedBorderColor = Color.Black.copy(0.2f),
            unfocusedBorderColor = Color.Black.copy(0.2f),
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
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        itemsIndexed(data) { index, value ->
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black.copy(0.2f),
                        shape = RoundedCornerShape(10.dp),
                    )
                    .height(42.dp),
                value = value?.toString() ?: "",
                singleLine = true,
                onValueChange = {
                    onValueChanged(it, index)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                    autoCorrect = false,
                ),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = 14.sp),
            ) { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    value ?: run {
                        Text(
                            text = "Host number ${index + 1}",
                            color = Color.Black.copy(alpha = 0.6f),
                            fontSize = 11.sp
                        )
                    }
                    innerTextField()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    VlsmCalculatorScreen {

    }
}