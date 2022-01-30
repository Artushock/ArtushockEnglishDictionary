package com.artushock.artushockenglishdictionary.ui

import androidx.fragment.app.Fragment
import com.artushock.artushockenglishdictionary.presenters.ResultPresenter
import org.koin.android.ext.android.inject

abstract class BaseResultFragment : Fragment(), ResultView {

    protected val presenter: ResultPresenter<ResultView> by inject()

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}