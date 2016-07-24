package com.code.sliski.loginscreen.ui

import com.code.sliski.preference.PreferencesManager
import spock.lang.Specification
import spock.lang.Unroll

class LoginFragmentPresenterTest extends Specification {

    PreferencesManager preferencesManagerMock = Mock(PreferencesManager)
    LoginFragmentMVP.View viewMock = Mock(LoginFragmentMVP.View)

    LoginFragmentPresenter tested

    void setup() {
        tested = new LoginFragmentPresenter(preferencesManagerMock)
        tested.attachView(viewMock)
    }

    @Unroll
    def "attemptLogin #should save user id to preferences manager"() {
        when:
        tested.attemptLogin(userId)

        then:
        times * preferencesManagerMock.saveUserId(userIdLong as Long)

        where:
        should       | times | userId       | userIdLong
        "should"     | 1     | "1408086"    | 1408086
        "should not" | 0     | "1408086ABC" | _
    }

    @Unroll
    def "attemptLogin #should go to user info"() {
        when:
        tested.attemptLogin(userId)

        then:
        times * viewMock.goToUserInfo()

        where:
        should       | times | userId
        "should"     | 1     | "1408086"
        "should not" | 0     | "1408086ABC"
    }

    @Unroll
    def "attemptLogin #should show bad format info"() {
        when:
        tested.attemptLogin(userId)

        then:
        times * viewMock.showBadFormatInfo()

        where:
        should       | times | userId
        "should"     | 1     | "1408086ABC"
        "should not" | 0     | "1408086"
    }
}