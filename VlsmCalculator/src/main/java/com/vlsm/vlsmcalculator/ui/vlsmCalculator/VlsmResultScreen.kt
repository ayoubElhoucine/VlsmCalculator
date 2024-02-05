package com.vlsm.vlsmcalculator.ui.vlsmCalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vlsm.vlsmcalculator.model.Subnet

@Composable
internal fun VlsmResultScreen(
    result: List<Subnet>,
    onBack: () -> Unit,
) {
    LazyColumn {
        item { Spacer(modifier = Modifier.height(16.dp)) }
        itemsIndexed(result) { _, item ->
            SubnetViewItem(subnet = item)
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

@Composable
private fun SubnetViewItem(
    subnet: Subnet
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .background(Color.Black.copy(alpha = 0.05f), shape = RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ItemView(title = "IP Address", value = subnet.address)
        ItemView(title = "Allocated size", value = subnet.allocatedSize.toString())
        ItemView(title = "Broadcast", value = subnet.broadcast)
        ItemView(title = "Dec mask", value = subnet.decMask)
        ItemView(title = "Needed size", value = subnet.neededSize.toString())
        ItemView(title = "Range", value = subnet.range)
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
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.Black,
        )
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Black,
        )
    }
}