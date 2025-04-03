package com.tyme.core.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class UserModel(
    val id: Long = 0,
    val username: String = "",
    val avatarUrl: String = "",
    val htmlUrl: String = "",
): Parcelable

fun UserModel.toUserDetail() = UserDetailModel(
    id = id,
    username = username,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
)

fun UserModel.encodeUserUrl() = UserModel(
    id = id,
    username = username,
    avatarUrl = Uri.encode(avatarUrl),
    htmlUrl = Uri.encode(htmlUrl),
)

fun UserModel.decodeUserUrl() = UserModel(
    id = id,
    username = username,
    avatarUrl = Uri.decode(avatarUrl),
    htmlUrl = Uri.decode(htmlUrl),
)
