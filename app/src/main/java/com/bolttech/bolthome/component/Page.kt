package com.bolttech.bolthome.component

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
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier

@Composable
fun Page(
    floatingActionButton: @Composable () -> Unit ={},
    content: @Composable (PaddingValues) -> Unit ,
) {
    Scaffold(
        topBar = { AppBar() },
        floatingActionButton = floatingActionButton,
        content = content
    )

}

@Composable
private fun AppBar() {
    TopAppBar(title = { },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            Image( painterResource(R.drawable.ic_chevron_left), "", modifier = Modifier.padding(horizontal = 16.dp))
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