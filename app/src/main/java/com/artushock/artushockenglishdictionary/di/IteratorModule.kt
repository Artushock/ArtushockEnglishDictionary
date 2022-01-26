package com.artushock.artushockenglishdictionary.di

import com.artushock.artushockenglishdictionary.data.repository.Repository
import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.artushockenglishdictionary.entities.DataModel
import com.artushock.artushockenglishdictionary.interactors.ResultInteractor
import com.artushock.artushockenglishdictionary.interactors.ResultInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class IteratorModule {
    @Provides
    internal fun provideResultInteractor(
        repository: Repository<List<DataModel>>
    ): ResultInteractor<AppState> = ResultInteractorImpl(repository)
}