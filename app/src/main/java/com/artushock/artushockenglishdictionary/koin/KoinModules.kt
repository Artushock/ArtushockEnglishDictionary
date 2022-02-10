package com.artushock.artushockenglishdictionary.koin

import androidx.room.Room
import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.artushockenglishdictionary.interactors.HistoryInteractor
import com.artushock.artushockenglishdictionary.interactors.HistoryInteractorImpl
import com.artushock.artushockenglishdictionary.interactors.ResultInteractor
import com.artushock.artushockenglishdictionary.interactors.ResultInteractorImpl
import com.artushock.artushockenglishdictionary.presenters.*
import com.artushock.artushockenglishdictionary.ui.HistoryView
import com.artushock.artushockenglishdictionary.ui.ResultView
import com.artushock.models.entities.DataModel
import com.artushock.repository.repository.Repository
import com.artushock.repository.repository.RepositoryImpl
import com.artushock.repository.repository.local.LocalRepository
import com.artushock.repository.repository.local.room.HistoryDataBase
import com.artushock.repository.repository.local.room.HistoryEntity
import com.artushock.repository.repository.remote.RemoteRepository
import com.artushock.repository.repository.remote.RetrofitImpl
import com.artushock.repository.repository.remote.data.RemoteDataModel
import com.artushock.repository.repository.remote.data.RoomImpl
import io.reactivex.disposables.CompositeDisposable
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

    factory<ResultInteractor<AppState>> { ResultInteractorImpl(get()) }

    factory<HistoryInteractor<List<HistoryEntity>>> { HistoryInteractorImpl(get()) }

    factory { CompositeDisposable() }

    factory { SchedulerProviderImpl() } bind SchedulerProvider::class

    factory<ResultPresenter<ResultView>> { ResultPresenterImpl(get()) }

    factory<HistoryPresenter<HistoryView>> { HistoryPresenterImpl(get()) }
}