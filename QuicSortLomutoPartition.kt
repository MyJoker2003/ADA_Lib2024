package PlayingGround

import java.util.*
import javax.swing.text.Highlighter

fun main(){
    println("QuickSort Lomutto Partition by Anastasio Salas Juan Carlos")

    val Lista = mutableListOf<Int>(13,45,67,32,90,34,50,50)
    print("Lista Original:")
    Lista.PrintAll()

    Lista.sort(0,Lista.size-1)
    print("\nLista Ordenada?:")
    Lista.PrintAll()
}

fun <T:Comparable<T>> MutableList<T>.sort(low:Int,high: Int){
    if (low<high){
        val p = this.LomutoPartition(low,high)
        this.sort(low,p-1)
        this.sort(p+1,high)
    }
}

fun <T:Comparable<T>> MutableList<T>.LomutoPartition(low:Int,high:Int) : Int{
    val pivot = this[high]
    var i = low
    for (j in low until high){
        if (this[j] <= pivot){
            Collections.swap(this,i,j)
            i++
        }
    }
    Collections.swap(this,i,high)
    return i
}

fun <T:Comparable<T>> MutableList<T>.PrintAll(){
    for (x in 0 until this.size){
        print("${this[x]} ")
    }
}