package bz.oron.rxgithub.ui.search

import android.arch.lifecycle.*
import bz.oron.rxgithub.models.GitHubUserSearch
import bz.oron.rxgithub.repositories.IGitHubRepository
import javax.inject.Inject

/**
 * Created by oron on 1/8/18.
 */
class SearchViewModel @Inject constructor(private val gitHubRepository: IGitHubRepository): ViewModel() {
  val items: LiveData<List<SearchItemViewModel>>

  private var query = MutableLiveData<String?>()

  init {
    val searchResults = Transformations.switchMap(query, {
      if (it != null && it.isNotBlank()) {
        gitHubRepository.searchUsers(it)
      } else {
        MutableLiveData()
      }
    })

    val resultItems = Transformations.map(searchResults, {
      it?.users?.map {
        SearchItemViewModel(it.username, it.avatarUrl)
      } ?: emptyList()
    })

    items = resultItems
  }

  fun search(query: String?) {
    this.query.value = query
  }
}