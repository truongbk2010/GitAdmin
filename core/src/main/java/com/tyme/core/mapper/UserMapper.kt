package com.tyme.core.mapper

import com.tyme.core.local.UserEntity
import com.tyme.core.model.UserModel
import com.tyme.core.network.UserDto


fun UserDto.toEntity() = UserEntity(
    id = id,
    username = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
)


fun UserEntity.toModel(): UserModel = UserModel(
    id = id,
    username = username,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
)

