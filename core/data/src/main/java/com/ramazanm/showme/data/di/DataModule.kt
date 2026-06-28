package com.ramazanm.showme.data.di

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.ramazanm.showme.data.repository.FirebaseRepository
import com.ramazanm.showme.data.repository.IFirebaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun provideConceptRepository(
        firestore: FirebaseFirestore
    ): IFirebaseRepository {
        // Returns your repository implementation that communicates with Firebase
        return FirebaseRepository(firestore)
    }
}