package bz.oron.rxgithub.ui.comments

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import bz.oron.rxgithub.R
import bz.oron.rxgithub.ui.extensions.inflate
import kotlinx.android.synthetic.main.item_comment.view.*

/**
 * Created by oron on 2/25/18.
 */
class CommentsAdapter(var comments: List<String>): RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

  override fun onBindViewHolder(holder: CommentsAdapter.ViewHolder, position: Int) {
    val comment = comments[position]
    holder.bindComment(comment)
  }

  override fun getItemCount() = comments.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsAdapter.ViewHolder {
    val inflatedView = parent.inflate(R.layout.item_comment, false)
    return ViewHolder(inflatedView)
  }

  class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    fun bindComment(comment: String) {
      view.commentItemTv.text = comment
    }
  }

}