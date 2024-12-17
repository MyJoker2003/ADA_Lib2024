package mx.cdmadero.tecnm.ptennis.viewmodel.model

data class PartidoTennis (var CantParticipants:Int){
    fun genCallendar():MutableList<MutableList<Int>>{
        /*return MutableList(CantParticipants) { MutableList(CantParticipants-1) { 0 } }*/
        val playerList:MutableList<Int> = MutableList(CantParticipants){it+1}
        val callendar:MutableList<MutableList<Int>> = MutableList(CantParticipants){ mutableListOf()}
        agendar(playerList,callendar)
        return callendar
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
}

fun <T : Comparable<T>> MutableList<T>.shift() {
    val aux2: MutableList<T> = this.subList(1, this.size).toMutableList()
    aux2.add(this[0])
    this.clear()
    this.addAll(aux2)
}