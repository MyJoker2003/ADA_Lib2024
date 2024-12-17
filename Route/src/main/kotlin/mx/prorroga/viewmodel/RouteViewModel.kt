package mx.prorroga.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import mx.prorroga.model.Tsp
import mx.tecnm.cdmadero.AdjacencyListGraph
import mx.tecnm.cdmadero.Vertex

class RouteViewModel : ViewModel() {
    //En esta parte se utiliza otra con nombre ListGraph MODIFICAR a posteriori
    private var _graph =  mutableStateOf(AdjacencyListGraph<String>())
    val graphState: State<AdjacencyListGraph<String>> = _graph

    //private var _route = mutableStateOf(mutableListOf<String>())
    //val route : State<MutableList<String>> = _route
    private var _route = mutableStateOf("")
    val route: State<String> = _route
    private var _cities = mutableStateOf("")
    val  cities : State<String> = _cities
    private var _costos = mutableStateOf("")
    val  costs : State<String> = _costos
    var startCity = mutableStateOf("")

    init {
        //Crear los vertices de la ciudades
        val cityA = _graph.value.addVertex("Urugay")
        val cityB = _graph.value.addVertex("Panuco")
        val cityC = _graph.value.addVertex("Coahuila")
        val cityD = _graph.value.addVertex("Campeche")

        // Definir los caminos existentes con sus pesos o costos de viaje
        _graph.value.addUndirectedEdge(cityA, cityB, 10.0)
        _graph.value.addUndirectedEdge(cityA, cityC, 15.0)
        _graph.value.addUndirectedEdge(cityA, cityD, 20.0)
        _graph.value.addUndirectedEdge(cityB, cityC, 35.0)
        _graph.value.addUndirectedEdge(cityB, cityD, 25.0)
        _graph.value.addUndirectedEdge(cityC, cityD, 30.0)

        var msj = ""
        val graph = _graph.value
        graph.adjacencyList.keys.toList().forEach{ elem->
            msj+="Ciudad (${elem.data}) ->"
            graph.adjacencyList[elem]!!.forEach { item ->
                msj+="${item.destination.data}, "
            }
            msj = msj.dropLast(1)
            msj+="\n"
        }
        this._cities.value=msj
    }
    fun startTravel(){
        if (this.startCity.value.isNotBlank()){
            try {
                val solution: Tsp = Tsp(_graph.value)
                val (minCost,optimalPath) = solution.tspDynamic(Vertex(startCity.value))
                this._costos.value = minCost.toString()
                val msj = optimalPath.joinToString("-> ")
                this._route.value = msj
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}