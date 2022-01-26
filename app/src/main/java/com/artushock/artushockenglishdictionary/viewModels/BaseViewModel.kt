package com.artushock.artushockenglishdictionary.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.artushock.artushockenglishdictionary.entities.AppState
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T : AppState>(
    protected val resultLiveData: MutableLiveData<T> = MutableLiveData(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProviderImpl(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
) : ViewModel() {

    open fun getTranslations(word: String): LiveData<T> = resultLiveData

    override fun onCleared() {
        compositeDisposable.clear()
    }
}