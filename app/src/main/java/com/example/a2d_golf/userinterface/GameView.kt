package com.example.a2d_golf.userinterface

import android.view.View
import androidx.compose.foundation.Canvas
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.a2d_golf.BallState
import com.example.a2d_golf.GameViewModel
import com.example.a2d_golf.LevelData
import com.example.a2d_golf.R
import com.example.a2d_golf.Vector2
import com.example.a2d_golf.consts.MiscConst
import kotlinx.coroutines.delay


@Composable
fun GameView(
    viewModel: GameViewModel = viewModel(),
    levelData: LevelData = LevelData(),
    miscConst: MiscConst = MiscConst()
) {
    val gameState by viewModel.gameState.collectAsState()
    val bState by viewModel.bState.collectAsState()
    val arrowModel by viewModel.movementArrowState.collectAsState()
    var prevTime : Long

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
        DrawMap(levelData.firstLevel)
        MovementArrow(modifier = Modifier, arrowState = arrowModel)
        Ball(modifier = Modifier, ballState = bState)
}

