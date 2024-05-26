package ua.pp.lumivoid.lumivoidservers

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import java.util.*


@SpringBootApplication
class LumivoidServersApplication {
	@Bean
	fun commandLineRunner(ctx: ApplicationContext): CommandLineRunner? {
		return CommandLineRunner { args: Array<String?>? ->
			println("Let's inspect the beans provided by Spring Boot:")

			val beanNames = ctx.beanDefinitionNames
			Arrays.sort(beanNames)
			for (beanName in beanNames) {
				println(beanName)
			}
		}
	}
}

fun main(args: Array<String>) {
	val dotenv = Dotenv.configure().ignoreIfMissing().load()
	dotenv.entries().forEach { entry ->
		System.setProperty(entry.key, entry.value)
	}

	SpringApplication.run(LumivoidServersApplication::class.java, *args)

	DBconnection.connect(
		"jdbc:mysql://${dotenv.get("MYSQL_HOST")}:${dotenv.get("MYSQL_PORT")}/${dotenv.get("MYSQL_DB")}",
		dotenv.get("MYSQL_USER"),
		dotenv.get("MYSQL_PASSWORD")
	)
}