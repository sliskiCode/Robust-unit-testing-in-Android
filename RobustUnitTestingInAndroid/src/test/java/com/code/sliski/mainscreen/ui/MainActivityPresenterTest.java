package com.code.sliski.mainscreen.ui;

import android.os.Bundle;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class MainActivityPresenterTest {

    private MainActivityPresenter mainActivityPresenter;

    // Mocks
    private View mainActivityPresenterView;

    @Test
    public void presentTestShouldNotInteractWithView() throws Exception {
        // GIVEN
        mainActivityPresenterView = mock(View.class);
        mainActivityPresenter = new MainActivityPresenter(1L);
        mainActivityPresenter.attach(mainActivityPresenterView);

        // WHEN
        mainActivityPresenter.present(mock(Bundle.class));

        // THEN
        verifyZeroInteractions(mainActivityPresenterView);
    }

    @Test
    public void presentTestShouldShowLoginFragment() throws Exception {
        // GIVEN
        mainActivityPresenterView = mock(View.class);
        mainActivityPresenter = new MainActivityPresenter(0L);
        mainActivityPresenter.attach(mainActivityPresenterView);

        // WHEN
        mainActivityPresenter.present(null);

        // THEN
        verify(mainActivityPresenterView, times(1)).showLoginScreen();
    }

    @Test
    public void presentTestShouldShowUserInfoScreen() throws Exception {
        // GIVEN
        mainActivityPresenterView = mock(View.class);
        mainActivityPresenter = new MainActivityPresenter(1L);
        mainActivityPresenter.attach(mainActivityPresenterView);

        // WHEN
        mainActivityPresenter.present(null);

        // THEN
        verify(mainActivityPresenterView, times(1)).showUserInfoScreen();
    }
}