package bz.oron.rxgithub.repositories

import android.arch.lifecycle.LiveData
import bz.oron.rxgithub.livedata.FirestoreQueryLiveData
import bz.oron.rxgithub.models.Comment
import bz.oron.rxlivedata.map
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

/**
 * Created by oron on 2/25/18.
 */
class CommentsRepository @Inject constructor(private val db: FirebaseFirestore): ICommentsRepository {
  override fun comments(username: String): LiveData<List<Comment>> {
    val ref = db.collection("users").document(username).collection("comments")

    val doc = FirestoreQueryLiveData(ref)

    return doc.map {
      if (!it.documents.isEmpty()) {
        it.toObjects(Comment::class.java)
      } else {
        emptyList()
      }
    }
  }

  override fun addComment(comment: Comment, username: String) {
    val ref = db.collection("users").document(username).collection("comments").document()
    ref.set(comment)
  }
}