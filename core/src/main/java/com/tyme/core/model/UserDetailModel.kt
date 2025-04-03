package com.tyme.core.model


data class UserDetailModel(
    val id: Long = 0,
    val username: String = "",
    val avatarUrl: String = "",
    val htmlUrl: String = "",
    val location: String = "",
    val followers: Long = 0,
    val following: Long = 0,
)
