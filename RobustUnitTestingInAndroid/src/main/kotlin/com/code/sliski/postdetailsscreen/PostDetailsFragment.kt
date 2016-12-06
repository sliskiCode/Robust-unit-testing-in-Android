package com.code.sliski.postdetailsscreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.code.sliski.R
import com.code.sliski.postlistscreen.ui.model.PresentationPost
import kotlinx.android.synthetic.main.details_fragment.*

class PostDetailsFragment : Fragment(),
                            OnPostClickListener {

    companion object {
        val POST: String = "POST"

        fun getInstance(post: PresentationPost): PostDetailsFragment {
            val fragment = PostDetailsFragment()
            val arguments = Bundle()
            arguments.putParcelable(POST, post)
            fragment.arguments = arguments
            return fragment
        }
    }

    private lateinit var post: PresentationPost

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.details_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        post = arguments.getParcelable(POST)

        updateView()
    }

    override fun onPostClick(post: PresentationPost) {
        this.post = post
        updateView()
    }

    private fun updateView() {
        score.text = post.score.toString()
        link.text = post.link
    }
}