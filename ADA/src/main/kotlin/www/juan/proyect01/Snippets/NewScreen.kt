package www.juan.proyect01.Snippets

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
fun MainScreen(onNavigate: () -> Unit) {
    Button(onClick = onNavigate) {
        Text("Ir a otra pantalla")
    }
}

@Composable
fun SecondScreen(onNavigateBack: () -> Unit) {
    Column {
        Text("Esta es la segunda pantalla")
        Button(onClick = onNavigateBack) {
            Text("Regresar a la primera pantalla")
        }
    }
}

@Composable
@Preview
fun App() {
    var currentScreen by remember { mutableStateOf("main") }

    when (currentScreen) {
        "main" -> MainScreen(onNavigate = { currentScreen = "second" })
        "second" -> SecondScreen(onNavigateBack = { currentScreen = "main" })
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
