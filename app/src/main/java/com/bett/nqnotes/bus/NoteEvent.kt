package com.bett.nqnotes.bus

import android.support.annotation.IntDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created by bett on 9/26/17.
 */
class NoteEvent(@param:Query val query: Int) {

    @IntDef(ACTION_INSERT.toLong(), ACTION_UPDATE.toLong(), ACTION_DELETE.toLong())
    @Retention(RetentionPolicy.SOURCE)
    annotation class Query

    companion object {

        const val ACTION_INSERT = 0
        const val ACTION_UPDATE = 1
        const val ACTION_DELETE = 2
    }
}
