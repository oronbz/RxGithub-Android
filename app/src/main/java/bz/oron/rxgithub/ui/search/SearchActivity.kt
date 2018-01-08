package bz.oron.rxgithub.ui.search

import android.os.Bundle
import bz.oron.rxgithub.R
import dagger.android.support.DaggerAppCompatActivity

class SearchActivity : DaggerAppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_search)
  }
}
