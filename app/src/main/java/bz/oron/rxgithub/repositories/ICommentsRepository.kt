package bz.oron.rxgithub.repositories

import android.arch.lifecycle.LiveData
import bz.oron.rxgithub.models.Comment

/**
 * Created by oron on 2/25/18.
 */
interface ICommentsRepository {
  fun comments(username: String): LiveData<List<Comment>>
  fun addComment(comment: Comment, username: String)
}