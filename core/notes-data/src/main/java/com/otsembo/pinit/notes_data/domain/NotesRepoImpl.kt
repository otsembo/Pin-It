package com.otsembo.pinit.notes_data.domain

import android.graphics.Bitmap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.otsembo.pinit.notes_data.common.AppResource
import com.otsembo.pinit.notes_data.data.model.AppNote
import com.otsembo.pinit.notes_data.data.repository.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*
import kotlin.random.Random

class NotesRepoImpl(
    private val fireStore: FirebaseFirestore,
    private val coroutineScope: CoroutineScope
) : NotesRepository {

    private val _notesFlow: MutableStateFlow<AppResource<List<AppNote>>> = MutableStateFlow(AppResource.Idle(data = emptyList()))
    private val notesFlow: StateFlow<AppResource<List<AppNote>>> = _notesFlow

    private val _errorMessage: MutableSharedFlow<String> = MutableSharedFlow()
    override val errorMessage: SharedFlow<String> = _errorMessage

    private val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    private val storageReference: StorageReference = FirebaseStorage.getInstance().reference

    override suspend fun createNote(appNote: AppNote): String {
        appNote.user = user?.uid
        var addNoteMessage = ADD_NOTES_FAIL
        val notesDocument = fireStore.collection(NOTES_COLLECTION).document()
        appNote.noteId = notesDocument.id
        notesDocument.set(appNote).addOnCompleteListener {
            if (it.isSuccessful) addNoteMessage = ADD_NOTES_SUCCESS else displayError(addNoteMessage)
        }.await()
        return addNoteMessage
    }

    override suspend fun updateNote(appNote: AppNote): String {
        var updateNoteMessage = UPDATE_NOTES_FAIL
        val notesDocument = fireStore.collection(NOTES_COLLECTION).document(appNote.noteId!!)
        notesDocument.set(appNote).addOnCompleteListener {
            if (it.isSuccessful) updateNoteMessage = UPDATE_NOTES_SUCCESS else displayError(updateNoteMessage)
        }.await()
        return updateNoteMessage
    }

    override suspend fun deleteNote(noteId: String): String {
        var deleteNoteMessage = DELETE_NOTE_FAIL
        fireStore.collection(NOTES_COLLECTION).document(noteId).delete().addOnCompleteListener {
            if (it.isSuccessful) deleteNoteMessage = DELETE_NOTE_SUCCESS else displayError(deleteNoteMessage)
        }.await()
        return deleteNoteMessage
    }

    override fun displayNotes(): StateFlow<AppResource<List<AppNote>>> {
        fireStore.collection(NOTES_COLLECTION).addSnapshotListener { value, error ->
            // show error if any
            error?.let { displayError(it.message ?: READ_NOTES_FAILED) }
        }
        return notesFlow
    }

    @Throws(IOException::class)
    override suspend fun storeImage(imageBitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.WEBP_LOSSLESS, IMAGE_QUALITY, outputStream)
        val randomName = (1..IMAGE_NAME_LENGTH)
            .map { _ -> Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("") + ".webp"
        val locationRef = storageReference.child("images/$randomName")
        val uploadTask = locationRef.putBytes(outputStream.toByteArray())
        uploadTask.addOnCompleteListener {
            if (!it.isSuccessful) it.exception?.message?.let { exceptionMessage -> displayError(error = exceptionMessage) }
        }.await()
        return locationRef.path
    }

    private fun displayError(error: String) {
        coroutineScope.launch { _errorMessage.emit(error) }
    }

    companion object {
        const val NOTES_COLLECTION = "notes"
        const val ADD_NOTES_FAIL = "There was an error adding your note"
        const val ADD_NOTES_SUCCESS = "Note was added successfully"
        const val UPDATE_NOTES_FAIL = "Note was not successfully updated"
        const val UPDATE_NOTES_SUCCESS = "The note was successfully updated"
        const val DELETE_NOTE_FAIL = "The note failed to delete"
        const val DELETE_NOTE_SUCCESS = "The note was deleted successfully"
        const val READ_NOTES_FAILED = "There was an unexpected error"

        const val IMAGE_QUALITY = 100
        const val IMAGE_NAME_LENGTH = 30

        private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    }
}
