package com.code.sliski.mainscreen.ui

import android.os.Bundle
import spock.lang.Specification
import spock.lang.Unroll

class MainActivityPresenterTest extends Specification {

    MainActivityMVP.View view = Mock(MainActivityMVP.View)

    MainActivityPresenter tested

    @Unroll
    def "#should add post login fragment"() {
        given:
        tested = new MainActivityPresenter(userId)
        tested.attach(view)

        when:
        tested.present(savedInstanceState)

        then:
        times * view.showLoginScreen()

        where:
        should       | times | savedInstanceState | userId
        "should"     | 1     | null               | 0L
        "should not" | 0     | null               | 1L
        "should not" | 0     | Mock(Bundle)       | 0L
        "should not" | 0     | Mock(Bundle)       | 1L
    }

    @Unroll
    def "#should add user info fragment"() {
        given:
        tested = new MainActivityPresenter(userId)
        tested.attach(view)

        when:
        tested.present(savedInstanceState)

        then:
        times * view.showUserInfoScreen()

        where:
        should       | times | savedInstanceState | userId
        "should not" | 0     | null               | 0L
        "should"     | 1     | null               | 1L
        "should not" | 0     | Mock(Bundle)       | 0L
        "should not" | 0     | Mock(Bundle)       | 1L
    }
}
