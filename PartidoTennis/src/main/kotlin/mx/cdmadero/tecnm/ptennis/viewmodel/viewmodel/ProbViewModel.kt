package mx.cdmadero.tecnm.ptennis.viewmodel.viewmodel

import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import mx.cdmadero.tecnm.ptennis.viewmodel.model.PartidoTennis
import javax.swing.JOptionPane

class ProbViewModel: ViewModel() {
    var _participantes = mutableStateOf(PartidoTennis(0))
    var participantes = mutableStateOf("")
    var calendario = mutableStateOf(mutableListOf<MutableList<Int>>())

    fun updateParticipantes(newParticipantes: String) {
        participantes.value = newParticipantes
        try{
            val numParticipantes = newParticipantes.toInt()
            if (numParticipantes>1){
                generate(numParticipantes)
            }else{
                throw IllegalArgumentException("Debe haber mas de un 1 participante.")
            }
        }catch (e: NumberFormatException){
            //Manejo de la excepcion si el valor no es un numero
            println(e.message)
        }catch (e: IllegalArgumentException){
            //Manejo de la excepcion si el numero de participante no es valido
            println(e.message)
        }catch (e: Exception){
            //Manejo generico de otros errores
            println("Error desconocido: ${e.message}")
        }
    }

    fun generate(numParticipantes:Int){
        _participantes.value = PartidoTennis(numParticipantes)
        calendario.value=_participantes.value.genCallendar()
        /*if (participantes.value.toIntOrNull() != null) {
            _participantes.value = PartidoTennis(participantes.value.toInt())
            calendario.value = _participantes.value.genCallendar()
        }*/
    }

    fun display(arg1 : String){
        try{
            val numParticipantes = arg1.toInt()
            if (numParticipantes>1){
                generate(numParticipantes)
            }else{
                throw IllegalArgumentException("Debe haber mas de un 1 participante.")
            }
        }catch (e: NumberFormatException){
            //Manejo de la excepcion si el valor no es un numero
            println(e.message)
        }catch (e: IllegalArgumentException){
            //Manejo de la excepcion si el numero de participante no es valido
            println(e.message)
        }catch (e: Exception){
            //Manejo generico de otros errores
            println("Error desconocido: ${e.message}")
        }
    }
}