package bz.oron.rxgithub.ui.comments

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import bz.oron.rxgithub.models.Comment
import bz.oron.rxgithub.repositories.ICommentsRepository
import bz.oron.rxlivedata.map
import bz.oron.rxlivedata.switchMap
import javax.inject.Inject

/**
 * Created by oron on 2/25/18.
 */
class CommentsViewModel @Inject constructor(private val commentsRepository: ICommentsRepository): ViewModel() {

  // INPUT
  val username = MutableLiveData<String>()

  // OUTPUT
  val comments: LiveData<List<String>>

  init {
    comments = username.switchMap {
      commentsRepository.comments(it)
    }.map {
          it.map { it.body }
        }
  }

  fun addComment(commentBody: String) {
    val comment = Comment(commentBody)
    username.value?.let {
      commentsRepository.addComment(comment, it)
    }
  }
}