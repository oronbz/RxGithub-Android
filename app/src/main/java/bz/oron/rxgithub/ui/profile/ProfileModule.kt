package bz.oron.rxgithub.ui.profile

import android.arch.lifecycle.ViewModel
import bz.oron.rxgithub.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by oron on 2/5/18.
 */
@Module
abstract class ProfileModule {

  @Binds
  @IntoMap
  @ViewModelKey(ProfileViewModel::class)
  abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel
}