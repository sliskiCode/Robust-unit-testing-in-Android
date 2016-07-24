package com.code.sliski.userinfoscreen.ui

import android.os.Bundle
import spock.lang.Specification
import spock.lang.Unroll

class UserInfoFragmentPresenterTest extends Specification {

    UserInfoFragmentMVP.View view = Mock(UserInfoFragmentMVP.View)

    UserInfoFragmentPresenter tested

    void setup() {
        tested = new UserInfoFragmentPresenter()
        tested.attachView(view)
    }

    @Unroll
    def "buildView #should add post list fragment"() {
        when:
        tested.buildView(savedInstanceState, isTablet)

        then:
        times * view.addPostListFragment()

        where:
        should       | times | savedInstanceState | isTablet
        "should"     | 1     | null               | false
        "should not" | 0     | Mock(Bundle)       | false
    }

    @Unroll
    def "buildView #should add post details fragment"() {
        when:
        tested.buildView(savedInstanceState, isTablet)

        then:
        times * view.addPostDetailsFragment()

        where:
        should       | times | savedInstanceState | isTablet
        "should"     | 1     | null               | true
        "should not" | 0     | Mock(Bundle)       | true
        "should not" | 0     | Mock(Bundle)       | false
    }
}
