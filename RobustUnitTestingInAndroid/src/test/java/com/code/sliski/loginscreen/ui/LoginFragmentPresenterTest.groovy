package com.code.sliski.loginscreen.ui

import com.code.sliski.preference.PreferencesManager
import spock.lang.Specification
import spock.lang.Unroll

class LoginFragmentPresenterTest extends Specification {

    PreferencesManager preferencesManagerMock = Mock(PreferencesManager)
    View viewMock = Mock(View)

    LoginFragmentPresenter tested

    void setup() {
        tested = new LoginFragmentPresenter(preferencesManagerMock)
        tested.attach(viewMock)
    }

    @Unroll
    def "#should save user id to preferences manager"() {
        when:
        tested.present(userId)

        then:
        times * preferencesManagerMock.saveUserId(userIdLong as Long)

        where:
        should       | times | userId       | userIdLong
        "should"     | 1     | "1408086"    | 1408086
        "should not" | 0     | "1408086ABC" | _
    }

    @Unroll
    def "#should go to user info"() {
        when:
        tested.present(userId)

        then:
        times * viewMock.showUserInfoScreen()

        where:
        should       | times | userId
        "should"     | 1     | "1408086"
        "should not" | 0     | "1408086ABC"
    }

    @Unroll
    def "#should show bad format info"() {
        when:
        tested.present(userId)

        then:
        times * viewMock.showBadFormatInfo()

        where:
        should       | times | userId
        "should"     | 1     | "1408086ABC"
        "should not" | 0     | "1408086"
    }
}