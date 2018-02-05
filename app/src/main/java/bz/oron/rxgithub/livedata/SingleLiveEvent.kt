package bz.oron.rxgithub.livedata

/**
 * Created by oron on 2/5/18.
 */
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import java.util.concurrent.atomic.AtomicBoolean


class SingleLiveEvent<T> : MutableLiveData<T>() {
  // private val TAG = "SingleLiveEvent"

  private val pending = AtomicBoolean(false)
  @MainThread
  override fun observe(owner: LifecycleOwner, observer: Observer<T>) {

    /*
    if (hasActiveObservers()) {
      Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
    }
     */

    super.observe(owner, Observer<T> { t ->
      if (pending.compareAndSet(true, false)) {
        observer.onChanged(t)
      }
    })
  }

  @MainThread
  override fun setValue(t: T?) {
    pending.set(true)
    super.setValue(t)
  }

  /**
   * Used for cases where T is Void, to make calls cleaner.
   */
  @MainThread
  fun call() {
    value = null
  }
}
