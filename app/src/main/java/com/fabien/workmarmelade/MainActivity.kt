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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import appolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.fabien.workmarmelade.model.RandomQuote
import com.fabien.workmarmelade.ui.theme.*
import com.fabien.workmarmelade.ui.theme.WorkMarmeladeTheme
import com.fabien.workmarmelade.ui.theme.util.CustomModal
import com.fabien.workmarmelade.ui.theme.util.CustomProgressBar
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkMarmeladeTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationComponent(navController = navController)
                }
            }
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "greeting") {
        composable("greeting") {
            Greeting(navController = navController)
        }
        composable("afterEnd") {
            AfterEnd(navController = navController)
        }
    }
}

@Composable
fun AfterEnd(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize().background(
        brush = Brush.radialGradient(
            colors = listOf(
                BackgroundNew1,
                BackgroundNew2
            )
        )
    )) {
        Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Vous avez fini le travail",
                fontSize = 38.sp,
                fontFamily = fontSansFamily,
                fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = "A bientot",fontSize = 38.sp,
                fontFamily = fontSansFamily,
                fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}

@Composable
fun Greeting(navController: NavHostController) {
//    Configuration des données a afficher par default

    val percentagePerEvolve = 10
    var percentage = remember { mutableStateOf<Int>(0) }
    val phraseEvolution = listOf<String>("CITATION SUIVANTE", "FINIR")
    val imagePhase =
        listOf<Int>(R.drawable.smiley_sick, R.drawable.smiley_meh, R.drawable.smiley_awe)
    var imageChange: MutableState<Int> = remember { mutableStateOf<Int>(0) }
    var buttonChange: MutableState<Int> = remember { mutableStateOf<Int>(0) }
    var dialogOpen: MutableState<Boolean> = remember { mutableStateOf<Boolean>(false) }
    val coroutineScope = rememberCoroutineScope()
    var data = remember {
        mutableStateOf<RandomQuoteQuery.RandomQuote>(
            RandomQuoteQuery.RandomQuote(
                "0",
                "",
                ""
            )
        )
    }
    LaunchedEffect(key1 = data) {
        data.value = quoteWork()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        BackgroundNew1,
                        BackgroundNew2
                    )
                )
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 282.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${data.value?.author}", fontSize = 38.sp,
                        fontFamily = fontSansFamily,
                        fontWeight = FontWeight.Bold, color = Color.White
                    )
                    Text(
                        text = "${data.value?.quote}",
                        textAlign = TextAlign.Center,
                        fontFamily = fontSansFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painterResource(id = imagePhase[imageChange.value]),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = -70.dp)
                        .height(131.dp)
                        .width(156.dp)
                )
            }
            Column(modifier = Modifier) {
                Spacer(modifier = Modifier.height(70.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom
                    ) {
//                        Text(text = "${percentage.value}%", fontSize = 38.sp,
//                        fontWeight = FontWeight.Bold, color = TextColor,
//                        )
//                        Text(text = " des citations", fontSize = 20.sp, color = TextColor)
                        Text(
                            buildAnnotatedString {
                                withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                                    withStyle(
                                        style = SpanStyle(
                                            color = TextColor,
                                            fontFamily = fontSansFamily,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 38.sp,
                                        )
                                    ) {
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 13.dp)
                ) {
                    Text(
                        text = "Votre progression",
                        fontFamily = fontSansFamily,
                        color = TextColor,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.fillMaxWidth(0.94f)) {
                            CustomProgressBar(
                                Modifier
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .height(14.dp),
                                350.dp,
                                ProgressBackground,
                                BackgroundNew1,
                                percentage.value,
//                            50,
                            )
                        }
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Image(
                                painterResource(id = R.drawable.picto_etoile),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(17.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "10 Citations", fontSize = 13.sp, fontFamily = fontSansFamily)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                var percent = percentage.value + percentagePerEvolve
                                coroutineScope.launch {
                                    if (percent > 100) {
                                        navController.navigate("afterEnd")
                                    } else {
                                        data.value = quoteWork()
                                        percentage.value = percent
                                        if (percent <= 39 && percent >= 0) {
                                            imageChange.value = 0
                                        } else if (percent <= 79 && percent >= 40) {
                                            imageChange.value = 1
                                        } else if (percent <= 100 && percent >= 80) {
                                            imageChange.value = 2
                                        }
                                        if (percent == 50) {
                                            dialogOpen.value = true
                                        }
                                        if (percent == 100) {
                                            buttonChange.value = 1
                                        }
                                    }

                                }

                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor),
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(50)
                                )
                                .background(color = ButtonColor)
                        ) {
                            Text(
                                text = "${phraseEvolution[buttonChange.value]}",
                                fontFamily = fontSansFamily,
                                fontSize = 12.sp, color = Color.White
                            )
                        }
                    }
                }

            }

        }

        if (dialogOpen.value) {
            Dialog(onDismissRequest = { dialogOpen.value = false }) {
                CustomModal(
                    modifier = Modifier,
                    openDialogCustom = dialogOpen, navController = navController
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkMarmeladeTheme {
        val navController = rememberNavController()
        Greeting(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun EndPreview() {
    WorkMarmeladeTheme {
        val navController = rememberNavController()
        AfterEnd(navController)
    }
}

suspend fun quoteWork(): RandomQuoteQuery.RandomQuote {
    val response = try {
        appolloClient.query(RandomQuoteQuery()).execute()
    } catch (e: ApolloException) {
        Log.d("LaunchList", "Error ${e}")
        null
    }
    Log.d("fabien", "fabien ${response?.data}")
    return (response?.data?.randomQuote ?: RandomQuoteQuery.RandomQuote("", "", ""))
}