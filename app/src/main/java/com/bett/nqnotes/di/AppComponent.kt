package com.bett.nqtodo.di

import com.bett.nqnotes.activities.AboutActivity
import com.bett.nqnotes.activities.CreateNoteActivity
import com.bett.nqnotes.activities.MainActivity
import com.bett.nqnotes.bus.BusModule
import com.bett.nqnotes.realm.RealmModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by bett on 9/14/17.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, RealmModule::class, BusModule::class))
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: CreateNoteActivity)
    fun inject(activity: AboutActivity)
}