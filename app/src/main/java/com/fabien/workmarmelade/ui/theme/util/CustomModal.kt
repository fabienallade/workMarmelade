package com.fabien.workmarmelade.ui.theme.util

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fabien.workmarmelade.ui.theme.BackgroundNew1
import com.fabien.workmarmelade.ui.theme.ProgressBackground
import com.fabien.workmarmelade.ui.theme.TextColor
import com.fabien.workmarmelade.ui.theme.fontSansFamily

@Composable
fun CustomModal(modifier: Modifier = Modifier,
                navController: NavController,
                openDialogCustom: MutableState<Boolean>,) {
    Card(shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(10.dp, 5.dp, 10.dp, 10.dp)
            .shadow(elevation = 1.dp),
    elevation = 8.dp) {
        Column(modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()) {
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Tu es à 50% des citations",
                    textAlign = TextAlign.Center,
                    fontFamily = fontSansFamily,
                modifier=Modifier.padding(top = 5.dp),
                    fontSize = 20.sp, color = BackgroundNew1)
                Text(text = "Tu as bientôt fini",
                textAlign= TextAlign.Center, fontFamily = fontSansFamily,
                modifier = Modifier.padding(top = 10.dp, start = 25.dp, end = 25.dp),
                fontSize = 17.sp, color = TextColor)
            }
            Spacer(modifier = Modifier.height(55.dp))
            Row(modifier= Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),) {
                OutlinedButton(onClick = { /*TODO*/
                                         openDialogCustom.value = false
                    navController.navigate("afterEnd")
                                         },
                    border = BorderStroke(0.5.dp, TextColor),
                    modifier = Modifier.fillMaxWidth(0.5f),
                    shape = RoundedCornerShape(bottomStart = 10.dp)) {
                    Text(text = "Arrêter",
                        color = TextColor,
                         fontSize = 18.sp,fontFamily = fontSansFamily,)
                }
                OutlinedButton(onClick = { /*TODO*/
                                         openDialogCustom.value = false},
                    border = BorderStroke(0.5.dp, TextColor),
                    modifier = Modifier.fillMaxWidth(1f),
                    shape = RoundedCornerShape(bottomEnd = 10.dp)
                ) {
                    Text(text = "Continuer",fontFamily = fontSansFamily,
                        color = TextColor,
                    fontSize = 18.sp)
                }

            }
        }

    }
}
@SuppressLint("UnrememberedMutableState")
@Preview(name="Custom Dialog")
@Composable
fun MyDialogUIPreview(){
    val navController = rememberNavController()
    CustomModal(openDialogCustom = mutableStateOf(true),
        navController = navController)
}