package bz.oron.rxgithub.repositories

import android.arch.lifecycle.LiveData
import bz.oron.rxgithub.models.GitHubUserSearch

/**
 * Created by oron on 2/4/18.
 */
interface IGitHubRepository {
  fun searchUsers(query: String): LiveData<GitHubUserSearch?>
}