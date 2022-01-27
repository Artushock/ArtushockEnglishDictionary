package com.artushock.artushockenglishdictionary.di

import com.artushock.artushockenglishdictionary.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeAndroidInjector(): MainActivity
}