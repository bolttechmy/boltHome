package com.bolttech.bolthome.screens

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bolttech.bolthome.component.Page
import com.bolttech.bolthome.data.UiState
import com.bolttech.bolthome.viewmodels.ScanResultViewModel


@Composable
fun ScanResultScreen() {

    val activity  = LocalContext.current as ComponentActivity

    Page {
        Surface(modifier = Modifier.fillMaxSize()) {
            val mainViewModel by remember { activity.viewModels<ScanResultViewModel>() }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                when (val result =
                    mainViewModel.uiState.collectAsState().value) {
                    is UiState.Content -> run {
                        result.data

                    }
                    is UiState.Error -> Text(text = result.message)
                    UiState.Loading -> CircularProgressIndicator()
                    UiState.Empty -> {}
                }
            }
        }
    }

}
