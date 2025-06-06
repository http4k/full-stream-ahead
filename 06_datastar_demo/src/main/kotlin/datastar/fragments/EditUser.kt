package datastar.fragments

import datastar.User
import org.http4k.format.Moshi
import org.http4k.template.ViewModel

data class EditUser(val user: User.Saved) : ViewModel {
    val userString = Moshi.asFormatString(user)
}

