package com.code.sliski.preference

import android.content.SharedPreferences
import com.tale.prettysharedpreferences.LongEditor
import com.tale.prettysharedpreferences.PrettySharedPreferences

public open class PreferencesManager(sharedPreferences: SharedPreferences) : PrettySharedPreferences<PreferencesManager>(sharedPreferences) {
    public fun userId(): LongEditor<*>? = getLongEditor("userId")
}