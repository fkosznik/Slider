package com.example.chargerstopper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.chargerstopper.ui.theme.ChargerStopperTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChargerStopperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Slider()
                }
            }
        }
    }
}

@Composable
fun Slider() {

    var sliderPosition by remember { mutableFloatStateOf(0f) }
    var isBatteryLower by remember { mutableStateOf(true) }
    var batteryPercentage by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
        context.registerReceiver(null, ifilter)
    }
    batteryStatus?.let { intent ->
        val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        batteryPercentage = (level * 100 / scale.toFloat()).toInt()

    }
    isBatteryLower = batteryPercentage < sliderPosition * 100
    Column  (modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it }
        )
        val percentage = (sliderPosition * 100).toInt()
        Text(text = "Slider: $percentage%")
        Text(text = "Battery Percentage: $batteryPercentage%")
        Checkbox(//check if battery
            checked = isBatteryLower,
            onCheckedChange = null // You can add logic here if needed
        )

    }


}
@Composable
@Preview
fun SliderPreview() {

    var sliderPosition by remember { mutableFloatStateOf(0f) }
    var isBatteryLower by remember { mutableStateOf(true) }
    var batteryPercentage by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
        context.registerReceiver(null, ifilter)
    }
    batteryStatus?.let { intent ->
        val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        batteryPercentage = (level * 100 / scale.toFloat()).toInt()

    }
    isBatteryLower = batteryPercentage < sliderPosition * 100
    Column  (modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it }
        )
        val percentage = (sliderPosition * 100).toInt()
        Text(text = "Slider: $percentage%")
        Text(text = "Battery Percentage: $batteryPercentage%")
        Checkbox(//check if battery
            checked = isBatteryLower,
            onCheckedChange = null // You can add logic here if needed
        )

    }


}


