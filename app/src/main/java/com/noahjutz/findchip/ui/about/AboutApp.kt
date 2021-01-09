package com.noahjutz.findchip.ui.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.noahjutz.findchip.R

@Composable
fun AboutApp(
    popBackStack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = popBackStack) { Icon(Icons.Default.ArrowBack) }
                },
                title = { Text("About") }
            )
        }
    ) {
        ScrollableColumn {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(vectorResource(R.drawable.ic_launcher_foreground))
                ProvideTextStyle(value = MaterialTheme.typography.h3) {
                    Text(stringResource(R.string.app_name))
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
                text = { Text("License") },
                secondaryText = { Text("GPL-3.0") },
                icon = { Icon(Icons.Default.LockOpen) },
            )
            ListItem(
                Modifier.clickable {},
                text = { Text("Donate") },
                secondaryText = { Text("Liberapay") },
                icon = { Icon(Icons.Default.Favorite) },
            )
            ListItem(
                Modifier.clickable {},
                text = { Text("Source Code") },
                secondaryText = { Text("GitHub") },
                icon = { Icon(Icons.Default.Code) },
            )
            ListItem(
                Modifier.clickable {},
                text = { Text("Contributing") },
                secondaryText = { Text("Find out how to contribute!") },
                icon = { Icon(Icons.Default.Create) },
            )
            ListItem(
                Modifier.clickable {},
                text = { Text("Version") },
                secondaryText = { Text("0.0.1") },
                icon = { Icon(Icons.Default.Update) },
            )
        }
    }
}