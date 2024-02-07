package com.vlsm.vlsmcalculatorsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vlsm.vlsmcalculator.ui.VlsmCalculatorView
import com.vlsm.vlsmcalculatorsample.ui.theme.VlsmCalculatorSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VlsmCalculatorSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VlsmCalculatorView()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    VlsmCalculatorSampleTheme {
        VlsmCalculatorView()
    }
}