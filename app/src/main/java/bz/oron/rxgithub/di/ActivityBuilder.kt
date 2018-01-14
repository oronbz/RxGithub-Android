package bz.oron.rxgithub.di

import bz.oron.rxgithub.ui.search.SearchActivity
import bz.oron.rxgithub.ui.search.SearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by oron on 1/8/18.
 */
@Module
abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = [SearchModule::class])
  abstract fun bindSearchActivity(): SearchActivity
}