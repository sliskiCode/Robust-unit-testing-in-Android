package com.code.sliski.mainscreen.ui

import android.os.Bundle
import com.code.sliski.UserRole
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import org.mockito.Mockito.*

var userRoleDictionary: Map<UserRole, String> = mapOf(Pair(UserRole.HERO, "You are a Hero."),
                                                      Pair(UserRole.GRUNT, "You are a Grunt."),
                                                      Pair(UserRole.PEON, "You are a Peon."))

@RunWith(Enclosed::class)
class MainActivityPresenterTest {

    class IndividualTests {

        lateinit var tested: MainActivityPresenter
        val viewMock = mock(View::class.java)

        fun setupPresenter(userId: Long) {
            tested = MainActivityPresenter(userId, UserRole.HERO, userRoleDictionary)
            tested.attach(viewMock)
        }

        @Test @Throws(Exception::class)
        fun `not interact with view`() {
            setupPresenter(1L)

            tested.present(state = mock(Bundle::class.java))

            verifyZeroInteractions(viewMock)
        }

        @Test @Throws(Exception::class)
        fun `show login fragment`() {
            setupPresenter(0L)

            tested.present(state = null)

            verify(viewMock, times(1)).showLoginScreen()
        }

        @Test @Throws(Exception::class)
        fun `not show login fragment`() {
            setupPresenter(1L)

            tested.present(state = null)

            verify(viewMock, never()).showLoginScreen()
        }

        @Test @Throws(Exception::class)
        fun `show user info screen`() {
            setupPresenter(1L)

            tested.present(state = null)

            verify(viewMock, times(1)).showUserInfoScreen()
        }

        @Test @Throws(Exception::class)
        fun `not show user info screen`() {
            setupPresenter(0L)

            tested.present(state = null)

            verify(viewMock, never()).showUserInfoScreen()
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedTests(var userId: Long,
                             var userRole: UserRole,
                             var message: String,
                             var times: Int) {

        lateinit var tested: MainActivityPresenter
        val mockView = mock(View::class.java)

        companion object {
            @Parameters(name = "{2}")
            @JvmStatic fun data() = listOf(
                    arrayOf(1, UserRole.HERO, "You are a Hero.", 1),
                    arrayOf(1, UserRole.GRUNT, "You are a Grunt.", 1),
                    arrayOf(1, UserRole.PEON, "You are a Peon.", 1),
                    arrayOf(0, UserRole.HERO, "No Message", 0),
                    arrayOf(0, UserRole.GRUNT, "No Message", 0),
                    arrayOf(0, UserRole.PEON, "No Message", 0))

        }

        @Test @Throws(Exception::class)
        fun `display message`() {
            tested = MainActivityPresenter(userId, userRole, userRoleDictionary)
            tested.attach(mockView)

            tested.present(state = null)

            verify(mockView, times(times)).displayMessage(message)
        }
    }
}