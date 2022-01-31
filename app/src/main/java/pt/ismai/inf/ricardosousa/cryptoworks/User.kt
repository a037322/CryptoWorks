package pt.ismai.inf.ricardosousa.cryptoworks

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val email: String? = null,
    val lastName: String? = null,
    val firstName: String? = null
)
