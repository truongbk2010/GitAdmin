package com.tyme.core.network

import com.squareup.moshi.Json

data class UserDetailDto(
    @Json(name = "id") val id: Long = 0,
    @Json(name = "login") val login: String = "",
    @Json(name = "avatar_url") val avatarUrl: String = "",
    @Json(name = "html_url") val htmlUrl: String = "",
    @Json(name = "location") val location: String = "",
    @Json(name = "followers") val followers: Long = 0,
    @Json(name = "following") val following: Long = 0,
)
