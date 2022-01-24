package com.artushock.artushockenglishdictionary.ui

import androidx.fragment.app.Fragment
import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.artushockenglishdictionary.presenters.BaseViewModel

abstract class BaseFragment<T : AppState> : Fragment() {

    abstract val viewModel: BaseViewModel<T>

    abstract fun renderData(appState: T)

}