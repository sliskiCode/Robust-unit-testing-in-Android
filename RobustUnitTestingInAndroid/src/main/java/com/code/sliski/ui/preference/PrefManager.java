package com.code.sliski.ui.preference;

import android.content.SharedPreferences;
import com.tale.prettysharedpreferences.LongEditor;
import com.tale.prettysharedpreferences.PrettySharedPreferences;

public class PrefManager extends PrettySharedPreferences<PrefManager> {

    public PrefManager(SharedPreferences sharedPreferences) {
        super(sharedPreferences);
    }

    public LongEditor<PrefManager> userId() {
        return getLongEditor("userId");
    }
}
