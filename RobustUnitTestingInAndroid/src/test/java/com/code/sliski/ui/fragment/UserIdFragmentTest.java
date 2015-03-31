package com.code.sliski.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import com.code.sliski.preference.PreferencesManager;
import com.code.sliski.R;
import com.code.sliski.ui.activity.MainActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowHandler;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.util.FragmentTestUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class UserIdFragmentTest {

    private UserIdFragment mUserIdFragment;
    private PreferencesManager mPrefManager;

    @Before
    public void setUp() throws Exception {
        Context applicationContext = Robolectric.application.getApplicationContext();
        String prefsKey = applicationContext.getString(R.string.preferences);
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences(prefsKey, Context.MODE_PRIVATE);
        mPrefManager = new PreferencesManager(sharedPreferences);
        mUserIdFragment = new UserIdFragment();
        FragmentTestUtil.startFragment(mUserIdFragment, MainActivity.class);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void onClickGoButtonShouldSaveIdFromUserIdEditTextToPreferences() throws Exception {
        long id = 1l;
        ((EditText) mUserIdFragment.getView().findViewById(R.id.user_id_editText)).setText(String.valueOf(id));
        mUserIdFragment.getView().findViewById(R.id.go_button).performClick();
        long savedId = mPrefManager.userId().getOr(0l);
        assertEquals(
                "User id not saved after onclick, but it should",
                id,
                savedId
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void onClickGoButtonShouldShowToastWithBadIdFormat() throws Exception {
        String id = "foo";
        ((EditText) mUserIdFragment.getView().findViewById(R.id.user_id_editText)).setText(String.valueOf(id));
        mUserIdFragment.getView().findViewById(R.id.go_button).performClick();
        ShadowHandler.idleMainLooper();
        assertEquals(
                "No validation toast showed, but it should",
                "Bad id format",
                ShadowToast.getTextOfLatestToast()
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void onClickGoButtonShouldReplaceFragmentWithUserInfoFragment() throws Exception {
        long id = 2l;
        ((EditText) mUserIdFragment.getView().findViewById(R.id.user_id_editText)).setText(String.valueOf(id));
        mUserIdFragment.getView().findViewById(R.id.go_button).performClick();
        Fragment fragment = mUserIdFragment.getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
        assertTrue(
                "Fragment should be instance of UserInfoFragment, but it is not.",
                fragment instanceof UserInfoFragment
        );
    }

    @SuppressWarnings("ConstantConditions")
    @After
    public void tearDown() throws Exception {
        mPrefManager.userId().remove().commit();
    }
}