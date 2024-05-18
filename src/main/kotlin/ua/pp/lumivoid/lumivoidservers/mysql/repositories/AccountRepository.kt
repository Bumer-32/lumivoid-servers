package ua.pp.lumivoid.lumivoidservers.mysql.repositories

import org.springframework.data.repository.CrudRepository
import ua.pp.lumivoid.lumivoidservers.mysql.tables.Account

interface AccountRepository: CrudRepository<Account, Int> {

}