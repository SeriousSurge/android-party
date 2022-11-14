package com.hiremarknolan.android_party_compose.domain.storage

import com.hiremarknolan.android_party_compose.common.Constants.USER_TOKEN
import javax.inject.Inject

class SessionManager @Inject constructor() : AndroidSharedPreferences() {

    fun saveAuthToken(token: String) {
        val editor = preferences.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return preferences.getString(USER_TOKEN, null)
    }

    fun clearAuthToken(): Boolean {
        preferences.edit()
             .putString(USER_TOKEN, null)
             .apply()
        return preferences.getString(USER_TOKEN, null).isNullOrEmpty()
    }

}