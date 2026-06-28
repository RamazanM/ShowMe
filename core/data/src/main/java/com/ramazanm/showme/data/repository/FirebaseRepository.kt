package com.ramazanm.showme.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.ramazanm.model.Concept
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRepository @Inject constructor(private val firestore: FirebaseFirestore) :
    IFirebaseRepository {
    override fun addConcept(concept: Concept) {
        firestore.collection("concepts").add(concept)
    }

    override suspend fun getConcepts(): List<Concept> {
        val documents = firestore.collection("concepts").get().await()
        val concepts = documents.map { document ->
            val obj = document.toObject(Concept::class.java)
            obj.copy(id = document.id)
        }
        return concepts
    }

    override suspend fun getConcept(documentId: String): Concept? {
        val document = firestore.collection("concepts").document(documentId).get().await()
        return document.toObject(Concept::class.java)

    }
}

class TestFirebaseRepository : IFirebaseRepository {
    override fun addConcept(concept: Concept) {
    }

    override suspend fun getConcepts(): List<Concept> {
        TODO("Not yet implemented")
    }

    override suspend fun getConcept(documentId: String): Concept? {
        TODO("Not yet implemented")
    }
}

interface IFirebaseRepository {
    fun addConcept(concept: Concept)
    suspend fun getConcepts(): List<Concept>
    suspend fun getConcept(documentId: String): Concept?
}
