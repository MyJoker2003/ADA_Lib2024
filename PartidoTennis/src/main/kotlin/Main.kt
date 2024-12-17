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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.cdmadero.tecnm.ptennis.viewmodel.viewmodel.DemoViewModel

@Composable
@Preview
fun App(viewModel:DemoViewModel= viewModel()) {

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row {
                OutlinedTextField(
                    value = viewModel.participantes.value,
                    onValueChange = {viewModel.updateParticipantes(it)},
                    //onValueChange = {viewModel.participantes.value = it},
                    label = { Text(text = "Participantes: ") }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {viewModel.updateMatrix()}){
                Text(text = "Generar")
            }
            var participante = 1
            if(viewModel.participantes.value!="") {
                Row {
                    Text(text = "p/d", modifier = Modifier.padding(6.dp))
                    for (i in 1 until viewModel.participantes.value.toInt()) {
                        Text(text = "d$i", modifier = Modifier.padding(8.dp))
                    }
                }
            }
            viewModel.matrix.value.forEach { row ->

                Row {
                    Text(text = "p${participante}", modifier = Modifier.padding(10.dp))
                    row.forEach { cell ->
                        Text(text = cell.toString(), modifier = Modifier.padding(14.dp))
                    }
                }
                participante+=1
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
