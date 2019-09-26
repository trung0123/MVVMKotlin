package com.example.askbekotlin

import android.app.Application
import android.content.Context
import timber.log.Timber
import kotlin.properties.Delegates

class AskbeApplication : Application() {

    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        Timber.plant(Timber.DebugTree())
    }
}