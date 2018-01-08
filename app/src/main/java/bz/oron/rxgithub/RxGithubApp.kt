package bz.oron.rxgithub

import bz.oron.rxgithub.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by oron on 1/8/18.
 */
class RxGithubApp: DaggerApplication() {
  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder().create(this)
  }
}