package com.code.sliski.mainscreen.ui

import android.os.Bundle
import com.code.sliski.UserRole
import spock.lang.Specification
import spock.lang.Unroll

import static com.code.sliski.UserRole.*

class MainActivityPresenterTest extends Specification {

    MainActivityPresenter tested
    View viewMock = Mock(View)

    def userRoleDictionary = [(HERO) : 'You are a Hero.',
                              (GRUNT): 'You are a Grunt.',
                              (PEON) : 'You are a Peon.']

    def setupPresenter(long userId, UserRole userRole = HERO) {
        tested = new MainActivityPresenter(userId, userRole, userRoleDictionary)
        tested.attach(viewMock)
    }

    def "should not interact with view"() {
        given:
        setupPresenter(1L)

        when:
        tested.present(Mock(Bundle))

        then:
        0 * viewMock._
    }

    @Unroll
    def "#should not show LoginFragment"() {
        given:
        setupPresenter(userId)

        when:
        tested.present(null)

        then:
        times * viewMock.showLoginScreen()

        where:
        times | should        | userId
        1     | "should"      | 0
        0     | "should not " | 1
    }

    @Unroll
    def "#should show UserInfoScreen"() {
        given:
        setupPresenter(userId)

        when:
        tested.present(null)

        then:
        times * viewMock.showUserInfoScreen()

        where:
        times | should        | userId
        1     | "should"      | 1
        0     | "should not " | 0
    }

    @Unroll
    def "#should display #message"() {
        given:
        setupPresenter(userId, userRole)

        when:
        tested.present(null)

        then:
        times * viewMock.displayMessage(message)

        where:
        times | should        | userId | userRole | message
        1     | "should"      | 1      | HERO     | "You are a Hero."
        1     | "should"      | 1      | GRUNT    | "You are a Grunt."
        1     | "should"      | 1      | PEON     | "You are a Peon."
        0     | "should not " | 0      | HERO     | "You are a Hero."
        0     | "should not " | 0      | GRUNT    | "You are a Grunt."
        0     | "should not " | 0      | PEON     | "You are a Peon."
    }
}