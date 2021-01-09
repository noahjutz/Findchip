package com.noahjutz.findchip.ui.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
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
    popBackStack: () -> Unit
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
                Modifier.clickable {},
                text = { Text("License") },
                secondaryText = { Text("GPL-3.0") },
                icon = { Icon(Icons.Default.LockOpen) },
            )
            ListItem(
                Modifier.clickable {},
                text = { Text("Dependencies") },
                secondaryText = { Text("Open source licenses") },
                icon = { Icon(Icons.Default.List) },
            )

            Divider()
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
        }
    }
}