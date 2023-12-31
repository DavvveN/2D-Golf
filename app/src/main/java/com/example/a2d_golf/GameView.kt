package com.example.a2d_golf

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun GameView(
    viewModel: GameViewModel = viewModel(),
    levelData: LevelData = LevelData(),
    miscConst: MiscConst = MiscConst()
) {
    val gameState by viewModel.gameState.collectAsState()
    val bState by viewModel.bState.collectAsState()
    var prevTime = 0L

    LaunchedEffect(Unit) {

        prevTime = System.currentTimeMillis()
        while (true) {
            val currTime = System.currentTimeMillis()
            viewModel.update(deltaTime = ((currTime - prevTime).toFloat()) / 1000)
            prevTime = currTime
            delay((miscConst.SECONDS_IN_MILLI / miscConst.FPS).toLong()) // Delay for 60 frames per second (1000 ms / 60)
        }
    }

    Background();
    DisplayLevelImage(modifier = Modifier, bState = bState);
    //DisplayFlag();
    DrawOutline(levelData.firstLevel)

}

@Composable
fun Background(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun DisplayLevelImage(
    modifier: Modifier = Modifier,
    bState: BallState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        /**
        Image(
        painter = painterResource(id = R.drawable.firstmap),
        contentScale = ContentScale.FillBounds,
        contentDescription = null,
        modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()
        )
         **/
        Ball(bState)
    }
}

@Composable
fun Ball(ballState: BallState) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            center = Offset(x = ballState.position.xPos, y = ballState.position.yPos),
            radius = ballState.RADIUS,
            color = Color.Yellow
        )
    }
}

@Composable
fun DrawOutline(level: List<Drawable>) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        for (d in level) {
            if (d is Line) {
                drawLine(
                    start = Offset(x = d.p1.x, y = d.p1.y),
                    end = Offset(x = d.p2.x, y = d.p2.y),
                    color = Color.Green,
                    strokeWidth = 10f
                )

                //TODO Design LEVEL
                if (d.p1.y == d.p2.y) {
                    drawLine(
                        start = Offset(x = d.p1.x, y = d.p1.y + 10),
                        end = Offset(x = d.p2.x, y = d.p2.y + 10),
                        color = Color(0, 100, 0),
                        strokeWidth = 10f
                    )
                }else if (d.p1.x == d.p2.x){
                    drawLine(
                        start = Offset(x = d.p1.x - 10, y = d.p1.y ),
                        end = Offset(x = d.p2.x - 10, y = d.p2.y ),
                        color = Color(0, 100, 0),
                        strokeWidth = 10f
                    )
                }
            }
        }


    }
}

@Composable
fun DisplayFlag(modifier: Modifier = Modifier) {
    Column {
        Box() {
            Image(
                painter = painterResource(R.drawable.flagga),
                contentDescription = null,
                modifier = modifier
                    .fillMaxSize(0.3f)
            )
        }
    }
}

