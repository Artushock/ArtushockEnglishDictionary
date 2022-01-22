package com.artushock.artushockenglishdictionary.presenters

import io.reactivex.Scheduler

interface  SchedulerProvider {
    fun io() : Scheduler
    fun ui() : Scheduler
}
