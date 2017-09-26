package com.bett.nqnotes.bus

import com.squareup.otto.Bus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by bett on 9/26/17.
 */
@Module
class BusModule {

    @Provides
    @Singleton
    fun provideBus(): Bus {
        return Bus()
    }
}