package bz.oron.rxgithub.ui.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import bz.oron.rxgithub.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : DaggerAppCompatActivity(), SearchView.OnQueryTextListener {

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var viewModel: SearchViewModel

  private lateinit var searchView: SearchView
  private lateinit var linearLayoutManager: LinearLayoutManager
  private lateinit var adapter: SearchAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_search)

    viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

    supportActionBar?.title = ""

    linearLayoutManager = LinearLayoutManager(this)
    searchRv.layoutManager = linearLayoutManager

    adapter = SearchAdapter(arrayListOf())
    searchRv.adapter = adapter

    setupBindings()

  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_search, menu)

    menu?.let {
      val menuItem = it.findItem(R.id.action_search)
      searchView = menuItem.actionView as SearchView
      searchView.queryHint = "Search for a user..."
      searchView.isIconified = false
      searchView.requestFocusFromTouch()
      searchView.maxWidth = Int.MAX_VALUE
      searchView.setOnQueryTextListener(this)
    }

    return super.onCreateOptionsMenu(menu)
  }

  private fun setupBindings() {
    viewModel.items.observe(this, Observer {
      it?.let {
        adapter.viewModels = it
        adapter.notifyDataSetChanged()
      }
    })
  }

  override fun onQueryTextChange(newText: String?): Boolean {
    return false
  }

  override fun onQueryTextSubmit(query: String?): Boolean {
    viewModel.search(query)
    return false
  }
}
