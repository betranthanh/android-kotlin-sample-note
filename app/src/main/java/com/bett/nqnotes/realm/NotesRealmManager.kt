package com.bett.nqnotes.realm

import com.bett.nqnotes.models.NoteDto
import io.realm.Realm
import io.realm.RealmConfiguration


/**
 * Created by bett on 9/20/17.
 */

class NotesRealmManager {

    val realm: Realm by lazy {
        val config = RealmConfiguration.Builder()
                .name("bett.realm")
                .schemaVersion(2)
                .build()
        Realm.getInstance(config)

//        Realm.getDefaultInstance()
    }

    fun find(id: Long): NoteDto? {
        return realm.where(NoteDto::class.java).equalTo("id", id).findFirst()
    }

    fun findAll(): List<NoteDto> {
        return realm.where(NoteDto::class.java).findAll()
    }

    fun insert(title: String, content: String) {
        realm.beginTransaction()
        var newId: Long = 1
        if (realm.where(NoteDto::class.java).max("id") != null) {
            newId = realm.where(NoteDto::class.java).max("id") as Long + 1
        }
        val note = realm.createObject(NoteDto::class.java, newId)
        note.title = title
        note.content = content
        realm.commitTransaction()
    }

    fun update(id: Long, title: String, content: String) {
        realm.beginTransaction()
        val note = find(id)
        note?.title = title
        note?.content = content

        realm.commitTransaction()
    }

    fun deleteById(id: Long) {
        realm.beginTransaction()
        val results = realm.where(NoteDto::class.java!!).equalTo("id", id).findAll()
        results.deleteAllFromRealm()
        realm.commitTransaction()
    }
}