package com.noahjutz.findchip.ui.device_details

import android.bluetooth.BluetoothDevice
import androidx.compose.animation.animate
import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.noahjutz.findchip.util.AnimationState
import kotlinx.coroutines.flow.map
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import kotlin.math.absoluteValue

@Composable
fun DeviceDetails(
    device: BluetoothDevice,
    viewModel: DeviceDetailsViewModel = getViewModel { parametersOf(device) },
    popBackStack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { IconButton(onClick = popBackStack) { Icon(Icons.Default.ArrowBack) } },
                title = { Text("DeviceDetails") }
            )
        },
        floatingActionButton = {
            val isBeeping by viewModel.isBeeping.collectAsState()
            ExtendedFloatingActionButton(
                onClick = { if (isBeeping) viewModel.stopBeep() else viewModel.startBeep() },
                icon = { Icon(if (isBeeping) Icons.Default.VolumeOff else Icons.Default.VolumeUp) },
                text = { Text("${if (isBeeping) "Stop" else "Start"} beeping") },
            )
        }
    ) {
        val isConnected by viewModel.isConnected.collectAsState(false)
        when {
            !isConnected -> ConnectingAlert(popBackStack)
            else -> DeviceDetailsContent(viewModel)
        }
    }
}

@Composable
private fun DeviceDetailsContent(
    viewModel: DeviceDetailsViewModel
) {
    val signalStrength by viewModel.rssi.map { 100 - it.absoluteValue }.collectAsState(initial = 0)
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Signal strength: $signalStrength%")

            SignalStrengthIndicator(signalStrength)
        }
    }
}

@Composable
fun SignalStrengthIndicator(signalStrength: Int) {
    Box(contentAlignment = Alignment.Center) {
        SignalStrengthIndicatorBackground()
        SignalStrengthIndicatorForeground(signalStrength)
    }
}

@Composable
fun SignalStrengthIndicatorBackground() {
    val radiusKey = FloatPropKey("radius")
    val opacityKey = FloatPropKey("opacity")

    val initState by remember { mutableStateOf(AnimationState.START) }
    val toState by remember { mutableStateOf(AnimationState.END) }

    val transition = transition(
        definition = transitionDefinition {
            state(AnimationState.START) {
                this[radiusKey] = 300f
                this[opacityKey] = 0.5f
            }
            state(AnimationState.END) {
                this[radiusKey] = 350f
                this[opacityKey] = 0f
            }
            transition(
                AnimationState.START,
                AnimationState.END
            ) {
                val animation = TweenSpec<Float>(
                    durationMillis = 2000,
                    easing = LinearOutSlowInEasing
                )
                radiusKey using infiniteRepeatable(animation)
                opacityKey using infiniteRepeatable(animation)
            }
        },
        initState = initState,
        toState = toState
    )

    val color = MaterialTheme.colors.primary
    Canvas(Modifier.preferredSize(300.dp)) {
        val radius = transition[radiusKey]
        val opacity = transition[opacityKey]
        drawCircle(
            color = color.copy(alpha = opacity),
            radius = radius
        )
    }
}

@Composable
fun SignalStrengthIndicatorForeground(signalStrength: Int) {
    val color = MaterialTheme.colors.primary
    val radius = animate((signalStrength * 3).toFloat(), animSpec = TweenSpec(2000))
    Canvas(Modifier.preferredSize(300.dp)) {
        drawCircle(
            color = color,
            radius = radius
        )
        drawCircle(
            color = color.copy(alpha = 0.1f),
            radius = 300f
        )
    }
}

@Composable
private fun ConnectingAlert(
    popBackStack: () -> Unit
) {
    AlertDialog(
        title = { Text("Connecting...") },
        text = {
            LinearProgressIndicator(Modifier.fillMaxWidth())
        },
        dismissButton = {
            TextButton(onClick = popBackStack) {
                Text("Cancel")
            }
        },
        confirmButton = {},
        onDismissRequest = popBackStack
    )
}
