package com.noahjutz.findchip.ui.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.noahjutz.findchip.R

@Composable
fun AboutApp(
    popBackStack: () -> Unit,
    openUrl: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = popBackStack) { Icon(Icons.Default.ArrowBack) }
                },
                title = { Text("About") },
            )
        }
    ) {
        var showDependencyDialog by remember { mutableStateOf(false) }
        var showSupportedDevicesDialog by remember { mutableStateOf(false) }
        when {
            showDependencyDialog -> DependencyDialog(
                openUrl = openUrl,
                onDismiss = { showDependencyDialog = false }
            )
            showSupportedDevicesDialog -> SupportedDevicesDialog(
                onDismiss = { showSupportedDevicesDialog = false }
            )
        }
        ScrollableColumn {
            Box(
                Modifier.fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        imageVector = vectorResource(R.drawable.ic_launcher_foreground).copy(
                            defaultWidth = 60.dp,
                            defaultHeight = 60.dp
                        ),
                        modifier = Modifier.clip(CircleShape)
                            .background(colorResource(R.color.ic_launcher_background)),
                        contentScale = ContentScale.FillBounds
                    )
                    Spacer(Modifier.preferredWidth(12.dp))
                    ProvideTextStyle(typography.h3) {
                        Text("Findchip")
                    }
                }
            }

            Divider()
            ListItem(
                Modifier.clickable {},
                text = { Text("Author") },
                secondaryText = { Text("Noah Jutz") },
                icon = { Icon(Icons.Default.Face) },
            )
            ListItem(
                Modifier.clickable {},
                text = { Text("Version") },
                secondaryText = { Text("0.0.1") },
                icon = { Icon(Icons.Default.Update) },
            )
            ListItem(
                Modifier.clickable { openUrl("https://github.com/noahjutz/Findchip/blob/master/LICENSE") },
                text = { Text("License") },
                secondaryText = { Text("GPL-3.0") },
                icon = { Icon(Icons.Default.LockOpen) },
                trailing = {
                    Icon(
                        Icons.Default.Launch,
                        tint = AmbientContentColor.current.copy(alpha = 0.5f)
                    )
                },
            )
            ListItem(
                Modifier.clickable { showDependencyDialog = true },
                text = { Text("Dependencies") },
                secondaryText = { Text("Open source licenses") },
                icon = { Icon(Icons.Default.List) },
            )
            ListItem(
                Modifier.clickable { showSupportedDevicesDialog = true },
                text = { Text("Supported devices") },
                secondaryText = { Text("1 supported device") },
                icon = { Icon(Icons.Default.Check) },
            )

            Divider()
            ListItem(
                Modifier.clickable { openUrl("https://liberapay.com/noahjutz/donate") },
                text = { Text("Donate") },
                secondaryText = { Text("Liberapay") },
                icon = { Icon(Icons.Default.Favorite) },
                trailing = {
                    Icon(
                        Icons.Default.Launch,
                        tint = AmbientContentColor.current.copy(alpha = 0.5f)
                    )
                },
            )
            ListItem(
                Modifier.clickable { openUrl("https://github.com/noahjutz/Findchip") },
                text = { Text("Source Code") },
                secondaryText = { Text("GitHub") },
                icon = { Icon(Icons.Default.Code) },
                trailing = {
                    Icon(
                        Icons.Default.Launch,
                        tint = AmbientContentColor.current.copy(alpha = 0.5f)
                    )
                },
            )
            ListItem(
                Modifier.clickable { openUrl("https://github.com/noahjutz/Findchip/blob/master/README.md") },
                text = { Text("Contributing") },
                secondaryText = { Text("Find out how to contribute!") },
                icon = { Icon(Icons.Default.Create) },
                trailing = {
                    Icon(
                        Icons.Default.Launch,
                        tint = AmbientContentColor.current.copy(alpha = 0.5f)
                    )
                },
            )
        }
    }
}

@Composable
fun DependencyDialog(
    openUrl: (String) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Dependencies") },
        text = {
            ScrollableColumn {
                ListItem(
                    Modifier.clickable { openUrl("https://developer.android.com/jetpack/androidx/") },
                    text = { Text("AndroidX") },
                    secondaryText = { Text("Apache 2.0") },
                    trailing = {
                        Icon(
                            Icons.Default.Launch,
                            tint = AmbientContentColor.current.copy(alpha = 0.5f)
                        )
                    },
                )
                ListItem(
                    Modifier.clickable { openUrl("https://insert-koin.io/") },
                    text = { Text("koin") },
                    secondaryText = { Text("Apache 2.0") },
                    trailing = {
                        Icon(
                            Icons.Default.Launch,
                            tint = AmbientContentColor.current.copy(alpha = 0.5f)
                        )
                    },
                )
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Dismiss")
            }
        }
    )
}

@Composable
fun SupportedDevicesDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Supported Devices") },
        text = {
            ScrollableColumn {
                ListItem(
                    Modifier.clickable { },
                    text = { Text("iFindU") },
                )
                Text("If your device is not on the list, please consider contributing.")
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Dismiss")
            }
        }
    )
}
