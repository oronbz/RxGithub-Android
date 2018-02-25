package bz.oron.rxgithub.ui.comments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import bz.oron.rxgithub.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_comments.*
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

  private lateinit var adapter: CommentsAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_comments)

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(CommentsViewModel::class.java)

    val username = intent.getStringExtra(CommentsActivity.USERNAME_EXTRA)

    viewModel.username.value = username

    supportActionBar?.title = "Comments"

    commentsRv.layoutManager = LinearLayoutManager(this)

    adapter = CommentsAdapter(emptyList())

    commentsRv.adapter = adapter

    setupBindings()
  }

  private fun setupBindings() {
    // INPUT

    addCommentEt.setOnEditorActionListener { _, actionId, _ ->
      if (actionId == EditorInfo.IME_ACTION_DONE) {
        viewModel.addComment(addCommentEt.text.toString())
        addCommentEt.setText("")
        true
      }
      false
    }

    // OUTPUT

    viewModel.comments.observe(this, Observer {
      it?.let {
        adapter.comments = it
        adapter.notifyDataSetChanged()
      }
    })
  }
}
