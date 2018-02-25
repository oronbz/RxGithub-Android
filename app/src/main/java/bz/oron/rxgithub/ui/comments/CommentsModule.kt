package bz.oron.rxgithub.ui.comments

import android.arch.lifecycle.ViewModel
import bz.oron.rxgithub.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by oron on 2/25/18.
 */
@Module
abstract class CommentsModule {

  @Binds
  @IntoMap
  @ViewModelKey(CommentsViewModel::class)
  abstract fun bindCommentsViewModel(viewModel: CommentsViewModel): ViewModel
}