package com.artushock.artushockenglishdictionary.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artushock.artushockenglishdictionary.viewModels.ResultViewModel
import com.artushock.artushockenglishdictionary.viewModels.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module(includes = [IteratorModule::class])
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ResultViewModel::class)
    protected abstract fun mainViewModel(resultViewModel: ResultViewModel): ViewModel

}

@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)