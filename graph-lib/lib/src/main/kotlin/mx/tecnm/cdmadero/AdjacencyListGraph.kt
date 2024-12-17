package mx.tecnm.cdmadero

import kotlin.math.E

class AdjacencyListGraph<T>:Graph<T> {
    val adjacencyList:HashMap<Vertex<T>, ArrayList<Edge<T>>> = HashMap()

    /*override fun addVertex(data: T): Vertex<T> {
        return Vertex(data)
    }*/

    override fun addVertex(data: T): Vertex<T> = Vertex(data)
    override fun addUndirectedEdge(source: Vertex<T>, destination: Vertex<T>, weigth: Double?) {
        this.addDirectedEdge(source,destination,weigth)
        this.addDirectedEdge(destination,source,weigth)
    }

    override fun addDirectedEdge(source: Vertex<T>, destination: Vertex<T>, weigth: Double?) {
        val edge=Edge(source,destination,weigth)
        val list: ArrayList<Edge<T>> = ArrayList()
        list.add(edge)
        if (this.adjacencyList.containsKey(source)){
            adjacencyList[source]!!.add(edge)
        }else {
            //this.adjacencyList.put(source, list)
            this.adjacencyList[source]=list
        }
    }

    override fun edges(source: Vertex<T>): ArrayList<Vertex<T>> {
        val array:ArrayList<Vertex<T>> = arrayListOf()
        this.adjacencyList[source]?.forEach { nodo->
            array.add(nodo.destination)
        }
        return array
    }

    fun numberOfVertices():Int{
        return this.adjacencyList.size
    }


    fun edging(source: Vertex<T>):ArrayList<Edge<T>>{
        return this.adjacencyList[source]!!
    }

}