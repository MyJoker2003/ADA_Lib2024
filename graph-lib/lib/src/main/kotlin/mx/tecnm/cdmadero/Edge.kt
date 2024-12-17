package mx.tecnm.cdmadero

data class Edge<T>(
    val source:Vertex<T>,
    val destination:Vertex<T>,
    val weigth:Double? = null
)