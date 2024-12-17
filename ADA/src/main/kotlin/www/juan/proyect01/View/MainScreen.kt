package www.juan.proyect01.View

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import www.juan.proyect01.ViewModel.InsertingModel
import www.juan.proyect01.ViewModel.TaskingModel

@Composable
@Preview
fun FirstScreen(onNavigate: () -> Unit) {

    val viewModel: TaskingModel = viewModel()
    var scrollState = rememberScrollState()
    Spacer(modifier = Modifier.padding(16.dp))
    Surface(
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
        color = Color(0xFFEFEFEF)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Card(
                shape = RoundedCornerShape(8.dp),
                elevation = 4.dp,
                modifier = Modifier.fillMaxWidth(0.8f).height(1000.dp)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "<< ADA: Proyecto #01 >>",
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(16.dp)
                    )
                    Divider()
                    Button(onClick = onNavigate){
                        Text("Agregar Tarea")
                    }
                    Divider()
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        text = "Lista de Tareas Pendientes:",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(16.dp)
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            item {
                                Row(modifier = Modifier.background(color = Color.Yellow)){
                                    Text("ID", modifier = Modifier.weight(1f).border(1.dp,Color.DarkGray).padding(5.dp))
                                    Text("Duracion", modifier = Modifier.weight(1f).border(1.dp,Color.DarkGray).padding(5.dp))
                                    Text("Utilidad", modifier = Modifier.weight(1f).border(1.dp,Color.DarkGray).padding(5.dp))
                                }
                                Divider()
                            }
                            /*items(conversionPairs){pair ->
                                Row {
                                    Text(pair.first.toString(), modifier = Modifier.weight(1f))
                                    Text(pair.second, modifier = Modifier.weight(1f))
                                    Text(pair.second, modifier = Modifier.weight(1f))
                                }
                                Divider()
                            }*/
                            if (viewModel.taskListState.value.size>0) {
                                items(viewModel.taskListState.value) { task ->
                                    Row {
                                        Text(task.id.toString(), modifier = Modifier.weight(1f).padding(10.dp, 0.dp))
                                        Text(
                                            task.duracion.toString(),
                                            modifier = Modifier.weight(1f).padding(10.dp, 0.dp)
                                        )
                                        Text(
                                            task.utilidad.toString(),
                                            modifier = Modifier.weight(1f).padding(10.dp, 0.dp)
                                        )
                                    }
                                }
                            }else{
                                item {
                                    Row { Text("No hay Tareas Pendientes", modifier = Modifier.weight(3f).border(1.dp, Color.DarkGray)) }
                                }
                            }
                        }
                    }
                    Button(onClick = {viewModel.getBestCombination()}){
                        Text("GET")
                    }
                    Text(
                        text = "Resultados",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(16.dp)
                    )
                    Text("Tareas Seleccionadas:\n${viewModel.selectList.value}")
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text("Tiempo Total Requerido -> ${viewModel.totalTime.value}")
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text("Utilidad Total -> ${viewModel.totalUtility.value}")
                    Spacer(modifier = Modifier.padding(16.dp))
                    Button(onClick = { viewModel.finishTasks() }){
                        Text("Agendar/Finalizar\nTareas")
                    }
                    Spacer(modifier = Modifier.padding(9.dp))
                    Text(viewModel.notificaciones.value)
                }
            }
        }
    }
}

@Composable
@Preview
fun SecondScreen(onNavigateBack: () -> Unit) {
    val viewModel: InsertingModel = viewModel()
    val vieModel2: TaskingModel = viewModel()
    val duracion = viewModel.duracion
    var utilidad = viewModel.utility
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEFEFEF)
    ){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                elevation = 4.dp,
                modifier = Modifier.fillMaxWidth(0.8f).height(400.dp)
            ){
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Nueva Tarea",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Divider()
                    Spacer(modifier = Modifier.padding(10.dp))
                    //Text("Duracion de la Tarea:")
                    OutlinedTextField(
                        value = duracion.value,
                        onValueChange = {newText -> duracion.value = newText},
                        label = { Text("Duracion de la Tarea") },
                        modifier = Modifier.fillMaxWidth(0.7f)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    //Text("Utilidad de la Tarea:")
                    OutlinedTextField(
                        value = utilidad.value,
                        onValueChange = {newText -> utilidad.value =newText},
                        label = { Text("Utilidad de la Tarea") },
                        modifier = Modifier.fillMaxWidth(0.7f)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row {
                        Button(onClick = {
                            viewModel.Insert()
                            if (viewModel.exito.value ==true){
                                vieModel2.refresh()
                                duracion.value=""
                                utilidad.value=""
                                viewModel.exito.value=false
                            }
                        }){
                            Text("Agregar")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(onClick = onNavigateBack){
                            Text("Regresar")
                        }
                    }

                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(viewModel.msj.value, style = TextStyle(fontSize = 15.sp))
                }
            }
        }
    }
    /*Column {
        Text("Esta es la segunda pantalla")
        Button(onClick = onNavigateBack) {
            Text("Regresar a la primera pantalla")
        }
    }*/
}
@Composable
@Preview
fun App() {
    var currentScreen by remember { mutableStateOf("main") }
    when (currentScreen){
        "main"-> FirstScreen(onNavigate = {currentScreen = "second"})
        "second" -> SecondScreen(onNavigateBack = {currentScreen = "main" })
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
