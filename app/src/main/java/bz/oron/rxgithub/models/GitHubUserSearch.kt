package bz.oron.rxgithub.models

import com.google.gson.annotations.SerializedName

/**
 * Created by oron on 2/4/18.
 */
data class GitHubUserSearch(
    @SerializedName("total_count") val count: Int,
    @SerializedName("items") val users: List<GitHubUser>
)