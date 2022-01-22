package com.artushock.artushockenglishdictionary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.artushock.artushockenglishdictionary.presenters.ResultPresenter

abstract class BaseResultFragment: Fragment(), ResultView {

    protected lateinit var presenter: ResultPresenter<ResultView>

    abstract fun createPresenter(): ResultPresenter<ResultView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }

}