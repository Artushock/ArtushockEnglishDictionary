package com.artushock.artushockenglishdictionary

import android.app.Application
import com.artushock.artushockenglishdictionary.koin.application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DictionaryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {

            androidContext(this@DictionaryApplication)
            modules(application)
        }
    }
}