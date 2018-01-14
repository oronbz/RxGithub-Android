package bz.oron.rxgithub.ui.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

/**
 * Created by oron on 1/8/18.
 */
class SearchViewModel @Inject constructor(): ViewModel() {
  val items = MutableLiveData<ArrayList<SearchItemViewModel>>()

  private var query = MutableLiveData<String?>()

  init {
    items.value = arrayListOf(SearchItemViewModel("oronbz", "https://avatars0.githubusercontent.com/u/1288090?s=460&v=4\""))
  }

  fun search(query: String?) {
    this.query.value = query
  }
}