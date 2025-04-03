package com.tyme.core.mapper

import com.tyme.core.local.UserDetailEntity
import com.tyme.core.model.UserDetailModel
import com.tyme.core.network.UserDetailDto


fun UserDetailDto.toEntity() = UserDetailEntity(
    id = id,
    username = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
    location = location,
    followers = followers,
    following = following,
)


fun UserDetailEntity.toModel(): UserDetailModel = UserDetailModel(
    id = id,
    username = username,
    avatarUrl = avatarUrl,
    location = location,
    htmlUrl = htmlUrl,
    followers = followers,
    following = following,
)