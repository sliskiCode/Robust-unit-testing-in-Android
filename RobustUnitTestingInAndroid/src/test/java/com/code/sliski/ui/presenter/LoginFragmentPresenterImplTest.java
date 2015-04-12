package com.code.sliski.ui.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import com.code.sliski.preference.PreferencesManager;
import com.code.sliski.ui.fragment.LoginFragmentView;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SuppressWarnings("ConstantConditions")
@RunWith(RobolectricTestRunner.class)
public class LoginFragmentPresenterImplTest {

    private LoginFragmentPresenterImpl loginFragmentPresenter;

    @Before
    public void setUp() throws Exception {
        loginFragmentPresenter = new LoginFragmentPresenterImpl();
        loginFragmentPresenter.setLoginFragmentView(mock(LoginFragmentView.class));
        loginFragmentPresenter.setPreferencesManager(getPreferencesManager());
    }

    @Test
    public void login_ShouldShowBadFormatInfo() throws Exception {
        loginFragmentPresenter.login("");
        verify(loginFragmentPresenter.getLoginFragmentView()).showBadFormatInfo();
    }

    @Test
    public void login_ShouldReplaceFragmentAndSaveIdToPrefs() throws Exception {
        loginFragmentPresenter.login("1408086");
        verify(
                loginFragmentPresenter.getLoginFragmentView()
        ).replaceWithUserInfoFragment();

        assertEquals(
                Long.valueOf(1408086L),
                loginFragmentPresenter.getPreferencesManager().userId().getOr(0l)
        );
    }

    @NotNull
    private PreferencesManager getPreferencesManager() {
        SharedPreferences sharedPreferences = Robolectric.application.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
        return new PreferencesManager(sharedPreferences);
    }
}