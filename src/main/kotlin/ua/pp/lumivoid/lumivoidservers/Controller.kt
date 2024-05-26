package ua.pp.lumivoid.lumivoidservers

import org.springframework.web.bind.annotation.*


@RestController
class Controller {
    @GetMapping("/")
    fun index(): String {
        return "What are you looking for here? It's server application! Leave please!"
    }

    @GetMapping("/a")
    fun a(): String {
        val sql = "SELECT * FROM `account`"
        val result = DBconnection.sql(sql)
        return result.toString()


//        val query = "SELECT * FROM account"
//        resultSet = statement.executeQuery(query)
//
//        while (resultSet.next()) {
//            val id = resultSet.getInt("id")
//            val name = resultSet.getString("email")
//            println("ID: $id, Name: $name")
//        }
    }

//    @PostMapping("/registerUser")
//    fun registerUser(
//        @RequestParam username: String,
//        @RequestParam password: String,
//        @RequestParam email: String
//    ): String {
//        val account = Account()
//
//        val registationKey = email + username + password
//        val registationKeyHash: String = MessageDigest.getInstance("SHA-256").digest(registationKey.toByteArray(Charsets.UTF_8)).toString()
//        val passwordHash: String = MessageDigest.getInstance("SHA-256").digest(password.toByteArray(Charsets.UTF_8)).toString()
//
//
//        //val sql = "INSERT INTO `account`(`username`, `password`, `email`, `registrationkey`, `registrationtime`) VALUES ('${username}', '${passwordHash}', '${email}', '${registationKeyHash}', '${LocalTime.now()}')"
//
//        return accountRepository!!.sql(sql).toString()
//    }
}