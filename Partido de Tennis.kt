package EjerciciosBasicos

fun main() {
    val players = 8
    val playerList:MutableList<Int> = MutableList(players){it+1}
    //println(playerList)
    val callendar:MutableList<MutableList<Int>> = MutableList(players){ mutableListOf()}
    agendar(playerList,callendar)
    playerList.forEach { item ->
        println("${item}: ${callendar[item-1]}")
    }
}

fun agendar(playerList: MutableList<Int>,callendar:MutableList<MutableList<Int>>){
    if (playerList.size==2){
        val x  = playerList[0]
        val y = playerList[1]
        callendar[x-1].add(0,y)
        callendar[y-1].add(0,x)
        return
    }else {
        val upPlayers = playerList.subList(0, playerList.size / 2).toMutableList()
        val dwPlayers = playerList.subList(playerList.size / 2, playerList.size).toMutableList()
        var upcopy = upPlayers
        var dwcopy = dwPlayers
        for (i in 0 until upPlayers.size) {
            callendar[upPlayers[i]-1].addAll(0, dwcopy)
            dwcopy.shift()
        }
        for (j in 0 until dwPlayers.size){
            callendar[dwPlayers[j]-1].addAll(0,upcopy)
            upcopy.shift()
        }
        agendar(upPlayers,callendar)
        agendar(dwPlayers,callendar)
    }

}

fun <T : Comparable<T>> MutableList<T>.shift() {
    //val aux: MutableList<T> = mutableListOf(this[0])
    val aux2: MutableList<T> = this.subList(1, this.size).toMutableList()
    aux2.add(this[0])
    //aux2.addAll(aux)
    this.clear()
    this.addAll(aux2)
}