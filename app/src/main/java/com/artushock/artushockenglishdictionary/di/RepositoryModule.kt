package com.artushock.artushockenglishdictionary.di

import com.artushock.artushockenglishdictionary.data.repository.Repository
import com.artushock.artushockenglishdictionary.data.repository.RepositoryImpl
import com.artushock.artushockenglishdictionary.data.repository.local.LocalRepository
import com.artushock.artushockenglishdictionary.data.repository.local.RoomImpl
import com.artushock.artushockenglishdictionary.data.repository.remote.RemoteRepository
import com.artushock.artushockenglishdictionary.data.repository.remote.RetrofitImpl
import com.artushock.artushockenglishdictionary.data.repository.remote.data.RemoteDataModel
import com.artushock.artushockenglishdictionary.entities.DataModel
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    internal fun provideRepository(
        localRepository: LocalRepository<List<RemoteDataModel>>,
        remoteRepository: RemoteRepository<List<RemoteDataModel>>,
    ): Repository<List<DataModel>>  {
        return RepositoryImpl(localRepository, remoteRepository)
    }

    @Provides
    internal fun provideLocalRepository(): LocalRepository<List<RemoteDataModel>> = RoomImpl()

    @Provides
    internal fun provideRemoteRepository(): RemoteRepository<List<RemoteDataModel>> = RetrofitImpl()
}