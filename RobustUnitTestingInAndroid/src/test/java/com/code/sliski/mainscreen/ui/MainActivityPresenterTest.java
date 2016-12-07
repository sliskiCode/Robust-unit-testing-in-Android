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

        MainActivityPresenter tested;
        View viewMock = mock(View.class);

        void setupPresenter(long userId, UserRole userRole) {
            tested = new MainActivityPresenter(userId, userRole, userRoleDictionary);
            tested.attach(viewMock);
        }

        @Test
        public void notInteractWithView() throws Exception {
            setupPresenter(1L, UserRole.HERO);

            tested.present(mock(Bundle.class));

            verifyZeroInteractions(viewMock);
        }

        @Test
        public void showLoginFragment() throws Exception {
            setupPresenter(0L, UserRole.HERO);

            tested.present(null);

            verify(viewMock, times(1)).showLoginScreen();
        }

        @Test
        public void showUserInfoScreen() throws Exception {
            setupPresenter(1L, UserRole.HERO);

            tested.present(null);

            verify(viewMock, times(1)).showUserInfoScreen();
        }
    }

    @RunWith(Parameterized.class)
    public static class ParameterizedTests {

        MainActivityPresenter tested;
        View mockView = mock(View.class);

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

        long userId;
        UserRole userRole;
        String message;
        int times;

        public ParameterizedTests(long userId, UserRole userRole, String message, int times) {
            this.userId = userId;
            this.userRole = userRole;
            this.message = message;
            this.times = times;
        }

        @Test
        public void displayMessage() {
            tested = new MainActivityPresenter(userId, userRole, userRoleDictionary);
            tested.attach(mockView);

            tested.present(null);

            verify(mockView, times(times)).displayMessage(message);
        }
    }
}