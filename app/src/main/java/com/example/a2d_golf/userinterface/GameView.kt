package com.example.a2d_golf.userinterface

import androidx.compose.foundation.layout.Row
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Path
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
    val arrowModel by viewModel.movementArrowState.collectAsState()
    var prevTime: Long

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

            Point(565f, 600f)
            Point(852f, 520f)

            val path = Path()
            path.moveTo(565f, 600f)
            path.quadraticBezierTo(852f,600f,852f,520f)

            //Draw out components
            Background()
            DrawMap(levelData.firstLevel, path = path)
            MovementArrow(modifier = Modifier, arrowState = arrowModel)
            Ball(modifier = Modifier, ballState = bState)
            BallTracer(modifier = Modifier, ballState = bState)
            SettingButton(viewModel = viewModel)


        }

        GameStatus.SETTINGS -> {
            SettingsView(viewModel = viewModel)
        }
    }
}

