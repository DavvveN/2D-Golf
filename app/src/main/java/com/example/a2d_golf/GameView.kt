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
    DrawOutline(levelData.firstLevel)
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
fun DrawOutline(level : List<Drawable>){
    Canvas(modifier = Modifier.fillMaxSize()) {

        for(d in level){
            if(d is Line ){
                drawLine(
                    start = Offset(x = d.xStart, y = d.yStart),
                    end = Offset(x = d.xEnd, y = d.yEnd),
                    color = Color.Red,
                    strokeWidth = 3f
                )
            }else if(d is Circle){
                translate (left = d.leftTranslate, top = d.topTranslate){
                    drawCircle(Color.Red, radius = d.radius.dp.toPx())
                }
            }
        }


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

