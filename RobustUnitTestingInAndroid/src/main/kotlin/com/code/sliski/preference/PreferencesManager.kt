package com.code.sliski.preference

import android.content.SharedPreferences
import com.tale.prettysharedpreferences.LongEditor
import com.tale.prettysharedpreferences.PrettySharedPreferences

class PreferencesManager(sharedPreferences: SharedPreferences) :
        PrettySharedPreferences<PreferencesManager>(sharedPreferences) {

    fun userId(): LongEditor<*> = getLongEditor("userId")
}