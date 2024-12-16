package EjerciciosBasicos

//QuickSort Lumoto Partition
//Date: 24/09/2024
//Student: Anastasio Salas Juan Carlos

fun main(){
    println("QuickSort Lomutto Partition by Anastasio Salas Juan Carlos\n")
    var nums = arrayOf(40,27,70,10,2,1)
    println("Lista desordenada:")
    nums.forEach { element -> print("$element,") }
    quickSort(nums,0,nums.size-1)
    println("\n\nLista Ordenada:")
    nums.forEach { element:Int -> print("$element,")}
}

fun quickSort(nums: Array<Int>,low: Int,high: Int){
    if (low < high){
        val p = partition(nums,low,high)
        quickSort(nums,low,p-1)
        quickSort(nums,p+1,high)
    }
}

fun partition(nums: Array<Int>, low:Int, high:Int):Int{
    val pivot = nums[high]
    var i = low
    for (j in low until high){
        if (nums[j] < pivot){
            swap(nums,i,j)
            i++
        }
    }
    swap(nums,i,high)
    return i;
}

fun swap(nums:Array<Int>, i:Int,j:Int){
    val tmp = nums[i]
    nums[i]=nums[j]
    nums[j]=tmp
}