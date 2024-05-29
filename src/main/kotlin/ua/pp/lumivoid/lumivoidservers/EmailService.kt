package ua.pp.lumivoid.lumivoidservers

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailService(private val mailSender: JavaMailSender) {
    fun sendRegEmail(to: String, subject: String, confirmCode: String) {
        val message = this.mailSender.createMimeMessage()
        val messageHelper = MimeMessageHelper(message)

        val htmlText = this.javaClass.getResource("/emails/register_email.html")!!.readText().replace("#####", "confirmCode=$confirmCode")

        messageHelper.setFrom(Dotenv.load().get("EMAIL"))
        messageHelper.setTo(to)
        messageHelper.setSubject(subject)
        messageHelper.setText(htmlText, true)

        mailSender.send(message)
    }

    fun sendPassEmail(to: String, subject: String, confirmCode: String) {
        val message = this.mailSender.createMimeMessage()
        val messageHelper = MimeMessageHelper(message)

        val htmlText = this.javaClass.getResource("/emails/change_password_email.html")!!.readText().replace("#####", "passCode=$confirmCode")

        messageHelper.setFrom(Dotenv.load().get("EMAIL"))
        messageHelper.setTo(to)
        messageHelper.setSubject(subject)
        messageHelper.setText(htmlText, true)

        mailSender.send(message)
    }
}