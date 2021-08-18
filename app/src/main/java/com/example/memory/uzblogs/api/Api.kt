package com.example.memory.uzblogs.api

import com.example.memory.uzblogs.model.PostModel
import com.example.memory.uzblogs.model.UserModel
import retrofit.Call
import retrofit.http.GET
import retrofit.http.Headers
import retrofit.http.Path

/**
 * @author user
 * @date 17.08.2021
 */
interface Api {

    @Headers("app-id:611b3647e0ac7f7327509ee5")
    @GET("user")
    fun getUsers(): Call<BaseResponse<List<UserModel>>>

    @Headers("app-id:611b3647e0ac7f7327509ee5")
    @GET("post")
    fun getPosts(): Call<BaseResponse<List<PostModel>>>

    @Headers("app-id:611b3647e0ac7f7327509ee5")
    @GET("user/{user_id}/post")
    fun getPostsByUser(
        @Path("user_id") id: String
    ): Call<BaseResponse<List<PostModel>>>
}