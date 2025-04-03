package com.tyme.core.network

import com.squareup.moshi.Json

data class UserDto(
    @Json(name = "id") val id: Long = 0,
    @Json(name = "login") val login: String = "",
    @Json(name = "avatar_url") val avatarUrl: String = "",
    @Json(name = "html_url") val htmlUrl: String = "",
)
