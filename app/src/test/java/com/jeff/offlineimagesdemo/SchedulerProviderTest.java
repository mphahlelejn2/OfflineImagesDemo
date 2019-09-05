package com.jeff.offlineimagesdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jeff.offlineimagesdemo.rx.BaseSchedulerProvider;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class SchedulerProviderTest implements BaseSchedulerProvider {

    @Nullable
    private static SchedulerProviderTest INSTANCE;

    // Prevent direct instantiation.
    private SchedulerProviderTest() {
    }

    public static synchronized  SchedulerProviderTest getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SchedulerProviderTest();
        }
        return INSTANCE;
    }

    @NonNull
    public Scheduler computation() {
        return Schedulers.trampoline();
    }

    @Override
    @NonNull
    public  Scheduler io() {
        return Schedulers.trampoline();
    }

    @Override
    @NonNull
    public Scheduler ui() {
        return Schedulers.trampoline();
    }
}