package com.code.sliski.ui.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.code.sliski.preference.PreferencesManager;
import com.code.sliski.ui.activity.MainActivityView;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SuppressWarnings("ConstantConditions")
@RunWith(RobolectricTestRunner.class)
public class MainActivityPresenterImplTest {

    private MainActivityPresenterImpl mainActivityPresenter;

    @Before
    public void setUp() throws Exception {
        mainActivityPresenter = new MainActivityPresenterImpl();
        mainActivityPresenter.setMainActivityView(mock(MainActivityView.class));
        mainActivityPresenter.setPreferencesManager(getPreferencesManager());
    }

    @Test
    public void addFragment_ShouldNotAddFragment() throws Exception {
        mainActivityPresenter.addFragment(mock(Bundle.class));
        MainActivityView mainActivityView =
                mainActivityPresenter.getMainActivityView();
        verify(mainActivityView, never()).addLoginFragment();
        verify(mainActivityView, never()).addUserInfoFragment();
    }

    @Test
    public void addFragment_ShouldAddLoginFragment() throws Exception {
        mainActivityPresenter.getPreferencesManager().userId().put(0L).commit();
        mainActivityPresenter.addFragment(null);
        verify(mainActivityPresenter.getMainActivityView()).addLoginFragment();
    }

    @Test
    public void addFragment_ShouldAddUserInfoFragment() throws Exception {
        mainActivityPresenter.getPreferencesManager().userId().put(1408086L).commit();
        mainActivityPresenter.addFragment(null);
        verify(mainActivityPresenter.getMainActivityView()).addUserInfoFragment();
    }

    @NotNull
    private PreferencesManager getPreferencesManager() {
        SharedPreferences sharedPreferences = Robolectric.application.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
        return new PreferencesManager(sharedPreferences);
    }
}
