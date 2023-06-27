package com.memoeslink.generator.common

import android.content.Context
import android.content.ContextWrapper

abstract class ContextWrapper(@JvmField protected var context: Context) : ContextWrapper(context)