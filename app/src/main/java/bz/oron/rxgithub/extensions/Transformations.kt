package bz.oron.rxgithub.extensions

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Transformations

/**
 * Created by oron on 2/4/18.
 */
fun <X,Y> LiveData<X>.map(func: (X) -> Y): LiveData<Y> {
  return Transformations.map(this, func)
}

fun <X,Y> LiveData<X>.switchMap(func: (X) -> LiveData<Y>): LiveData<Y> {
  return Transformations.switchMap(this, func)
}

fun <X> LiveData<X>.filter(test: (X) -> Boolean): LiveData<X> {
  val result = MediatorLiveData<X>()

  result.addSource(this, {
    if (it != null && test(it)) {
      result.value = it
    }
  })

  return result
}

fun <X> LiveData<X>.filterNullables(test: (X?) -> Boolean): LiveData<X> {
  val result = MediatorLiveData<X>()

  result.addSource(this, {
    if (test(it)) {
      result.value = it
    }
  })

  return result
}

fun <X> LiveData<X?>.filterNull(): LiveData<X> {
  val result = MediatorLiveData<X>()

  result.addSource(this, {
    it?.let {
      result.value = it
    }
  })

  return result
}