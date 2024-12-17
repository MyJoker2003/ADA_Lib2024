package mx.prorroga.model

import mx.tecnm.cdmadero.AdjacencyListGraph
import mx.tecnm.cdmadero.Vertex

//Aqui tambie es solo ListGraph
class Tsp(//Traverler salesman problem
    val graph : AdjacencyListGraph<String>
) {
    fun getRouteGreedy(start:Vertex<String>):List<Vertex<String>>{
        val route = ArrayList<Vertex<String>>()
        var current=start
        val size = graph.numberOfVertices()
        var n=0
        route.add(current!!)
        while (n < size) {
            var min = Double.MAX_VALUE//Obtiene el valor maximo para un Double
            var temp:Vertex<String>? = null
            for (edge in graph.edges(current)){
                /*if (edge.weight!! < min && !route.contains(edge.destination)){
                    temp = edge.destination
                    min = edge.weight!!
                }*/
            }
            if (temp != null) current = temp!!
            else break
            route.add(current!!)
            n++
        }
        return route
    }

    fun tspDynamic(startCity: Vertex<String>): Pair<Double, List<String>> {
        val cityToIndex = this.graph.adjacencyList.keys.mapIndexed{index, vertex ->
            vertex.data to index
        }.toMap()

        val cities = this.graph.adjacencyList.keys.toList()
        val n = cities.size

        val memo = mutableMapOf<Pair<Int,Int>,Double>()
        val parent = mutableMapOf<Pair<Int,Int>,Int>() //Se usara para rastrear el camino optimo

        fun dp(visited: Int, currentCityIndex:Int):Double{
            if (visited == (1 shl n) - 1){
                val returnEdge = graph.edging(cities[currentCityIndex]).firstOrNull(){
                    it.destination.data == startCity.data
                }
                return returnEdge?.weigth ?:Double.MAX_VALUE
            }
            val key = visited to currentCityIndex
            if (key in memo) return memo[key]!!

            var minCost = Double.MAX_VALUE
            for (nextCityIndex in cities.indices) {
                if ((visited and (1 shl nextCityIndex)) == 0) {
                    val edge = this.graph.edging(cities[currentCityIndex]).firstOrNull{
                        it.destination.data == cities[nextCityIndex].data
                    } ?: continue
                    val cost = edge.weigth ?: continue
                    val newVisited = visited or (1 shl nextCityIndex)

                    val newCost = cost + dp(newVisited,nextCityIndex)

                    if (newCost < minCost){
                        minCost = newCost
                        parent[key] = nextCityIndex //Aqui guardo el indice de la siguiente ciudad en el camino optimo
                    }
                }
            }
            memo[key] = minCost
            return minCost
        }

        val startIndex = cityToIndex[startCity.data]!!
        val minCost = dp(1 shl startIndex, startIndex)

        //A continuacion se reconstruye la ruta optima mediante el uso de 'parent'
        val optimalPath = mutableListOf<String>()
        var visited = 1 shl startIndex
        var currentCityIndex = startIndex
        while (true) {
            optimalPath.add(cities[currentCityIndex].data)
            val nextCityIndex = parent[visited to currentCityIndex] ?: break
            visited = visited or (1 shl nextCityIndex)
            currentCityIndex = nextCityIndex
        }
        optimalPath.add(startCity.data) //Aqui se cierra el ciclo al agregar la ciudad incial al final
        return minCost to optimalPath
    }
}