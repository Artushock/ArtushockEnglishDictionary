package com.artushock.artushockenglishdictionary.koin

import androidx.room.Room
import com.artushock.artushockenglishdictionary.data.repository.Repository
import com.artushock.artushockenglishdictionary.data.repository.RepositoryImpl
import com.artushock.artushockenglishdictionary.data.repository.local.LocalRepository
import com.artushock.artushockenglishdictionary.data.repository.local.RoomImpl
import com.artushock.artushockenglishdictionary.data.repository.local.room.HistoryDataBase
import com.artushock.artushockenglishdictionary.data.repository.local.room.HistoryEntity
import com.artushock.artushockenglishdictionary.data.repository.remote.RemoteRepository
import com.artushock.artushockenglishdictionary.data.repository.remote.RetrofitImpl
import com.artushock.artushockenglishdictionary.data.repository.remote.data.RemoteDataModel
import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.artushockenglishdictionary.entities.DataModel
import com.artushock.artushockenglishdictionary.interactors.HistoryInteractor
import com.artushock.artushockenglishdictionary.interactors.HistoryInteractorImpl
import com.artushock.artushockenglishdictionary.interactors.ResultInteractor
import com.artushock.artushockenglishdictionary.interactors.ResultInteractorImpl
import com.artushock.artushockenglishdictionary.presenters.*
import com.artushock.artushockenglishdictionary.ui.HistoryFragment
import com.artushock.artushockenglishdictionary.ui.HistoryView
import com.artushock.artushockenglishdictionary.ui.ResultFragment
import com.artushock.artushockenglishdictionary.ui.ResultView
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val application = module {

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }


    single<LocalRepository<List<HistoryEntity>>> {
        RoomImpl(get())
    }
    single<RemoteRepository<List<RemoteDataModel>>> {
        RetrofitImpl()
    }

    factory<Repository<List<DataModel>, List<HistoryEntity>>> {
        RepositoryImpl(
            localRepository = get(),
            remoteRepository = get())
    }

    scope(named<HistoryFragment>()) {

        factory<HistoryInteractor<List<HistoryEntity>>> { HistoryInteractorImpl(get()) }

        factory<HistoryPresenter<HistoryView>> { HistoryPresenterImpl(get()) }
    }

    scope(named<ResultFragment>()) {

        factory<ResultInteractor<AppState>> { ResultInteractorImpl(get()) }

        factory<ResultPresenter<ResultView>> { ResultPresenterImpl(get()) }
    }

    factory { CompositeDisposable() }

    factory { SchedulerProviderImpl() } bind SchedulerProvider::class
}