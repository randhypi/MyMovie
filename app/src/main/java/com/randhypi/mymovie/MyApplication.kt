package com.randhypi.mymovie

import android.app.Application
import com.capstone.core.di.databaseModule
import com.capstone.core.di.networkModule
import com.capstone.core.di.repositoryModule
import com.randhypi.mymovie.di.useCaseModule
import com.randhypi.mymovie.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}