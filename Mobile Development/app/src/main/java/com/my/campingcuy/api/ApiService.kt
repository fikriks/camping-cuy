package com.my.campingcuy.api

import com.my.campingcuy.ui.model.LoginDataAccount
import com.my.campingcuy.ui.model.RegisterDataAccount
import com.my.campingcuy.ui.model.ResponseDetail
import com.my.campingcuy.ui.model.ResponseLocationStory
import com.my.campingcuy.ui.model.ResponseLogin
import com.my.campingcuy.ui.model.ResponsePagingStory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {
    @POST("register")
    fun registUser(@Body requestRegister: RegisterDataAccount): Call<ResponseDetail>

    @POST("login")
    fun loginUser(@Body requestLogin: LoginDataAccount): Call<ResponseLogin>

    @GET("stories")
    fun getLocationStory(
        @Query("size") size: Int? = null,
        @Query("location") location: Int? = 0,
        @Header("Authorization") token: String,
    ): Call<ResponseLocationStory>

    @GET("stories")
    suspend fun getPagingStory(
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
        @Query("location") location: Int? = 0,
        @Header("Authorization") token: String,
    ): ResponsePagingStory

    @Multipart
    @POST("stories")
    fun addStory(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
        @Part("lat") lat: Float?,
        @Part("lon") lon: Float?,
        @Header("Authorization") token: String
    ): Call<ResponseDetail>
}