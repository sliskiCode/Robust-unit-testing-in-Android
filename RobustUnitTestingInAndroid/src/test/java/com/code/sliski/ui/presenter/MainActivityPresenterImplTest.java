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

    private MainActivityPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MainActivityPresenterImpl();
        presenter.setView(mock(MainActivityView.class));
        presenter.setPreferencesManager(getPreferencesManager());
    }

    @Test
    public void addFragment_ShouldNotAddFragment() throws Exception {
        presenter.addFragment(mock(Bundle.class));
        MainActivityView mainActivityView =
                presenter.getView();
        verify(mainActivityView, never()).addLoginFragment();
        verify(mainActivityView, never()).addUserInfoFragment();
    }

    @Test
    public void addFragment_ShouldAddLoginFragment() throws Exception {
        presenter.getPreferencesManager().userId().put(0L).commit();
        presenter.addFragment(null);
        verify(presenter.getView()).addLoginFragment();
    }

    @Test
    public void addFragment_ShouldAddUserInfoFragment() throws Exception {
        presenter.getPreferencesManager().userId().put(1408086L).commit();
        presenter.addFragment(null);
        verify(presenter.getView()).addUserInfoFragment();
    }

    @NotNull
    private PreferencesManager getPreferencesManager() {
        SharedPreferences sharedPreferences = Robolectric.application.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
        return new PreferencesManager(sharedPreferences);
    }
}
