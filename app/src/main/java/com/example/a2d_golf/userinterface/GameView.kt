package com.example.a2d_golf.userinterface

import android.media.MediaPlayer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.a2d_golf.*
import com.example.a2d_golf.consts.MiscConst
import kotlinx.coroutines.delay


@Composable
fun GameView(
    viewModel: GameViewModel = viewModel(),
    levelData: LevelData = LevelData(),
    miscConst: MiscConst = MiscConst(),

) {
    val gameState by viewModel.gameState.collectAsState()
    val bState by viewModel.bState.collectAsState()
    var prevTime: Long

    val sS = viewModel._settingState.collectAsState()

    val context = LocalContext.current
    val mediaPlayer = MediaPlayer.create(context,R.raw.music)
    mediaPlayer.setVolume(sS.value.volume,sS.value.volume)

    if(gameState.start) {
        mediaPlayer.start()
        viewModel.startMusic()
    }


    when (gameState.status) {
        GameStatus.START -> {
            StartPage(modifier = Modifier, viewModel)
        }

        GameStatus.GAME -> {
            LaunchedEffect(Unit) {

                prevTime = System.currentTimeMillis()
                while (true) {
                    val currTime = System.currentTimeMillis()
                    viewModel.update(deltaTime = ((currTime - prevTime).toFloat()) / 1000)
                    prevTime = currTime
                    delay((miscConst.SECONDS_IN_MILLI / miscConst.FPS).toLong())

                }
            }

            //Draw out components
            Background()
            DrawMap(levelData.levels.get(gameState.currentLevel))
            MovementArrow(modifier = Modifier, arrowState = viewModel._movementArrowState)
            Ball(modifier = Modifier, ballState = bState)
            BallTracer(modifier = Modifier, ballState = bState)
            SettingButton(viewModel = viewModel)
            Flag(viewModel = viewModel, gameState = gameState)

            if (gameState.promptRestart) {
                PromptRestart(viewModel = viewModel)
            }

        }

        GameStatus.SETTINGS -> {
            SettingsView(viewModel = viewModel)
        }

        GameStatus.LEVELS -> {
            LevelSelectorView(viewModel = viewModel)
        }
    }

}
