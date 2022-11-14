package com.hiremarknolan.android_party_compose

import android.app.Application
import com.hiremarknolan.android_party_compose.domain.storage.AndroidSharedPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AndroidPartyApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        AndroidSharedPreferences.init(this)
    }
}