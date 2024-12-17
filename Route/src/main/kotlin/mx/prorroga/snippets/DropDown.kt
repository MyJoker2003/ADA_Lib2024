package mx.prorroga.snippets

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import kotlin.math.exp

@Composable
@Preview
fun App() {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Seleccione una ciudad") }
    var selectedIndex by remember { mutableStateOf(-1) }
    var displayIndex by remember { mutableStateOf(-1) }
    val options = listOf("Opción 1", "Opción 2", "Opción 3", "Opción 4")

    MaterialTheme{
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Column(modifier = Modifier.padding(10.dp).fillMaxWidth(0.5f).height(60.dp)) {
                Text(
                    text = selectedOption,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(onClick = { expanded = true })
                        .padding(16.dp)
                        .background(MaterialTheme.colors.surface)
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    options.forEachIndexed { index, option ->
                        DropdownMenuItem(onClick = {
                            selectedOption = option
                            selectedIndex = index
                            expanded = false
                        }) {
                            Text(text = option)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                displayIndex = selectedIndex
            }) {
                Text("Mostrar indice seleccionado")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Indice seleccionado: ${if (displayIndex != -1) displayIndex else "Ninguno"} ")
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
