package com.test_project.app.app

import android.app.Application

class App : Application() {

    companion object {
        private var instance: App? = null

        @Synchronized
        fun getInstance(): App? {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}

