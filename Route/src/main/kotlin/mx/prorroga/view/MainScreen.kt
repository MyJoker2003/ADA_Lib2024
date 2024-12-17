package mx.prorroga.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.prorroga.viewmodel.RouteViewModel

@Composable
@Preview
fun App() {
    var viewModel:RouteViewModel = viewModel()
    var from by viewModel.startCity
    var buttonText = "Ir"

    MaterialTheme{
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text="Traveling Salesman Person (TSP)",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text=viewModel.cities.value
            )
            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = from,
                onValueChange = {from = it},
                label = { Text(text = "Inicio") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Spacer(modifier = Modifier.padding(8.dp))

            Button(onClick = {viewModel.startTravel()}){
                Text(buttonText)
            }
            Text("Ruta Optima: ${viewModel.route.value}")
            Spacer(modifier = Modifier.padding(8.dp))
            Text("Costo Total: ${viewModel.costs.value}")
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
