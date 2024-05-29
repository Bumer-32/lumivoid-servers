package ua.pp.lumivoid.lumivoidservers.tasks

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ua.pp.lumivoid.lumivoidservers.DBconnection

@Component
class clearDB {
    @Scheduled(fixedRate = 3_600_000) //in milis
    fun clearDB() {
        val sql = "DELETE FROM `account` WHERE TIMEDIFF(CURTIME(), `registrationtime`) >= '01:00:00' AND `isconfirmed` = 0"
        DBconnection.sql(sql)
    }
}