package com.tyme.core.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET(value = "users")
    suspend fun getAllUsers(
        @Query("per_page") pageSize: Int = 20,
        @Query("since") since: Long
    ): Response<List<UserDto>>


    @GET(value = "users/{username}")
    suspend fun getUserDetail(
        @Path("username") username: String,
    ): UserDetailDto

}
