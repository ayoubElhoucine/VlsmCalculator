package com.vlsm.vlsmcalculator.ui.vlsmCalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
private fun SubnetViewItem(
    subnet: Subnet
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 10.dp)
            .background(Color.Black.copy(alpha = 0.05f), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {

    }
}

@Composable
private fun ItemView(
    title: String,
    value: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {

    }
}