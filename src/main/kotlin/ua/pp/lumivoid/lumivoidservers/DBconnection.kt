package ua.pp.lumivoid.lumivoidservers

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement
import kotlin.system.exitProcess

object DBconnection {
    private var connection: Connection? = null
    private var statement: Statement? = null

    fun connect(url: String, user: String, password: String) {
        try {
            connection = DriverManager.getConnection(url, user, password)
            statement = connection?.createStatement()
        } catch (e: Exception) {
            e.printStackTrace()
            exitProcess(1)
        }
    }

    fun sql(sql: String): MutableMap<String, Any> {
        if (sql.split("\\s+".toRegex()).first() == "SELECT") {
            val resultSet = statement!!.executeQuery(sql)
            val result = mutableMapOf<String, Any>()

            val metadata = resultSet.metaData
            val columCount = metadata.columnCount

            while (resultSet.next()) {
                for (i in 1..columCount) {
                    result[metadata.getColumnName(i)] = resultSet.getObject(i)
                }
            }

            resultSet.close()

            return result
        } else {
            val rowsAffected = statement!!.executeUpdate(sql)
            val result = mutableMapOf<String, Any>()
            result["rowsAffected"] = rowsAffected
            return result
        }
    }
}