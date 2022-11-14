package com.hiremarknolan.android_party_compose.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.hiremarknolan.android_party_compose.domain.model.Server
import kotlinx.serialization.Serializable


@Serializable
data class ServerDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("distance")
    val distance: Int
)

fun ServerDto.toServer(): Server {
    return Server(
        name = name,
        distance = distance
    )
}