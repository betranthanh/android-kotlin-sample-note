package com.bett.nqnotes.realm

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by bett on 9/22/17.
 */
@Module
class RealmModule {

    @Provides
    @Singleton
    fun provideNotesRealmManager(): NotesRealmManager {
        return NotesRealmManager()
    }
}
