package www.juan.proyect01.Snippets

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun mYApp() {
    val conversionPairs = (1..15).map { decimal ->
        Pair(decimal, decimal.toString(2).padStart(8, '0'))
    }

    MaterialTheme{
        LazyColumn(
            modifier = Modifier.fillMaxWidth(0.5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Row (modifier = Modifier.background(
                    color = Color.Yellow
                )) {
                    Text("Decimal", modifier = Modifier.weight(1f).border(1.dp,Color.DarkGray).padding(10.dp))
                    Text("Binario", modifier = Modifier.weight(1f).border(1.dp,Color.DarkGray).padding(10.dp))
                }
                Divider()
            }
            items(conversionPairs) { pair ->
                Row {
                    Text(pair.first.toString(), modifier = Modifier.weight(1f))
                    Text(pair.second, modifier = Modifier.weight(1f))
                }
                Divider()
            }
        }
    }
    /*var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Button(onClick = {
            text = "Hello, Desktop!"
        }) {
            Text(text)
        }
    }*/
}
@Composable
@Preview
fun CenteredCard(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Card (
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
            modifier = Modifier.fillMaxWidth(0.8f).height(600.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                //verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text("<< ADA: Proyecto #01 >>")
                Spacer(modifier = Modifier.padding(5.dp))
                Divider()
                Button(onClick = {

                }){ Text("Agregar Tarea") }
                Divider()
                Spacer(modifier = Modifier.padding(12.dp))
                Text(
                    text = "Lista de Tareas Pendientes: ",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(16.dp)
                )

            }

        }
    }
}
@Composable
@Preview
fun theApp(){
    MaterialTheme{
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFEFEFEF)
        ) {
            CenteredCard()
        }
    }

}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        //mYApp()
        theApp()
    }
}
