package bz.oron.rxgithub.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import bz.oron.rxgithub.models.GitHubUserSearch
import bz.oron.rxgithub.network.GitHubApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by oron on 2/4/18.
 */
class GitHubRepository @Inject constructor(private val gitHubApi: GitHubApi): IGitHubRepository {
  companion object {
    private val ACCESS_TOKEN = "e58310d0e10d3148e7bf1d16f454d6260010836f"
  }

  override fun searchUsers(query: String): LiveData<GitHubUserSearch?> {
    val liveData = MutableLiveData<GitHubUserSearch?>()

    gitHubApi.searchUsers(query, ACCESS_TOKEN).enqueue(object : Callback<GitHubUserSearch> {
      override fun onResponse(call: Call<GitHubUserSearch>?,
          response: Response<GitHubUserSearch>?) {
        if (response != null && response.isSuccessful) {
          liveData.value = response.body()
        } else {
          liveData.value = null
        }
      }

      override fun onFailure(call: Call<GitHubUserSearch>?, t: Throwable?) {
        liveData.value = null
      }
    })

    return liveData
  }
}