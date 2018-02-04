package bz.oron.rxgithub.ui.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import bz.oron.rxgithub.models.GitHubUserSearch
import bz.oron.rxgithub.network.GitHubApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by oron on 1/8/18.
 */
class SearchViewModel @Inject constructor(private val gitHubApi: GitHubApi): ViewModel() {
  val items = MutableLiveData<List<SearchItemViewModel>>()

  private var query = MutableLiveData<String?>()

  init {
    // items.value = listOf(SearchItemViewModel("oronbz", "https://avatars0.githubusercontent.com/u/1288090?s=460&v=4\""))
  }

  fun search(query: String?) {
    this.query.value = query

    if (query != null ){
      gitHubApi.searchUsers(query, "e58310d0e10d3148e7bf1d16f454d6260010836f")
          .enqueue(object : Callback<GitHubUserSearch> {
            override fun onResponse(call: Call<GitHubUserSearch>?,
                response: Response<GitHubUserSearch>?) {
              if (response != null && response.isSuccessful) {
                val count = response.body()?.count ?: 0
                Log.d("SearchViewModel", "Found $count results.")

                items.value = response.body()?.let {
                  it.users.map {
                    SearchItemViewModel(it.username, it.avatarUrl)
                  }
                }
              }
            }

            override fun onFailure(call: Call<GitHubUserSearch>?, t: Throwable?) {
              Log.d("SearchViewModel", "Failed!.")
            }
          })
    }

  }
}