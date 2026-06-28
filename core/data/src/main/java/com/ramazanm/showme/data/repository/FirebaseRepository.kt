package com.ramazanm.showme.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.ramazanm.model.Concept
import javax.inject.Inject

class FirebaseRepository @Inject constructor(private val firestore: FirebaseFirestore):
    IFirebaseRepository {
    override fun addConcept(concept: Concept) {
        firestore.collection("concepts").add(concept)
    }
}

class TestFirebaseRepository: IFirebaseRepository {
    override fun addConcept(concept: Concept) {
    }
}

interface IFirebaseRepository {
    fun addConcept(concept: Concept)
}
