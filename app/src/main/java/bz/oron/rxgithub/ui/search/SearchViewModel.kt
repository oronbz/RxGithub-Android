package bz.oron.rxgithub.ui.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

/**
 * Created by oron on 1/8/18.
 */
class SearchViewModel @Inject constructor(): ViewModel() {
  val title = MutableLiveData<String>()

  init {
    title.value = "Hello from SearchViewModel!s"
  }
}