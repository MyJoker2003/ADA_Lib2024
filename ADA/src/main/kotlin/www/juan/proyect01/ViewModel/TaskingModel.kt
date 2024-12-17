package www.juan.proyect01.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import www.juan.proyect01.ConRep.DBConnection
import www.juan.proyect01.Model.Task
import java.sql.Connection
import java.sql.SQLException

class TaskingModel : ViewModel() {
    private var _taskList = mutableStateOf(mutableListOf<Task>())
    val taskListState : State<MutableList<Task>> = _taskList
    private var _selections =  mutableStateOf(mutableListOf<Task>())
    val selectedListState: State<MutableList<Task>> = _selections
    var selectList = mutableStateOf("")
    var totalTime = mutableStateOf("")
    var totalUtility = mutableStateOf("")
    var notificaciones = mutableStateOf("")
    val limit = 5

    init {
        /*_taskList.value.add(Task(1,1,10.0))
        _taskList.value.add(Task(2,2,15.0))
        _taskList.value.add(Task(3,3,40.0))
        _taskList.value.add(Task(4,5,50.0))
        _taskList.value.add(Task(5,2,25.0))*/
        _taskList.value = this.getTasks()

    }
    fun refresh(){
        this._taskList.value = this.getTasks()
    }
    fun getBestCombination(){
        val tareas = this._taskList.value
        if (tareas.size > 0){
            try {
                val dp = fillDP(tareas)
                _selections.value= backTrackResult(dp,tareas.size)
                this.selectList.value = this._selections.value.getList()
                this.totalTime.value=this._selections.value.getTotalDuracion().toString()
                this.totalUtility.value=this._selections.value.getTotalUtility().toString()
                println(this.selectList.value)
                println(this.totalTime.value)
                println(this.totalUtility.value)
            }catch (ex: Exception){
                ex.printStackTrace() // O bien, puedes imprimir solo el mensaje de la excepción
                println("Error: ${ex.message}")
            }
        }else{
            println("Todo Vacio")
        }
    }

    fun fillDP(tareas: MutableList<Task>): Array<DoubleArray>{
        val dp = Array(tareas.size+1){DoubleArray(limit+1)}

        for (i in 1..tareas.size){
                for (tiempo in 1..limit){
                    val tiempoTareaAcual = tareas[i-1].duracion
                    if (tiempoTareaAcual <= tiempo){
                        dp[i][tiempo] = maxOf(
                            dp[i-1][tiempo], // No se incluye la tarea
                            dp[i-1][tiempo-tiempoTareaAcual] + tareas[i-1].utilidad // Se incluye la tarea
                        )
                    }else{
                        dp[i][tiempo] = dp[i-1][tiempo] //No se incluye la tarea
                    }
                }
            }
            return dp

    }
    fun backTrackResult(dp: Array<DoubleArray>, Size:Int): MutableList<Task>{
        val seleccion =  mutableListOf<Task>()
        var tiempoRestante = limit
        for (i in Size downTo 1){
            if (dp[i][tiempoRestante]!=dp[i-1][tiempoRestante]){
                seleccion.add(this._taskList.value[i-1])
                tiempoRestante -= this._taskList.value[i-1].duracion
            }
        }
        return seleccion
    }

    fun MutableList<Task>.getTotalDuracion():Int{
        var total = 0
        for (i in 0 until this.size){
            total+=this[i].duracion
        }
        return total
    }

    fun MutableList<Task>.getList(): String{
        var lista = "";
        for (i in 0 until this.size){lista+=this[i].toString()+"\n"}
        return lista;
    }

    fun MutableList<Task>.getTotalUtility():Double{
        var total =0.0
        for (i in 0 until this.size){
            total+=this[i].utilidad
        }
        return total
    }

    fun getTasks():MutableList<Task>{
        val tasks = mutableListOf<Task>()
        try {
            val conn: Connection? = DBConnection().getConnection()
            val callableStatement = conn!!.prepareCall("{ CALL getPendientes() }")

            val resultset = callableStatement.executeQuery()
            while (resultset.next()){
                val tarea = Task(
                    id = resultset.getInt("id_tarea"),
                    duracion = resultset.getInt("duracion"),
                    utilidad = resultset.getDouble("utilidad")
                )
                tasks.add(tarea)
            }
            resultset.close()
            callableStatement.close()
            conn.close()
        }catch (ex: Exception){
            ex.printStackTrace()
        }
        return tasks
    }

    fun finishTasks(){
        if (_selections.value.isNotEmpty()){
            try{
                val conn:Connection? = DBConnection().getConnection()
                val callableStatement = conn!!.prepareCall("{ CALL finishTask(?) }")
                for (item in 0 until _selections.value.size) {
                    callableStatement.setInt(1,selectedListState.value[item].id)
                    callableStatement.executeUpdate()
                }
                callableStatement.close()
                conn.close()
                this.notificaciones.value="Procedimiento EXITOSO"
                this.selectList.value=""
                this.totalTime.value=""
                this.totalUtility.value=""
                this._taskList.value = getTasks()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }else{
            this.notificaciones.value = "No se ha generado alguna combinacion de tareas"
        }
    }
}

class InsertingModel : ViewModel(){
    //var newID = mutableStateOf("")
    var duracion = mutableStateOf("")
    var utility = mutableStateOf("")
    var msj = mutableStateOf("")
    var exito = mutableStateOf(false)

    fun Insert(){
        println("ssss")
        if (duracion.value!="" && utility.value!=""){
            try {
                val duration: Int = this.duracion.value.toInt()
                val ganancia: Double = this.utility.value.toDouble()
                try {
                    val conn: Connection? = DBConnection().getConnection()
                    val callableStatement = conn!!.prepareCall("{ CALL newTask(?,?) }")
                    callableStatement.setInt(1, duration)
                    callableStatement.setDouble(2, ganancia)
                    callableStatement.executeUpdate()
                    msj.value = "Tarea Ingresada EXITOSAMENTE"
                    exito.value=true
                    callableStatement.close()
                    conn.close()
                } catch (e: SQLException) {
                    e.printStackTrace()
                    msj.value = "Entrada Incorrecta/" +
                            "Solo se aceptan valores numericos"
                }

            } catch (e: Exception) {
               msj.value = "Ingresa valores numericos"
            }
        }else{
            msj.value = "Debes llenar todos los campos para continuar..."
            println("cslkd,")
        }
    }
}