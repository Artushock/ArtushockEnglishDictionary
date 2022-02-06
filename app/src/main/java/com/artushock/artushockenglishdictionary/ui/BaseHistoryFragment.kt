package com.artushock.artushockenglishdictionary.ui

import androidx.fragment.app.Fragment
import com.artushock.artushockenglishdictionary.presenters.HistoryPresenter
import com.artushock.artushockenglishdictionary.presenters.ResultPresenter
import org.koin.android.ext.android.inject

abstract class BaseHistoryFragment : Fragment(), HistoryView {

    protected val presenter: HistoryPresenter<HistoryView> by inject()

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}