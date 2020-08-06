package com.example.mycontacs

import android.app.Application
import com.example.mycontacs.di.dependencyModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyContactsApp: Application() {

    companion object{
        lateinit var mApp: MyContactsApp
        private set
    }

    override fun onCreate() {
        super.onCreate()
        mApp = this
        startKoin {
            androidContext(this@MyContactsApp)
            modules(dependencyModule)
        }
    }

}