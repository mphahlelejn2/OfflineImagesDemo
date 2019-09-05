package com.jeff.offlineimagesdemo.di
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules= [AndroidSupportInjectionModule::class, AppModule::class, Builder::class])
interface AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
       fun  application(application:Application):Builder
        fun build():AppComponent
    }

    fun inject(instance: App?)
}