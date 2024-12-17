package www.juan.proyect01.ConRep

import www.juan.proyect01.Model.Task
import java.sql.Connection
import java.sql.SQLException

fun obtenerTareas():MutableList<Task>{
    val task = mutableListOf<Task>()
    try {
        val conn:Connection? = DBConnection().getConnection()
        val callableStatement = conn!!.prepareCall("{ CALL getPendientes() }")

        val resultset = callableStatement.executeQuery()
        while (resultset.next()){
            val tarea = Task(
                id = resultset.getInt("id_tarea"),
                duracion = resultset.getInt("duracion"),
                utilidad = resultset.getDouble("utilidad")
            )
            task.add(tarea)
        }
        resultset.close()
        callableStatement.close()
        conn.close()
    }catch (ex:Exception){
        ex.printStackTrace()
    }
    return task
}

fun finish(){
    try {
        val conn:Connection? = DBConnection().getConnection()
        val callableStatement = conn!!.prepareCall("{ CALL finishTask(?) }")
        callableStatement.setInt(1,2)
        callableStatement.executeUpdate()
        println("Procedimiento almacenado ejecutado con éxito.")
        callableStatement.close()
        conn.close()
    }catch (e:SQLException){
        e.printStackTrace()
    }
}

fun main() {
    try {
        var tareas = obtenerTareas()
        for (t in 0 until tareas.size){
            println(tareas[t].toString())
        }
    }catch (e: Exception){
        e.printStackTrace()
    }

    finish()
}