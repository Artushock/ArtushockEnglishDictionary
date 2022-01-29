package com.artushock.artushockenglishdictionary.koin

import com.artushock.artushockenglishdictionary.data.repository.Repository
import com.artushock.artushockenglishdictionary.data.repository.RepositoryImpl
import com.artushock.artushockenglishdictionary.data.repository.local.LocalRepository
import com.artushock.artushockenglishdictionary.data.repository.local.RoomImpl
import com.artushock.artushockenglishdictionary.data.repository.remote.RemoteRepository
import com.artushock.artushockenglishdictionary.data.repository.remote.RetrofitImpl
import com.artushock.artushockenglishdictionary.data.repository.remote.data.RemoteDataModel
import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.artushockenglishdictionary.entities.DataModel
import com.artushock.artushockenglishdictionary.interactors.ResultInteractor
import com.artushock.artushockenglishdictionary.interactors.ResultInteractorImpl
import com.artushock.artushockenglishdictionary.presenters.ResultPresenter
import com.artushock.artushockenglishdictionary.presenters.ResultPresenterImpl
import com.artushock.artushockenglishdictionary.presenters.SchedulerProvider
import com.artushock.artushockenglishdictionary.presenters.SchedulerProviderImpl
import com.artushock.artushockenglishdictionary.ui.ResultView
import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.bind
import org.koin.dsl.module

val application = module {

    single<RemoteRepository<List<RemoteDataModel>>> {
        RetrofitImpl()
    }

    single<LocalRepository<List<RemoteDataModel>>> {
        RoomImpl()
    }


    factory<Repository<List<DataModel>>> { RepositoryImpl(get(), get()) }

    factory<ResultInteractor<AppState>> { ResultInteractorImpl(get()) }

    factory { CompositeDisposable() }

    factory { SchedulerProviderImpl() } bind SchedulerProvider::class

    factory<ResultPresenter<ResultView>> { ResultPresenterImpl(get(), get(), get()) }


}