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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun GameView(
    viewModel: GameViewModel = viewModel(),
    levelData: LevelData = LevelData()
){
    val gameState by viewModel.gameState.collectAsState()

    Background();
    DisplayLevelImage(modifier = Modifier, gameState, levelData);
    //DisplayFlag();
    DrawLine()
}

@Composable
fun Background(modifier : Modifier = Modifier){
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun DisplayLevelImage(
    modifier: Modifier = Modifier,
    gameState: GameState,
    levelData: LevelData){
    Column{
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Ball(gameState.ballState, -850f, 200f);
            Image(
                painter = painterResource(id = R.drawable.firstmap),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
            /**
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
            ) {
                //TODO get current level - Not just first
                for (comp in levelData.firstLevel){
                    LevelPart(comp);
                }

            }
            **/
        }
    }
}
@Composable
fun DrawLine(){
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawLine(
            start = Offset(x = 0f, y = 735f),
            end = Offset(x = 565f, y = 735f),
            color = Color.Red,
            strokeWidth = 3f
        )
        drawLine(
            start = Offset(x = 565f, y = 735f),
            end = Offset(x = 852f, y = 520f),
            color = Color.Red,
            strokeWidth = 3f
        )
        translate(left = -271f, top = 72f) {
            drawCircle(Color.Red, radius = 10.dp.toPx())
        }

        drawLine(
            start = Offset(x = 885f, y = 534f),
            end = Offset(x = 885f, y = 735f),
            color = Color.Red,
            strokeWidth = 3f
        )

        drawLine(
            start = Offset(x = 885f, y = 735f),
            end = Offset(x = 1176f, y = 735f),
            color = Color.Red,
            strokeWidth = 3f
        )

        drawLine(
            start = Offset(x = 1176f, y = 735f),
            end = Offset(x = 1176f, y = 475f),
            color = Color.Red,
            strokeWidth = 3f
        )
        translate(left = 62f, top = 0f) {
            drawCircle(Color.Red, radius = 7.dp.toPx())
        }
        drawLine(
            start = Offset(x = 1200f, y = 456f),
            end = Offset(x = 1544f, y = 456f),
            color = Color.Red,
            strokeWidth = 3f
        )
        translate(left = 420f, top = 0f) {
            drawCircle(Color.Red, radius = 7.dp.toPx())
        }
        drawLine(
            start = Offset(x = 1568f, y = 465f),
            end = Offset(x = 1568f, y = 825f),
            color = Color.Red,
            strokeWidth = 3f
        )
        drawLine(
            start = Offset(x = 1568f, y = 825f),
            end = Offset(x = 1798f, y = 825f),
            color = Color.Red,
            strokeWidth = 3f
        )
        drawLine(
            start = Offset(x = 1798f, y = 825f),
            end = Offset(x = 1798f, y = 695f),
            color = Color.Red,
            strokeWidth = 3f
        )
        translate(left = 689f, top = 228f) {
            drawCircle(Color.Red, radius = 9.dp.toPx())
        }

        drawLine(
            start = Offset(x = 1825f, y = 677f),
            end = Offset(x = canvasWidth, y = 677f),
            color = Color.Red,
            strokeWidth = 3f
        )

    }
}

@Composable
fun DisplayFlag(modifier: Modifier = Modifier){
    Column{
        Box(){
            Image(
                painter = painterResource(R.drawable.flagga),
                contentDescription = null,
                modifier = modifier
                    .fillMaxSize(0.3f)
            )
        }
    }
}

@Composable
fun LevelPart(views : List<GridComponent>){

    //TODO - last GridComponent cover the rest of the screen.
    val colMod = Modifier
        .fillMaxHeight()
        .border(BorderStroke(2.dp, SolidColor(Color.Red)))

    for(v in views){
        if(v.getIsLast()){
            colMod.padding(end = 10.dp)
        }
    }
    Column(
        modifier = colMod,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        for(v in views){
            var imageModifier = Modifier
            if(v.getIsLast()){
                imageModifier.fillMaxWidth()
            }
            Image(
                painter = painterResource(LocalContext.current.resources.getIdentifier(v.getId(), "drawable", LocalContext.current.packageName)),
                contentDescription = null,
                modifier = imageModifier
            )

            //TODO - handle collision and check what shape it is
        }
    }

}
