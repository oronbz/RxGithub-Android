package bz.oron.rxgithub.di

import bz.oron.rxgithub.ui.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by oron on 1/8/18.
 */
@Module
abstract class ActivityBuilder {

  @ContributesAndroidInjector
  abstract fun bindsSearchActivity(): SearchActivity
}