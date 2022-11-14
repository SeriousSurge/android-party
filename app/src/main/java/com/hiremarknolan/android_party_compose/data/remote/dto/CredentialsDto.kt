package com.hiremarknolan.android_party_compose.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.hiremarknolan.android_party_compose.domain.model.UserCredentials
import kotlinx.serialization.Serializable

@Serializable
data class CredentialsDto(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)

fun UserCredentials.toCredentialsDto(): CredentialsDto {
    return CredentialsDto(
        username = username,
        password = password
    )
}
