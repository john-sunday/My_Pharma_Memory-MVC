package com.juandomingo.mypharmamemorymvc.model

import android.app.Application
import android.content.Context

class Context: Application() {
    companion object {
        lateinit var context: Context

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}