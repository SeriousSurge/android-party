package com.hiremarknolan.android_party_compose.domain.storage

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.hiremarknolan.android_party_compose.R

abstract class AndroidSharedPreferences {
    companion object {
        private lateinit var context: Application
        fun init(application: Application) {
            this.context = application
        }
    }

    protected val preferences: SharedPreferences by lazy {
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    }

}