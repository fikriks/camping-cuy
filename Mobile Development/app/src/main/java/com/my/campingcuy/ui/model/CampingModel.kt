package com.my.campingcuy.ui.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CampingModel(
    val id: String,
    val name: String,
    val description: String,
    val photoUrl: String,
    val createdAt: String,
    val lat: Float?,
    val lon: Float?
) : Parcelable

data class RegisterDataAccount(
    var name: String,
    var email: String,
    var password: String
)

data class LoginDataAccount(
    var email: String,
    var password: String
)

data class ResponseDetail(
    var error: Boolean,
    var message: String
)

data class ResponseLogin(
    var error: Boolean,
    var message: String,
    var loginResult: LoginResult
)

data class LoginResult(
    var userId: String,
    var name: String,
    var token: String
)

data class ResponseLocationStory(
    @field:SerializedName("error")
    var error: String,

    @field:SerializedName("message")
    var message: String,

    @field:SerializedName("listStory")
    var listStory: List<ListStory>
)

data class ResponsePagingStory(
    @field:SerializedName("error")
    var error: String,

    @field:SerializedName("message")
    var message: String,

    @field:SerializedName("listStory")
    var listStory: List<ListStory>
)