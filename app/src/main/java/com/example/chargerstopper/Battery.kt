package com.example.chargerstopper

import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext


@Composable
fun BatteryPercentage() {
    var batteryPercentage by remember { mutableStateOf(0) }
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

    Column {
        Text(text = "Battery Percentage: $batteryPercentage%")
    }
}