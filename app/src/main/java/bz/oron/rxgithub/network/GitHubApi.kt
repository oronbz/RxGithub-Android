package bz.oron.rxgithub.network

import bz.oron.rxgithub.models.GitHubUserSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by oron on 2/4/18.
 */
interface GitHubApi {
  @GET("search/users")
  fun searchUsers(@Query("q") query: String, @Query("access_token") accessToken: String): Call<GitHubUserSearch>
}