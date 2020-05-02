package com.leado.repos

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Source

open class BaseFireStore() {
    private val TAG = this.javaClass.simpleName

    protected var cacheSource: Source
    protected var defaultSource: Source
    protected val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var settings = FirebaseFirestoreSettings.Builder()

    init {
        db.firestoreSettings = settings.setPersistenceEnabled(true).build()
        defaultSource = Source.DEFAULT  //Source can be CACHE, SERVER, or DEFAULT.
        cacheSource = Source.CACHE  //Source can be CACHE, SERVER, or DEFAULT.
    }

}