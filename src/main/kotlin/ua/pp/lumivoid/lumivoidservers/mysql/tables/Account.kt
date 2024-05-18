package ua.pp.lumivoid.lumivoidservers.mysql.tables

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.sql.Time

@Entity
class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Int? = null

    private var email: String? = null

    private var username: String? = null

    private var password: BooleanArray? = null

    private var isconfirmed: Boolean? = null

    private var registationkey: BooleanArray? = null

    private var registationtime: Time? = null

    private var admin: Boolean? = null


    fun getId(): Int? { return id }
    fun setId(id: Int) { this.id = id }

    fun getEmail(): String? { return email }
    fun setEmail(email: String) { this.email = email }

    fun getUsername(): String? { return username }
    fun setUsername(username: String) { this.username = username }

    fun getPassword(): BooleanArray? { return password }
    fun setPassword(password: BooleanArray) { this.password = password }

    fun getIsconfirmed(): Boolean? { return isconfirmed }
    fun setIsconfirmed(isconfirmed: Boolean) { this.isconfirmed = isconfirmed }

    fun getRegistationkey(): BooleanArray? { return registationkey }
    fun setRegistationkey(registationkey: BooleanArray) { this.registationkey = registationkey }

    fun getRegistationtime(): Time? { return registationtime }
    fun setRegistationtime(registationtime: Time) { this.registationtime = registationtime }

    fun getAdmin(): Boolean? { return admin }
    fun setAdmin(admin: Boolean) { this.admin = admin }

}