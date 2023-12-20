package com.example.a2d_golf

import android.util.Log
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
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
}

@Composable
fun Background(modifier : Modifier = Modifier){
    Column {
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
            /**
            Ball(gameState.ballState);
            Image(
                painter = painterResource(id = R.drawable.firstmap),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
            **/

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
            ) {
                //TODO get current level - Not just first
                for (comp in levelData.firstLevel){
                    LevelPart(comp);
                }

                /**
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.first_1),
                        contentDescription = null,
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.first_3),
                        contentDescription = null
                    )
                    Image(
                        painter = painterResource(id = R.drawable.first_2),
                        contentDescription = null
                    )

                }
                **/
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

@Composable
fun LevelPart(views : List<GridComponent>){
    val colMod = Modifier
        .fillMaxHeight()
        .border(BorderStroke(2.dp, SolidColor(Color.Red)))

    for(v in views){
        if(v.getIsLast()){
            colMod.fillMaxWidth()
        }
    }
    Column(
        modifier = colMod,
        verticalArrangement = Arrangement.Bottom
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
