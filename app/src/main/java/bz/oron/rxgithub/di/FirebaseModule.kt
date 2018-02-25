package bz.oron.rxgithub.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides

/**
 * Created by oron on 2/25/18.
 */
@Module
class FirebaseModule {

  @Provides
  fun providesFireStore(): FirebaseFirestore = FirebaseFirestore.getInstance()

}