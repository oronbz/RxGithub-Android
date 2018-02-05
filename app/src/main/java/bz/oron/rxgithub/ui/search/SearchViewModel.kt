package bz.oron.rxgithub.ui.search

import android.arch.lifecycle.*
import bz.oron.rxgithub.extensions.*
import bz.oron.rxgithub.repositories.IGitHubRepository
import javax.inject.Inject

/**
 * Created by oron on 1/8/18.
 */
class SearchViewModel @Inject constructor(private val gitHubRepository: IGitHubRepository): ViewModel() {
  val items: LiveData<List<SearchItemViewModel>>

  private val query = MutableLiveData<String?>()

  init {
    val searchResults = query
        .filterNull()
        .filter { it.isNotBlank() }
        .switchMap {
          gitHubRepository.searchUsers(it)
        }

    items = searchResults
        .filterNull().map {
          it.users.map {
            SearchItemViewModel(it.username, it.avatarUrl)
          }
        }
  }

  fun search(query: String?) {
    this.query.value = query
  }
}