package bz.oron.rxgithub.di

import bz.oron.rxgithub.network.GitHubApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

/**
 * Created by oron on 2/4/18.
 */
@Module
class RetrofitModule {

  @Provides
  @Named("baseUrl")
  fun provideBaseUrl() = "https://api.github.com/"

  @Provides
  fun provideRetrofit(@Named("baseUrl") baseUrl: String): Retrofit {
    return Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
  }

  @Provides
  fun provideGitHubApi(retrofit: Retrofit): GitHubApi = retrofit.create(GitHubApi::class.java)
}