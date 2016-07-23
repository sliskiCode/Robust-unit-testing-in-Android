package com.code.sliski.postdetailsscreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.code.sliski.R
import com.code.sliski.api.model.Post
import kotlinx.android.synthetic.main.details_fragment.*

class PostDetailsFragment : Fragment(),
                            OnPostClickListener {

    val POST: String = "POST"

    lateinit var post: Post

    companion object {
        fun getInstance(post: Post): PostDetailsFragment {
            val postDetailsFragment = PostDetailsFragment()
            postDetailsFragment.post = post
            return postDetailsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.details_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            post = savedInstanceState.getParcelable(POST)
        }

        updateView()
    }

    override fun onPostClick(post: Post) {
        this.post = post
        updateView()
    }

    private fun updateView() {
        score.text = post.score.toString()
        link.text = post.link
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(POST, post)
    }
}