package com.memoeslink.generator.common

import android.content.Context
import androidx.startup.Initializer

internal class BaseInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Session.getInstance().initDatabase(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>?>> {
        return mutableListOf()
    }
}