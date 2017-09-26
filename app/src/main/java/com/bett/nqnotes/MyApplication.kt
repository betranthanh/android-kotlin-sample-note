package com.bett.nqnotes

import android.app.Application
import com.bett.nqtodo.di.AppComponent
import com.bett.nqtodo.di.DaggerAppComponent
import io.realm.Realm


/**
 * Created by bett on 9/20/17.
 */
class MyApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .build()

    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}
