package bz.oron.rxgithub.ui.search

import android.arch.lifecycle.*
import bz.oron.rxgithub.livedata.*
import bz.oron.rxgithub.models.GitHubUserSearch
import bz.oron.rxgithub.repositories.IGitHubRepository
import bz.oron.rxlivedata.filter
import bz.oron.rxlivedata.filterNull
import bz.oron.rxlivedata.map
import bz.oron.rxlivedata.switchMap
import javax.inject.Inject

/**
 * Created by oron on 1/8/18.
 */
class SearchViewModel @Inject constructor(private val gitHubRepository: IGitHubRepository): ViewModel() {
  val items: LiveData<List<SearchItemViewModel>>
  val presentUserProfile: SingleLiveEvent<Pair<String, String>> = SingleLiveEvent()

  private val query = MutableLiveData<String?>()
  private val searchResults: LiveData<GitHubUserSearch>

  init {
    searchResults = query
        .filterNull()
        .filter { it.isNotBlank() }
        .switchMap {
          gitHubRepository.searchUsers(it)
        }.filterNull()

    items = searchResults
        .map {
          it.users.map {
            SearchItemViewModel(it.username, it.avatarUrl)
          }
        }
  }

  fun search(query: String?) {
    this.query.value = query
  }

  fun click(index: Int) {
    val users = searchResults.value?.users

    users?.let {
      val user = users[index]
      val profileData = Pair(user.username, user.avatarUrl)
      presentUserProfile.value = profileData
    }
  }
}