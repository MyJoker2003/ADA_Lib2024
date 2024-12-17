package mx.tecnm.cdmadero

interface Graph<T> {
    /*Se utiliza para definir un cierto comportamiento de los grafos independientemente de la forma
    de implementacion (matriz de adyacencia, lista de adyacencia)*/
    fun addVertex(data:T) : Vertex<T>
    fun addUndirectedEdge(source:Vertex<T>,destination:Vertex<T>,weigth:Double?)
    fun addDirectedEdge(source:Vertex<T>,destination:Vertex<T>,weigth:Double?)
    fun edges(source: Vertex<T>):ArrayList<Vertex<T>>

}