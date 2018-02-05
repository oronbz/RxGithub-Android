package bz.oron.rxgithub.ui.profile

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

/**
 * Created by oron on 2/5/18.
 */
class ProfileViewModel @Inject constructor(): ViewModel() {

  // INPUT
  val username: MutableLiveData<String> = MutableLiveData()
  val avatarUrl: MutableLiveData<String> = MutableLiveData()

  // OUTPUT
  val profileText: LiveData<String>
  val profileImageUrl: LiveData<String>

  init {
    profileText = username
    profileImageUrl = avatarUrl
  }

  fun showComments() {

  }
}