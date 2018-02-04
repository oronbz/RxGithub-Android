package bz.oron.rxgithub.models

import com.google.gson.annotations.SerializedName

/**
 * Created by oron on 2/4/18.
 */
data class GitHubUser(
    val id: Int,
    @SerializedName("login") val username: String,
    @SerializedName("avatar_url") val avatarUrl: String
)