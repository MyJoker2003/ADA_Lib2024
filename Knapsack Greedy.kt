package EjerciciosBasicos

import java.security.Principal

//TAREA: Anastasio Salas
class Item{
    val name:String
    var weigh: Int
    var value: Int
    var VxW:Double

    constructor(name:String,weigh:Int,value:Int){
        this.name=name
        this.weigh=weigh
        this.value=value
        this.VxW=value.toDouble()/weigh.toDouble()
    }
}

class KnapsackItem{
    val item:Item
    var cantidad:Double
    constructor(item: Item,cantidad:Double){
        this.item = item
        this.cantidad = cantidad
    }

    fun getTrueValue():Double{
        return this.item.value*this.cantidad
    }
}
fun main() {
    println("Knapsack Problema by Anastasio Salas Juan Carlos")
    //Se crea la lista Inicial del los items disponibles
    var ItemList:MutableList<Item> = mutableListOf()
    ItemList.add(Item("Item1",1,1))
    ItemList.add(Item("Item3",3,2))
    ItemList.add(Item("Item2",5,2))
    ItemList.add(Item("Item4",4,7))

    ItemList.forEach { element ->
        //Para cada item se imprime su razon valor/peso
        println("${element.name}->V=${element.value}/ W=${element.weigh} = ${element.VxW}")
    }
    println("")

    val OL = ItemList.quickSortMiddle() //Lista Ordenada de con base en las razones valor/peso

    println("Lista Ordenada:")
    OL.forEach { element ->
        println("Item: ${element.name} Razon: ${element.VxW}")
    }

    val Knapsack:MutableList<KnapsackItem> = mutableListOf() // Se prepara la mochila
    var espacioDisponible: Double = 10.0 //Se define la capcidad de la mochila
    var it = 0 //Iterardor
    while (espacioDisponible>0.0 && it < OL.size){ //Mientras haya espacio y me queden items fuera
        if (OL[it].weigh<espacioDisponible){
            //Si el peso del item es menor al espacio disponible se toma completo
            Knapsack.add(KnapsackItem(OL[it],1.0))
            //Se reduce el espacio disponible en proporcion al item seleccionado
            //espacioDisponible-=OL[it].weigh
        }
        else{
            val cant = espacioDisponible/OL[it].weigh
            Knapsack.add(KnapsackItem(OL[it],cant))
        }
        espacioDisponible-=OL[it].weigh
        it++
    }
    Knapsack.Show()
}

fun List<Item>.quickSortMiddle():List<Item>{
    if (this.size<2) return this
    val pivot = this[this.size/2].VxW
    val low = this.filter { it.VxW < pivot }
    val equal=this.filter { it.VxW == pivot }
    val high = this.filter { it.VxW > pivot }
    return high.quickSortMiddle()+equal+low.quickSortMiddle()
}

fun MutableList<KnapsackItem>.Show(){
    var valorTotal = 0.0
    println("\nContenido de la Mochila:")
    this.forEach { element ->
        println("Item: ${element.item.name}\t" +
                "Cantidad:${element.cantidad}\t"+
                "Valor: ${element.getTrueValue()}"
        )
        valorTotal+=element.getTrueValue()
    }
    println("\nTotal de Items: ${this.size}")
    println("Valor Acumulado: $valorTotal")
}