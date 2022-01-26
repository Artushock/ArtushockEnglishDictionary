package com.artushock.artushockenglishdictionary.di

import android.app.Application
import com.artushock.artushockenglishdictionary.data.repository.application.DictionaryApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RepositoryModule::class,
    MainActivityModule::class,
    FragmentModule::class,
    ViewModelModule::class,
    AndroidInjectionModule::class
])
interface AppComponent : AndroidInjector<DictionaryApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(application: DictionaryApplication)
}