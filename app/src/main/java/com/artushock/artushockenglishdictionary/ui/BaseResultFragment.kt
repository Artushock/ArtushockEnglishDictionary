package com.artushock.artushockenglishdictionary.ui

import androidx.fragment.app.Fragment
import com.artushock.artushockenglishdictionary.presenters.ResultPresenter
import org.koin.androidx.scope.fragmentScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.scope.Scope

abstract class BaseResultFragment : Fragment(), ResultView, KoinScopeComponent {
    final override val scope: Scope by fragmentScope()
    protected val presenter: ResultPresenter<ResultView> by scope.inject()

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}