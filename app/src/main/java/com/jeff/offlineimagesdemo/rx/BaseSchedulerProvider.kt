package com.jeff.offlineimagesdemo.rx

import io.reactivex.Scheduler
import io.reactivex.annotations.NonNull

interface BaseSchedulerProvider {
    @NonNull
   public fun io():Scheduler

    @NonNull
   public  fun ui():Scheduler
}
