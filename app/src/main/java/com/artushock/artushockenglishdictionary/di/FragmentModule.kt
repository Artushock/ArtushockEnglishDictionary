package com.artushock.artushockenglishdictionary.di

import com.artushock.artushockenglishdictionary.ui.ResultFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeResultFragment(): ResultFragment
}