package com.otsembo.pinit.notes_data.common

object NotesUtil {
    private const val BASE_IMAGE_URL = "gs://pin-it-d9e73.appspot.com/"
    fun getFirebaseImage(url: String): String {
        return "${BASE_IMAGE_URL}$url"
    }
}
