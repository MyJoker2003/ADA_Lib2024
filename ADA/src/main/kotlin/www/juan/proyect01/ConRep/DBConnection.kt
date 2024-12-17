package www.juan.proyect01.ConRep

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DBConnection {
    private val URL="jdbc:mysql://localhost:3306/jobs"
    private val USER="root"
    private val PASSWORD = "2015anastasio"

    fun getConnection(): Connection?{
        return try {
            DriverManager.getConnection(URL,USER,PASSWORD)
        }catch (e: SQLException){
            null
        }
    }
}