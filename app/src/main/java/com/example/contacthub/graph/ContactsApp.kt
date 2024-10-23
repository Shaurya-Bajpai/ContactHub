package com.example.contacthub.graph

import android.app.Application
import com.example.contacthub.mode.Prefs

val prefs: Prefs by lazy {
    ContactsApp.prefs!!
}

class ContactsApp: Application() {
//    override fun onCreate() {
//        super.onCreate()
//        Graph.provide(this)
//    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        prefs = Prefs(applicationContext)
        Graph.provide(this)
    }

    companion object {
        var prefs: Prefs? = null
        lateinit var instance: ContactsApp
            private set
    }
}