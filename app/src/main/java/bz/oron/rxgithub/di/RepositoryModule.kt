package bz.oron.rxgithub.di

import bz.oron.rxgithub.repositories.CommentsRepository
import bz.oron.rxgithub.repositories.GitHubRepository
import bz.oron.rxgithub.repositories.ICommentsRepository
import bz.oron.rxgithub.repositories.IGitHubRepository
import dagger.Binds
import dagger.Module

/**
 * Created by oron on 2/4/18.
 */
@Module
abstract class RepositoryModule {

  @Binds
  abstract fun bindGitHubRepository(gitHubRepository: GitHubRepository): IGitHubRepository

  @Binds
  abstract fun bindCommentsRepository(commentsRepository: CommentsRepository): ICommentsRepository

}