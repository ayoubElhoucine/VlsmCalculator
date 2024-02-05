package com.vlsm.vlsmcalculator.ui.vlsmCalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.vlsm.vlsmcalculator.model.Subnet

@Composable
internal fun VlsmResultScreen(
    result: List<Subnet>,
    onBack: () -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        itemsIndexed(result) { _, item ->
            Text(
                text = item.toString()
            )
        }
    }
}