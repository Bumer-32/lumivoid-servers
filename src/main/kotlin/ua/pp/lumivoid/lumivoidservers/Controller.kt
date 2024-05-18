package ua.pp.lumivoid.lumivoidservers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ua.pp.lumivoid.lumivoidservers.mysql.tables.Account
import ua.pp.lumivoid.lumivoidservers.mysql.repositories.AccountRepository

@RestController
class Controller {
    @Autowired
    private val accountRepository: AccountRepository? = null

    @GetMapping("/")
    fun index(): String {
        return "Greetings from Spring Boot!"
    }

    @GetMapping("/something/")
    fun something(): String {
        val accounts: Iterable<Account> = accountRepository!!.findAll()
        return accounts.toString()
    }
}