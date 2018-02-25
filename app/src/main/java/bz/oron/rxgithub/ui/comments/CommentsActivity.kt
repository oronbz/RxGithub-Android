package bz.oron.rxgithub.ui.comments

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import bz.oron.rxgithub.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class CommentsActivity : DaggerAppCompatActivity() {

  companion object {
    private val USERNAME_EXTRA: String = "USERNAME_EXTRA"

    fun createIntent(context: Context, username: String): Intent {
      val intent = Intent(context, CommentsActivity::class.java)
      intent.putExtra(USERNAME_EXTRA, username)
      return intent
    }
  }

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var viewModel: CommentsViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_comments)

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(CommentsViewModel::class.java)

    supportActionBar?.title = "Comments"

    commentsRv.layoutManager = LinearLayoutManager(this)

    commentsRv.adapter = CommentsAdapter(listOf("Comment 1", "Comment 2", "Comment 3"))
  }
}
