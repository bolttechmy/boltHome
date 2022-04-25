package com.bolttech.bolthome.component

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bolttech.bolthome.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun Page(
    floatingActionButton: @Composable () -> Unit = {},
    backPress: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = { AppBar() },
        floatingActionButton = floatingActionButton,
        content = content
    )

}

@Composable
private fun AppBar(backPress: () -> Unit = {}) {
    val context = LocalContext.current;
    TopAppBar(title = { },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            Image(
                painterResource(R.drawable.ic_chevron_left),
                "",
                modifier = Modifier.padding(horizontal = 16.dp).clickable {
                    backPress()
                }
            )
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "image",
                    tint = Color.Black,
                )
            }
        }
    )
}