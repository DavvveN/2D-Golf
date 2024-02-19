package com.example.a2d_golf.userinterface


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import com.example.a2d_golf.GameViewModel

@Composable
fun SettingButton(viewModel : GameViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        IconButton(
            onClick = {
                viewModel.showSettings()
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(64.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                modifier = Modifier.size(64.dp)
            )
        }
    }
}