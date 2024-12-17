package www.juan.proyect01.Snippets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.Text

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        AppContent()
    }
}

@Composable
fun AppContent() {
    // Estado para controlar si el Popup está visible o no
    var showPopup by remember { mutableStateOf(false) }

    // Columna que contiene el botón para abrir el Popup
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextButton(text = "Abrir Popup") {
            showPopup = true
        }

        // Mostrar el Popup si showPopup es true
        if (showPopup) {
            PopupWindow(onDismiss = { showPopup = false })
        }
    }
}

@Composable
fun BasicTextButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.Blue)
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        BasicText(text = text, style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold))
    }
}

@Composable
fun PopupWindow(onDismiss: () -> Unit) {
    // Popup personalizado usando Box
    Popup(
        alignment = Alignment.Center,
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier
                .padding(32.dp)
                .size(200.dp, 150.dp)
                .background(Color.Gray)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {
                BasicText("Este es un Popup flotante", style = TextStyle(color = Color.White))
                Spacer(modifier = Modifier.height(16.dp))
                BasicTextButton(text = "Cerrar", onClick = onDismiss)
            }
        }
    }
}
