package com.code.sliski.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.code.sliski.event.OnPostClickedEvent
import com.code.sliski.model.Post
import com.code.sliski.App
import com.code.sliski.R
import com.code.sliski.util.findView
import de.greenrobot.event.EventBus
import java.lang
import javax.inject.Inject

public class PostDetailsFragment : Fragment() {

    public val POST: String = "POST"

    var mPost: Post? = null

    var mEventBus: EventBus? = null
        [Inject] set

    companion object Factory {
        fun getInstance(post: Post): PostDetailsFragment {
            val postDetailsFragment = PostDetailsFragment()
            postDetailsFragment.mPost = post
            return postDetailsFragment
        }
    }

    override public fun onCreate(savedInstanceState: Bundle?) {
        super<Fragment>.onCreate(savedInstanceState)
        App.mGraph.inject(this)
        mEventBus?.register(this)
    }

    override public fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override public fun onActivityCreated(savedInstanceState: Bundle?) {
        super<Fragment>.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            mPost = savedInstanceState.getParcelable(POST)
        }
        if (mPost != null) {
            setViews()
        }
    }

    private fun setViews() {
        findView<TextView>(R.id.score).setText(lang.String.valueOf(mPost?.score))
        findView<TextView>(R.id.link).setText(mPost?.link)
    }

    override public fun onDestroy() {
        super<Fragment>.onDestroy()
        mEventBus?.unregister(this)
    }

    override public fun onSaveInstanceState(outState: Bundle) {
        super<Fragment>.onSaveInstanceState(outState)
        outState.putParcelable(POST, mPost)
    }

    public fun onEvent(event: OnPostClickedEvent) {
        mPost = event.post;
        setViews()
    }
}