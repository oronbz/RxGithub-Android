package bz.oron.rxgithub.livedata

import android.arch.lifecycle.LiveData
import android.util.Log
import com.google.firebase.firestore.*

/**
 * Created by oron on 2/25/18.
 */
class FirestoreQueryLiveData(private val query: Query) : LiveData<QuerySnapshot>() {

  private val listener = QueryEventListener()
  private var registration: ListenerRegistration? = null

  override fun onActive() {
    Log.d(LOG_TAG, "onActive")
    registration = query.addSnapshotListener(listener)
  }

  override fun onInactive() {
    Log.d(LOG_TAG, "onInactive")
    registration?.remove()
  }

  private inner class QueryEventListener : EventListener<QuerySnapshot> {
    override fun onEvent(snapshot: QuerySnapshot?, e: FirebaseFirestoreException?) {
      if (e != null) {
        Log.w(LOG_TAG, "Listen failed.", e)
        return
      }

      value = snapshot
    }
  }

  companion object {
    private val LOG_TAG = "QueryLiveData"
  }
}