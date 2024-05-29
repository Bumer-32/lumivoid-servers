package ua.pp.lumivoid.lumivoidservers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.pp.lumivoid.lumivoidservers.util.Hasher
import java.time.LocalTime
import java.time.ZoneOffset


@RestController
class Controller(private val emailService: EmailService) {

    @GetMapping("/")
    fun index(): String {
        return "What are you looking for here? It's server application! Leave please!"
    }

    @PostMapping("/login")
    fun login(@RequestBody payload: Map<String, String>): ResponseEntity<String> {
        val email: String = payload["email"].toString()
        val password: String = payload["password"].toString()

        val sql = "SELECT `username`, `password`, `isconfirmed` FROM `account` WHERE `email` = '$email'"

        println(sql)

        val result = DBconnection.sql(sql)

        if (result["isconfirmed"] == 0) {
            return ResponseEntity("Not found", HttpStatus.NOT_FOUND)
        }

        if (result.isEmpty()) {
            return ResponseEntity("Not found", HttpStatus.NOT_FOUND)
        }

        return if (result["password"] == Hasher.sha256(password)) {
            ResponseEntity(result["username"].toString(), HttpStatus.OK)
        } else {
            ResponseEntity("Wrong pass", HttpStatus.UNAUTHORIZED)
        }
    }

    @PostMapping("/confirm")
    fun confirm(@RequestBody payload: Map<String, String>): ResponseEntity<String> {
        val sql = "UPDATE `account` SET `isconfirmed` = 1 WHERE `registrationkey` = '${payload["registrationKey"]}'"

        return if (DBconnection.sql(sql)["rowsAffected"] == 1) {
            ResponseEntity("OK", HttpStatus.OK)
        } else {
            ResponseEntity("Not found", HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/registerUser")
    fun registerUser(@RequestBody payload: Map<String, String>): ResponseEntity<String> {
        val username: String = payload["username"].toString()
        val email: String = payload["email"].toString()
        val password: String = payload["password"].toString()

        val checkSql = "SELECT `id` FROM `account` WHERE `username` = '$username' OR `email` = '$email'"

        if (DBconnection.sql(checkSql).isEmpty()) {
            try {
                val registrationKey = email + username + password + LocalTime.now(ZoneOffset.UTC)
                val registrationKeyHash: String = Hasher.sha256(registrationKey)
                val passwordHash = Hasher.sha256(password)

                val sql = "INSERT INTO `account`(`username`, `password`, `email`, `registrationkey`, `registrationtime`) VALUES ('$username', '$passwordHash', '$email', '$registrationKeyHash', CURTIME())"

                if (DBconnection.sql(sql)["rowsAffected"] == 1) {
                    emailService.sendRegEmail(email, "Lumivoid registration confirm", registrationKeyHash)
                    return ResponseEntity("OK", HttpStatus.OK)
                } else{
                    return ResponseEntity("Can't add to db", HttpStatus.INTERNAL_SERVER_ERROR)
                }
            } catch (e: Exception) {
                return ResponseEntity("Unknown error", HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else {
            return ResponseEntity("Account already exists", HttpStatus.CONFLICT)
        }
    }
}