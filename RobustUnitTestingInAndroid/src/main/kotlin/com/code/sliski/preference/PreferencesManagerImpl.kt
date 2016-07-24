package com.code.sliski.preference

import android.content.SharedPreferences
import com.tale.prettysharedpreferences.PrettySharedPreferences

class PreferencesManagerImpl(sharedPreferences: SharedPreferences) :
        PrettySharedPreferences<PreferencesManagerImpl>(sharedPreferences),
        PreferencesManager {

    override fun saveUserId(userId: Long) {
        getLongEditor("userId").put(userId).commit()
    }

    override fun getUserId(): Long = getLongEditor("userId").getOr(0L)
}