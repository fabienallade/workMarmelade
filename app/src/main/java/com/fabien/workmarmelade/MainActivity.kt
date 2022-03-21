package com.fabien.workmarmelade

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import appolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.fabien.workmarmelade.model.RandomQuote
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

    val percentagePerEvolve = 10
    var percentage = remember{mutableStateOf<Int>(0)}
    val phraseEvolution = listOf<String>("Citation suivante","finir")
    val imagePhase = listOf<Int>(R.drawable.smiley_sick,R.drawable.smiley_meh,R.drawable.smiley_awe)
    var imageChange : MutableState<Int> =remember{mutableStateOf<Int>(0)}
    var buttonChange : MutableState<Int> =remember{mutableStateOf<Int>(0)}
    var data  = remember{
        mutableStateOf<RandomQuoteQuery.RandomQuote>(RandomQuoteQuery.RandomQuote("1","fabien","fabien"))
    }
    LaunchedEffect(key1 = data,){
        data.value = quoteWork()
    }

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
            Row(modifier = Modifier.padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "${data.value?.author}", fontSize = 38.sp,
                        fontFamily = fontSansFamily,
                        fontWeight = FontWeight.Bold, color = Color.White)
                    Text(text = "${data.value?.quote}",
                        fontFamily = fontSansFamily,fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
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
                Image(painterResource(id = imagePhase[imageChange.value]),
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
//                        Text(text = "${percentage.value}%", fontSize = 38.sp,
//                        fontWeight = FontWeight.Bold, color = TextColor,
//                        )
//                        Text(text = " des citations", fontSize = 20.sp, color = TextColor)
                        Text(
                            buildAnnotatedString {
                                withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                                    withStyle(style = SpanStyle(color = TextColor
                                        ,fontFamily = fontSansFamily,fontWeight = FontWeight.Bold,fontSize = 38.sp,)) {
                                        append("${percentage.value}% ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            color = TextColor,
                                            fontSize = 20.sp,
                                            fontFamily = fontSansFamily,
                                        )
                                    ) {
                                        append("des citations\n")
                                    }
                                }
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(31.dp))
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 13.dp)) {
                    Text(text = "Votre progression",fontFamily = fontSansFamily,color= TextColor, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(25.dp))
                    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.fillMaxWidth(0.9f)) {
                            CustomProgressBar(
                                Modifier
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .height(14.dp),
                                400.dp,
                                ProgressBackground,
                                BackgroundNew1,
                                percentage.value,)
                        }
                        Column(modifier = Modifier.fillMaxWidth(0.1f)) {
                            Image(painterResource(id = R.drawable.picto_etoile),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(18.dp)
                                    .height(17.dp))
                        }
                    }
                    Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "10 Citations", fontSize = 13.sp,fontFamily = fontSansFamily,)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(onClick = {

                        },
                            colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor),
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(50)
                                )
                                .background(color = ButtonColor)) {
                            Text(text = "${phraseEvolution[buttonChange.value]}",
                                fontFamily = fontSansFamily,
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

 suspend fun quoteWork(): RandomQuoteQuery.RandomQuote {
    val response =  try {
       appolloClient.query(RandomQuoteQuery()).execute()
    }catch (e:ApolloException){
        Log.d("LaunchList", "Error ${e}")
        null
    }
    Log.d("fabien","fabien ${response?.data}")
     return (response?.data?.randomQuote ?: RandomQuoteQuery.RandomQuote("","",""))
}