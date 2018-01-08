package bz.oron.rxgithub.di

import bz.oron.rxgithub.RxGithubApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by oron on 1/8/18.
 */

@Singleton
@Component(
    modules = [
      (AndroidSupportInjectionModule::class),
      (AppModule::class),
      (ActivityBuilder::class)
    ]
)
interface AppComponent: AndroidInjector<RxGithubApp> {

  @Component.Builder
  abstract class Builder: AndroidInjector.Builder<RxGithubApp>()
}