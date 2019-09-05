package com.jeff.offlineimagesdemo.di

import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import com.jeff.offlineimagesdemo.view.DetailsActivity
import com.jeff.offlineimagesdemo.view.MainActivity

@Module(includes = arrayOf(AndroidInjectionModule::class))
abstract class Builder {
    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun bindDetailsActivity(): DetailsActivity
}