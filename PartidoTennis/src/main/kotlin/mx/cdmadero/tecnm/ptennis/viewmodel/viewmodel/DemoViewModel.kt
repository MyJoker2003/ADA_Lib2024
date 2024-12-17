package mx.cdmadero.tecnm.ptennis.viewmodel.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class DemoViewModel : ViewModel() {
    var participantes = mutableStateOf("2")
    val matrix = mutableStateOf(generateMatrix(participantes.value))

    fun updateParticipantes(newParticipantes: String) {
        participantes.value = newParticipantes
        matrix.value = generateMatrix(newParticipantes)
    }

    private fun generateMatrix(size: String): MutableList<MutableList<Int>> {
        return if (size.toIntOrNull() != null) {
            MutableList(size.toInt()) { MutableList(size.toInt()-1) { 0 } }
        } else {
            mutableListOf()
        }
    }

    fun updateMatrix(){
        matrix.value = generateMatrix(participantes.toString())
    }
}