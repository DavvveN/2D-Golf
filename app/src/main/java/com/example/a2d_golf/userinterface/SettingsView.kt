package com.example.a2d_golf.userinterface

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.a2d_golf.GameViewModel
import com.example.a2d_golf.R
import com.example.a2d_golf.SettingsState


@Composable
fun SettingsView(viewModel: GameViewModel) {
    val m = LocalContext.current
    val sS = viewModel._settingState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.width(500.dp),
                horizontalAlignment = Alignment.Start
            ){
                Text("VOLUME")

                Slider(
                    value = sS.value.volume,
                    onValueChange = { it.also { viewModel._settingState.value = viewModel.settingsState.value.copy(volume = it) } },
                    steps = 20,
                    valueRange = 0f..1f,
                )
            }
            Button(
                onClick = {
                    viewModel.startGame()
                }
            ) {
                Text("CONTINUE")
            }

            Button(
                onClick = {
                    val mediaPlayer = MediaPlayer.create(m,R.raw.ball_bounce)
                    mediaPlayer.start()
                }
            ){
                Text("PLAY")
            }
        }
    }
}