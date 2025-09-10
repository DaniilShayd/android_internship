package com.example.domain.utils

import com.example.domain.data.Album
import com.example.domain.data.Post
import com.example.domain.data.Todo
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

data class PaginatedResult<T>(
    val data: List<T>,
    val lastDocument: DocumentSnapshot?,
    val hasMore: Boolean
)

class FirebaseClient {
    private val db = FirebaseFirestore.getInstance()

    private suspend inline fun <reified T : Any> getDocumentById(
        collectionName: String,
        documentId: String
    ): T? {
        return try {
            db.collection(collectionName)
                .document(documentId)
                .get()
                .await()
                .toObjectWithId()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private suspend inline fun <reified T : Any> getPaginatedCollection(
        collectionName: String,
        orderByField: String,
        pageSize: Long = 10,
        lastDoc: DocumentSnapshot? = null
    ): PaginatedResult<T> {
        return try {
            var query: Query = db.collection(collectionName)
                .orderBy(orderByField)
                .limit(pageSize)

            lastDoc?.let { query = query.startAfter(it) }

            val querySnapshot = query.get().await()
            val data = querySnapshot.documents.mapNotNull { it.toObjectWithId<T>() }

            PaginatedResult(
                data = data,
                lastDocument = querySnapshot.documents.lastOrNull(),
                hasMore = querySnapshot.size() == pageSize.toInt()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            PaginatedResult(emptyList(), null, false)
        }
    }

    suspend fun getAlbumById(albumId: String): Album? = getDocumentById("albums", albumId)
    suspend fun getAlbums(pageSize: Long = 10, lastDoc: DocumentSnapshot? = null) =
        getPaginatedCollection<Album>("albums", "title", pageSize, lastDoc)

    suspend fun getPostById(postId: String): Post? = getDocumentById("posts", postId)
    suspend fun getPosts(pageSize: Long = 10, lastDoc: DocumentSnapshot? = null) =
        getPaginatedCollection<Post>("posts", "title", pageSize, lastDoc)

    suspend fun togglePostLike(postId: String): Boolean {
        return try {
            val postRef = db.collection("posts").document(postId)
            db.runTransaction { transaction ->
                val snapshot = transaction.get(postRef)
                val currentLikedState = snapshot.getBoolean("isLiked") ?: false
                transaction.update(postRef, "isLiked", !currentLikedState)
                !currentLikedState
            }.await()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun createTodo(title: String, isCompleted: Boolean = false): Todo? {
        return try {
            val data = hashMapOf(
                "title" to title,
                "isCompleted" to isCompleted
            )

            val docRef = db.collection("todos").add(data).await()
            Todo(id = docRef.id, title = title, isCompleted = isCompleted)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getTodos(pageSize: Long = 10, lastDoc: DocumentSnapshot? = null) =
        getPaginatedCollection<Todo>("todos", "title", pageSize, lastDoc)

    private inline fun <reified T : Any> DocumentSnapshot.toObjectWithId(): T? {
        return toObject(T::class.java)?.let { obj ->
            obj.javaClass.declaredFields.firstOrNull { it.name == "id" }?.let { field ->
                field.isAccessible = true
                field.set(obj, this.id)
            }
            obj
        }
    }
}