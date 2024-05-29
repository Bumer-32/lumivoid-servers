package ua.pp.lumivoid.lumivoidservers

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class LumivoidServersApplication

fun main(args: Array<String>) {
	val dotenv = Dotenv.load()
	dotenv.entries().forEach { entry ->
		System.setProperty(entry.key, entry.value)
	}

	DBconnection.connect(
		"jdbc:mysql://${dotenv.get("MYSQL_HOST")}:${dotenv.get("MYSQL_PORT")}/${dotenv.get("MYSQL_DB")}",
		dotenv.get("MYSQL_USER"),
		dotenv.get("MYSQL_PASSWORD")
	)

	SpringApplication.run(LumivoidServersApplication::class.java, *args)
}