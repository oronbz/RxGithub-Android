package bz.oron.rxgithub.ui.profile

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import bz.oron.rxgithub.R
import bz.oron.rxgithub.ui.transformations.RoundedTransformation
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*
import javax.inject.Inject

class ProfileActivity : DaggerAppCompatActivity() {

  companion object {
    private val USERNAME_EXTRA: String = "USERNAME_EXTRA"
    private val AVATAR_URL_EXTRA: String = "AVATAR_URL_EXTRA"

    fun createIntent(context: Context, username: String, avatarUrl: String): Intent {
      val intent = Intent(context, ProfileActivity::class.java)
      intent.putExtra(USERNAME_EXTRA, username)
      intent.putExtra(AVATAR_URL_EXTRA, avatarUrl)
      return intent
    }
  }

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var viewModel: ProfileViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_profile)

    title = "User Profile"

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProfileViewModel::class.java)

    val username = intent.getStringExtra(USERNAME_EXTRA)
    val avatarUrl = intent.getStringExtra(AVATAR_URL_EXTRA)

    viewModel.username.value = username
    viewModel.avatarUrl.value = avatarUrl

    setupBindings()
  }

  private fun setupBindings() {
    // INPUT
    profileShowCommentsBtn.setOnClickListener {
      viewModel.showComments()
    }

    // OUTPUT
    viewModel.profileText.observe(this, Observer {
      profileTv.text = it
    })

    viewModel.profileImageUrl.observe(this, Observer {
      it?.let {
        Picasso.with(this)
            .load(it)
            .transform(RoundedTransformation())
            .fit()
            .centerCrop()
            .into(profileIv)
      }
    })
  }
}
