package com.fabien.workmarmelade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import appolloClient
import com.fabien.workmarmelade.ui.theme.*
import com.fabien.workmarmelade.ui.theme.WorkMarmeladeTheme
import com.fabien.workmarmelade.ui.theme.util.CustomProgressBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkMarmeladeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
//    Configuration des données a afficher par default
    var percentage = 0
    val phraseEvolution = listOf<String>("Continuer")
    val imagePhase = listOf<Int>(R.drawable.picto_etoile)
//    val response = appolloClient.query(RandomQuoteQuery()).execute().data
    Column(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    BackgroundNew1,
                    BackgroundNew2
                )
            )
        )){
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(height = 282.dp)
            ){
            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "{Auteur}", fontSize = 38.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(text = "{Citation}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )){
            Box(modifier = Modifier.fillMaxSize(),) {
                Image(painterResource(id = R.drawable.smiley_awe),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = -70.dp)
                        .height(131.dp)
                        .width(156.dp))
            }
            Column(modifier = Modifier) {
                Spacer(modifier = Modifier.height(70.dp))
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Bottom) {
                        Text(text = "30%", fontSize = 38.sp,
                        fontWeight = FontWeight.Bold, color = TextColor,
                        )
                        Text(text = " des citations", fontSize = 20.sp, color = TextColor)
                    }
                }
                Spacer(modifier = Modifier.height(31.dp))
                Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 13.dp)) {
                    Text(text = "Votre progression")
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                        CustomProgressBar(
                            Modifier
                                .clip(shape = RoundedCornerShape(10.dp))
                                .height(14.dp),
                            400.dp,
                            ProgressBackground,
                            BackgroundNew1,
                            0,)
                        Image(painterResource(id = R.drawable.picto_etoile),
                            contentDescription = null,
                            modifier = Modifier
                                .width(18.dp)
                                .height(17.dp))
                    }
                    Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "10 Citations", fontSize = 13.sp)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor),
                            modifier = Modifier.clip(
                                RoundedCornerShape(50)).background(color = ButtonColor)) {
                            Text(text = "Citation Suivante",
                            fontSize = 12.sp, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkMarmeladeTheme {
        Greeting("Android")
    }
}