package com.artushock.artushockenglishdictionary.viewModels

import io.reactivex.Scheduler

interface  SchedulerProvider {
    fun io() : Scheduler
    fun ui() : Scheduler
}
