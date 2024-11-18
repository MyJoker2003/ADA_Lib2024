package EjerciciosBasicos

fun main(){
    val primos: MutableList<Int> = mutableListOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)
    println(primos)
    val num: Int = 53
    if(isThereManual(num,primos)) println("El numero $num EXISTe en las lista Primos");
    else println("El numero $num NO existe en las lista Primos");
    if(isThereAuto(num,primos)) println("El numero $num EXISTE en las lista Primos");
    else println("El numero $num NO existe en las lista Primos");
    if(isThereAuto2(num,primos)) println("El numero $num EXISTE en las lista Primos");
    else println("El numero $num NO existe en las lista Primos");
}

fun isThereManual(elemento:Int, lista:List<Int>) : Boolean{
    return lista.indexOf(elemento) >-1
}

fun isThereAuto(elemento:Int, lista:List<Int>) : Boolean{
    return lista.contains(elemento)
}

fun isThereAuto2(elemento:Int, lista:List<Int>) : Boolean{
    val positives = lista.filter { it == elemento}
    return positives.count()>0
}