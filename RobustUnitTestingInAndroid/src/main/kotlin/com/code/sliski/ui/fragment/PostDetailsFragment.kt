package com.code.sliski.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.code.sliski.event.OnPostClickedEvent
import com.code.sliski.model.Post
import com.code.sliski.ui.BaseApplication
import com.code.sliski.ui.R
import de.greenrobot.event.EventBus
import java.lang
import javax.inject.Inject

public class PostDetailsFragment : Fragment() {

    public val POST: String = "POST"

    var mPost: Post? = null

    private var mScoreTextView: TextView? = null
    private var mLinkTextView: TextView? = null

    companion object Factory {
        fun getInstance(post: Post): PostDetailsFragment {
            val postDetailsFragment = PostDetailsFragment()
            postDetailsFragment.mPost = post
            return postDetailsFragment
        }
    }

    override public fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override public fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.details_fragment, container, false)
        mScoreTextView = view.findViewById(R.id.score) as TextView
        mLinkTextView = view.findViewById(R.id.link) as TextView
        return view
    }

    override public fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            mPost = savedInstanceState.getParcelable(POST)
        }
        if (mPost != null) {
            setViews()
        }
    }

    private fun setViews() {
        mScoreTextView?.setText(lang.String.valueOf(mPost?.score))
        mLinkTextView?.setText(mPost?.link)
    }

    override public fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override public fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(POST, mPost)
    }

    public fun onEvent(event: OnPostClickedEvent) {
        mPost = event.post;
        setViews()
    }
}