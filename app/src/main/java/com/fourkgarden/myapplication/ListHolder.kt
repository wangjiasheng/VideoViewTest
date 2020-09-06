package com.fourkgarden.myapplication

import android.media.MediaPlayer
import android.view.Surface
import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ListHolder(itemView: View) : ViewHolder(itemView) {
    @JvmField
    var mTextView: TextView? = null
    var mVideoView:TextureView?=null
    var mediaPlayer:MediaPlayer?=null;

    init {
        mTextView = itemView.findViewById(R.id.mText)
        mVideoView=itemView.findViewById(R.id.mVideoView)
        mediaPlayer= MediaPlayer()
        itemView.setTag(R.id.holder,this)
    }
}