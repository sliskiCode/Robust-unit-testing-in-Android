package com.code.sliski.mainscreen.ui;

import android.os.Bundle;
import com.code.sliski.UserRole;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(Enclosed.class)
public class MainActivityPresenterTest {

    static Map<UserRole, String> userRoleDictionary = new HashMap<>();

    static {
        userRoleDictionary.put(UserRole.HERO, "You are a Hero.");
        userRoleDictionary.put(UserRole.GRUNT, "You are a Grunt.");
        userRoleDictionary.put(UserRole.PEON, "You are a Peon.");
    }

    public static class IndividualTests {

        private MainActivityPresenter mainActivityPresenter;

        // Mocks
        private View mainActivityPresenterView;

        @Test
        public void presentTestShouldNotInteractWithView() throws Exception {
            // GIVEN
            mainActivityPresenterView = mock(View.class);
            mainActivityPresenter = new MainActivityPresenter(1L, UserRole.HERO, userRoleDictionary);
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
            mainActivityPresenter = new MainActivityPresenter(0L, UserRole.HERO, userRoleDictionary);
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
            mainActivityPresenter = new MainActivityPresenter(1L, UserRole.HERO, userRoleDictionary);
            mainActivityPresenter.attach(mainActivityPresenterView);

            // WHEN
            mainActivityPresenter.present(null);

            // THEN
            verify(mainActivityPresenterView, times(1)).showUserInfoScreen();
        }
    }

    @RunWith(Parameterized.class)
    public static class ParameterizedTests {

        private MainActivityPresenter mainActivityPresenter;

        // Mocks
        private View mainActivityPresenterView;

        @Parameters(name = "{2}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {1, UserRole.HERO, "You are a Hero.", 1},
                    {1, UserRole.GRUNT, "You are a Grunt.", 1},
                    {1, UserRole.PEON, "You are a Peon.", 1},
                    {0, UserRole.HERO, "No Message", 0},
                    {0, UserRole.GRUNT, "No Message", 0},
                    {0, UserRole.PEON, "No Message", 0}
            });
        }

        private long userId;
        private UserRole userRole;
        private String message;
        private int times;

        public ParameterizedTests(long userId, UserRole userRole, String message, int times) {
            this.userId = userId;
            this.userRole = userRole;
            this.message = message;
            this.times = times;
        }

        @Test
        public void presentTestShouldDisplay() {
            // GIVEN
            mainActivityPresenterView = mock(View.class);
            mainActivityPresenter = new MainActivityPresenter(userId, userRole, userRoleDictionary);
            mainActivityPresenter.attach(mainActivityPresenterView);

            // WHEN
            mainActivityPresenter.present(null);

            // THEN
            verify(mainActivityPresenterView, times(times)).displayMessage(message);
        }
    }
}