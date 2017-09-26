package com.bett.nqnotes.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by bett on 9/20/17.
 */
open class NoteDto: RealmObject() {

    @PrimaryKey
    var id: Long = 0
    var title: String = ""
    var content: String = ""
}