package ua.pp.lumivoid.lumivoidservers.util

import java.security.MessageDigest

object Hasher {
    fun sha256(text: Any): String {
        val bytes = text.toString().toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }
}