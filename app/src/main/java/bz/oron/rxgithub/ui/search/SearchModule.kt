package bz.oron.rxgithub.ui.search

import android.arch.lifecycle.ViewModel
import bz.oron.rxgithub.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by oron on 1/8/18.
 */
@Module
abstract class SearchModule {

  @Binds
  @IntoMap
  @ViewModelKey(SearchViewModel::class)
  abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}