package mx.cdmadero.tecnm.ptennis.viewmodel.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.cdmadero.tecnm.ptennis.viewmodel.viewmodel.DemoViewModel
import mx.cdmadero.tecnm.ptennis.viewmodel.viewmodel.ProbViewModel

@Composable
@Preview
fun App(viewModel: ProbViewModel = viewModel()) {
    val participantes by viewModel.participantes
    var calendario by viewModel.calendario
    var isEditable by remember { mutableStateOf(true) }
    var scrollState = rememberScrollState()

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Problema del Partido de Tennis")
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Row {
                OutlinedTextField(
                    value = viewModel.participantes.value,
                    //onValueChange = {viewModel.updateParticipantes(it)},
                    onValueChange = {viewModel.participantes.value = it},
                    label = { Text(text = "Participantes: ") },
                    enabled = isEditable
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Button(onClick = {
                    viewModel.display(participantes)
                    isEditable=false
                }){ Text(text = "Display") }
                Button(onClick = {
                    calendario = mutableListOf<MutableList<Int>>()
                    viewModel.participantes.value=""
                    isEditable=true
                }){
                    Text(text = "Clear")
                }
            }


            if (calendario.isNotEmpty()){
                Text(text = "Calendario", fontSize = 18.sp, modifier = Modifier.padding(top = 16.dp))
                if (participantes !=""){
                    Row {
                        Text(text = "p/d", modifier = Modifier.padding(6.dp))
                        for (i in 1 until participantes.toInt()){
                            Text(text = "d$i", modifier = Modifier.padding(8.dp))
                        }
                    }
                }
                var participante = 1
                calendario.forEach { fila->
                    Row {
                        Text(text = "p${participante}", modifier = Modifier.padding(10.dp))
                        fila.forEach { valor ->
                            Text(text = valor.toString(), modifier = Modifier.padding(14.dp))
                        }
                    }
                    participante++
                }
            }else{

            }

            /*if(viewModel.participantes.value!="") {
                Row {
                    Text(text = "p/d", modifier = Modifier.padding(6.dp))
                    for (i in 1 until viewModel.participantes.value.toInt()) {
                        Text(text = "d$i", modifier = Modifier.padding(8.dp))
                    }
                }
            }*/
            /*viewModel.matrix.value.forEach { row ->

                Row {
                    Text(text = "p${participante}", modifier = Modifier.padding(10.dp))
                    row.forEach { cell ->
                        Text(text = cell.toString(), modifier = Modifier.padding(14.dp))
                    }
                }
                participante+=1
            }*/
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
