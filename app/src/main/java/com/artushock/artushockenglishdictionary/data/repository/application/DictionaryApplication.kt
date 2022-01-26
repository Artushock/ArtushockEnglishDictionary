package com.artushock.artushockenglishdictionary.data.repository.application

import android.app.Application
import com.artushock.artushockenglishdictionary.di.DaggerAppComponent

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class DictionaryApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        //DaggerAppComponent.create().inject(this)
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}