package mx.prorroga.snippets

import mx.tecnm.cdmadero.AdjacencyListGraph
import mx.tecnm.cdmadero.Vertex


/*fun main() {
    val graph:AdjacencyListGraph<String> = AdjacencyListGraph()

    val city1= graph.addVertex("A")
    val city2= graph.addVertex("B")
    val city3= graph.addVertex("C")
    val city4= graph.addVertex("D")

    graph.addUndirectedEdge(city1,city2,10.0)
    graph.addUndirectedEdge(city1,city3,15.0)
    graph.addUndirectedEdge(city1,city4,20.0)
    graph.addUndirectedEdge(city2,city3,35.0)
    graph.addUndirectedEdge(city2,city4,25.0)
    graph.addUndirectedEdge(city3,city4,30.0)
    /*graph.addDirectedEdge(city1,city2,10.0)
    graph.addDirectedEdge(city1,city3,15.0)
    graph.addDirectedEdge(city2,city1,10.0)
    graph.addDirectedEdge(city2,city3,35.0)
    graph.addDirectedEdge(city3,city1,15.0)
    graph.addDirectedEdge(city3,city2,35.0)*/

    val (minCost,path)= tsp(graph,city1)
    println("La distancia mas cercana es: $minCost")
    println("La Ruta seguida fue: ${path.joinToString("->")}")

}

fun tsp(graph: AdjacencyListGraph<String>,startCity:Vertex<String>):Pair<Double,List<String>>{
    val cityToIndex = graph.adjacencyList.keys.mapIndexed { index, vertex ->
        vertex.data to index
    }.toMap()

    //Obtener el numero de total de ciudades
    val ciudades = graph.adjacencyList.keys.toList()
    val n = ciudades.size

    //Memoizacion
    val memo = mutableMapOf<Pair<Int,Int>,Double>()
    val pathMemo = mutableMapOf<Pair<Int, Int>, List<String>>()

    //Funcion recursiva con memoizacion
    fun dp(CdVisitadas: Int, IndexCdActual: Int): Double {
        //Si ya se han visitado todas las ciudades.
        if (CdVisitadas == (1 shl n)-1){
            /*return graph.edging(ciudades[IndexCdActual]).firstOrNull {
                it.destination.data == startCity.data
            }?.weigth ?: Double.MAX_VALUE*/
            val returnEdge = graph.edging(ciudades[IndexCdActual]).firstOrNull{it.destination.data==startCity.data}
            return returnEdge?.weigth?:Double.MAX_VALUE
        }
        val key = CdVisitadas to IndexCdActual
        if (key in memo) return memo[key]!!

        var minCost = Double.MAX_VALUE
        var bestPath: List<String> = emptyList()

        for (IndexCdSig in ciudades.indices){
            if ((CdVisitadas and (1 shl IndexCdSig))==0){
                /*val cost = graph.edging(ciudades[IndexCdActual]).firstOrNull{
                    it.destination.data == ciudades[IndexCdSig].data
                }?.weigth?:continue*/
                val edge = graph.edging(ciudades[IndexCdActual]).firstOrNull { it.destination.data ==ciudades[IndexCdSig].data }?: continue
                val cost = edge.weigth?:continue
                val newVisitated = CdVisitadas or (1 shl IndexCdSig)
                val newCost = cost + dp(newVisitated,IndexCdSig)
                //minCost = minOf(minCost,newCost)
                if (newCost < minCost){
                    minCost=newCost
                    bestPath= listOf(ciudades[IndexCdActual].data) + (pathMemo[newVisitated to IndexCdSig]?: emptyList())
                }
            }
        }
        memo[key]=minCost
        pathMemo[key]=bestPath
        return minCost
    }
    val startIndex = cityToIndex[startCity.data]!!
    val minCost = dp(1 shl startIndex,startIndex)
    val optimalPath = listOf(startCity.data)+(pathMemo[1 shl startIndex to startIndex]?: emptyList())+startCity.data
    return minCost to optimalPath
}*/

fun main() {
    // Crear un grafo de ciudades
    val graph = AdjacencyListGraph<String>()

    // Crear los vértices de las ciudades
    val cityA = graph.addVertex("Urugay")
    val cityB = graph.addVertex("Jalisco")
    val cityC = graph.addVertex("Santo Cristo")
    val cityD = graph.addVertex("Veneccia")

    // Agregar aristas (caminos entre ciudades) con pesos (costos de viaje)
    graph.addUndirectedEdge(cityA, cityB, 10.0)
    graph.addUndirectedEdge(cityA, cityC, 15.0)
    graph.addUndirectedEdge(cityA, cityD, 20.0)
    graph.addUndirectedEdge(cityB, cityC, 35.0)
    graph.addUndirectedEdge(cityB, cityD, 25.0)
    graph.addUndirectedEdge(cityC, cityD, 30.0)

    graph.adjacencyList.keys.toList().forEach { elem->
        print("Ciudad: ${elem.data}->")
        graph.adjacencyList[elem]!!.forEach { item ->
            print("${item.destination.data},")
        }
        println()
    }
    println()

    // Ejecutar el algoritmo TSP empezando desde una ciudad (por ejemplo, ciudad A)
    val (minCost, optimalPath) = tsp(graph, cityA)

    // Imprimir el costo mínimo y la ruta óptima
    println("Costo mínimo: $minCost")
    println("Ruta óptima: ${optimalPath.joinToString(" -> ")}")
}


fun tsp(graph: AdjacencyListGraph<String>, startCity: Vertex<String>): Pair<Double, MutableList<String>> {
    val cityToIndex = graph.adjacencyList.keys.mapIndexed { index, vertex ->
        vertex.data to index
    }.toMap()

    val cities = graph.adjacencyList.keys.toList()
    val n = cities.size

    val memo = mutableMapOf<Pair<Int, Int>, Double>()
    val parent = mutableMapOf<Pair<Int, Int>, Int>()  // Estructura para rastrear el camino óptimo

    fun dp(visited: Int, currentCityIndex: Int): Double {
        if (visited == (1 shl n) - 1) {
            val returnEdge = graph.edging(cities[currentCityIndex]).firstOrNull {
                it.destination.data == startCity.data
            }
            return returnEdge?.weigth ?: Double.MAX_VALUE
        }

        val key = visited to currentCityIndex
        if (key in memo) return memo[key]!!

        var minCost = Double.MAX_VALUE
        for (nextCityIndex in cities.indices) {
            if ((visited and (1 shl nextCityIndex)) == 0) {
                val edge = graph.edging(cities[currentCityIndex]).firstOrNull {
                    it.destination.data == cities[nextCityIndex].data
                } ?: continue
                val cost = edge.weigth ?: continue
                val newVisited = visited or (1 shl nextCityIndex)

                val newCost = cost + dp(newVisited, nextCityIndex)

                if (newCost < minCost) {
                    minCost = newCost
                    parent[key] = nextCityIndex  // Guarda el índice de la siguiente ciudad en el camino óptimo
                }
            }
        }

        memo[key] = minCost
        return minCost
    }

    val startIndex = cityToIndex[startCity.data]!!
    val minCost = dp(1 shl startIndex, startIndex)

    // Reconstrucción de la ruta óptima utilizando `parent`
    val optimalPath = mutableListOf<String>()
    var visited = 1 shl startIndex
    var currentCityIndex = startIndex
    while (true) {
        optimalPath.add(cities[currentCityIndex].data)
        val nextCityIndex = parent[visited to currentCityIndex] ?: break
        visited = visited or (1 shl nextCityIndex)
        currentCityIndex = nextCityIndex
    }
    optimalPath.add(startCity.data)  // Cerrar el ciclo al agregar la ciudad inicial al final

    return minCost to optimalPath
}

