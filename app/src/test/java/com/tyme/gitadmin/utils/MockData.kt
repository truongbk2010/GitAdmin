package com.tyme.gitadmin.utils

import com.tyme.core.model.UserModel

object MockData {

    val LIST_USER_MOCK =
        Array(10) {
            UserModel(
                id = 1,
                username = "roland",
                avatarUrl = "https://avatars.githubusercontent.com/u/28?v=4",
                htmlUrl = "https://github.com/roland",
            )
        }.toList()
}
