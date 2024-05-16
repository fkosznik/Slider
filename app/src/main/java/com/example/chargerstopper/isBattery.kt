package com.example.chargerstopper

import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BatterySliderCheck() {
    var sliderPosition by remember { mutableStateOf(0f) }
    var batteryPercentage by remember { mutableStateOf(0) }
    var isBatteryLower by remember { mutableStateOf(true) }
    val context = LocalContext.current


    // Create a BroadcastReceiver to receive battery status updates
    val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
        context.registerReceiver(null, ifilter)
    }

    // Extract battery percentage
    batteryStatus?.let { intent ->
        val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        batteryPercentage = (level * 100 / scale.toFloat()).toInt()
    }

    // Check if battery percentage is lower than slider position
    isBatteryLower = batteryPercentage < sliderPosition * 100

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it }
        )
        Text(text = "Slider Position: ${(sliderPosition * 100).toInt()}%")
        Text(text = "Battery Percentage: $batteryPercentage%")
        Checkbox(
            checked = isBatteryLower,
            onCheckedChange = null // You can add logic here if needed
        )


    }
}

@Composable
@Preview
fun BatterySliderCheckPreview() {
    var sliderPosition by remember { mutableStateOf(0f) }
    var batteryPercentage by remember { mutableStateOf(0) }
    var isBatteryLower by remember { mutableStateOf(true) }
    val context = LocalContext.current

    // Create a BroadcastReceiver to receive battery status updates
    val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
        context.registerReceiver(null, ifilter)
    }

    // Extract battery percentage
    batteryStatus?.let { intent ->
        val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        batteryPercentage = (level * 100 / scale.toFloat()).toInt()
    }

    // Check if battery percentage is lower than slider position
    isBatteryLower = batteryPercentage < sliderPosition * 100

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it }
        )
        Text(text = "Slider Position: ${(sliderPosition * 100).toInt()}%")
        Text(text = "Battery Percentage: $batteryPercentage%")
        Checkbox(
            checked = isBatteryLower,
            onCheckedChange = null // You can add logic here if needed
        )


    }
}
