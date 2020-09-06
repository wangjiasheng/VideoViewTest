package com.fourkgarden.myapplication

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*

class LayoutManager(context: Context?) : LinearLayoutManager(context) {
    private val pagerSnapHelper: PagerSnapHelper = PagerSnapHelper()
    private var mRecyclerView: RecyclerView? = null
    private var pagerListener: OnViewPagerListener? = null
    private var mDrift = 0
    override fun onAttachedToWindow(view: RecyclerView) {
        super.onAttachedToWindow(view)
        pagerSnapHelper.attachToRecyclerView(view)
        mRecyclerView = view
        mRecyclerView!!.addOnChildAttachStateChangeListener(listener)
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            val view = pagerSnapHelper.findSnapView(this)
            val positon = getPosition(view!!)
            if (pagerListener != null) {
                pagerListener!!.onPageSelect(view,positon, positon == itemCount - 1)
            }
        }
    }

    override fun onLayoutCompleted(state: RecyclerView.State) {
        super.onLayoutCompleted(state)
        if (pagerListener != null) {
            pagerListener!!.onLayoutComplete()
        }
    }

    override fun scrollVerticallyBy(dy: Int, recycler: Recycler, state: RecyclerView.State): Int {
        mDrift = dy
        return super.scrollVerticallyBy(dy, recycler, state)
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: Recycler, state: RecyclerView.State): Int {
        mDrift = dx
        return super.scrollHorizontallyBy(dx, recycler, state)
    }

    fun setPagerListener(listener: OnViewPagerListener?) {
        pagerListener = listener
    }

    private val listener: OnChildAttachStateChangeListener = object : OnChildAttachStateChangeListener {
        override fun onChildViewAttachedToWindow(view: View) {}
        override fun onChildViewDetachedFromWindow(view: View) {
            if (mDrift >= 0) {
                if (pagerListener != null) {
                    pagerListener!!.onPageRelease(view,true, getPosition(view))
                }
            } else {
                if (pagerListener != null) {
                    pagerListener!!.onPageRelease(view,true, getPosition(view))
                }
            }
        }
    }

    interface OnViewPagerListener {
        fun onPageRelease(view:View,isNext: Boolean, positon: Int)
        fun onPageSelect(view:View ,position: Int, isBottom: Boolean)
        fun onLayoutComplete()
    }

}