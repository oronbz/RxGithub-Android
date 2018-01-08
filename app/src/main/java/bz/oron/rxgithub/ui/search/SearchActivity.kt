package bz.oron.rxgithub.ui.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import bz.oron.rxgithub.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : DaggerAppCompatActivity() {

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var viewModel: SearchViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_search)

    viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

    setupBindings()

  }

  private fun setupBindings() {
    viewModel.title.observe(this, Observer {
      searchTitleTv.text = it
    })
  }
}
