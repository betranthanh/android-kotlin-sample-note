package com.bett.nqtodo.di

import android.content.Context
import com.bett.nqnotes.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by bett on 9/14/17.
 */
@Module
class AppModule(private val app: MyApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }
}

