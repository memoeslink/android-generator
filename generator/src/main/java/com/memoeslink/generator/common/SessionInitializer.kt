package com.memoeslink.generator.common

import android.content.Context
import androidx.startup.Initializer

internal class SessionInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        // Initialize Generator database
        Session.getInstance().initDatabase(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>?>> {
        return mutableListOf()
    }
}