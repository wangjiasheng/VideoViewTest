package com.fourkgarden.myapplication

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val context: Context,private val mRecycleView:RecyclerView,private val layoutManager: LayoutManager,private val list: MutableList<MovieBean>) : RecyclerView.Adapter<ListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ListHolder(view)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.mTextView!!.text =list[position].name
    }

    override fun getItemCount(): Int {
        return list?.size;
    }
    init {
        layoutManager.setPagerListener(object : LayoutManager.OnViewPagerListener {
            override fun onPageRelease(view :View,isNext: Boolean, positon: Int) {
            }
            override fun onPageSelect(view:View,position: Int, isBottom: Boolean) {
                var holder:ListHolder =view.getTag(R.id.holder) as ListHolder
                var mVideoView=holder.mVideoView
                var mediaPlayer = holder.mediaPlayer
                if (mediaPlayer != null) {
                    if(mediaPlayer.isPlaying){
                        mediaPlayer?.stop()
                    }
                }
                mediaPlayer?.reset()
                mediaPlayer?.setDataSource(list[position].url)
                if (mVideoView != null) {
                    mediaPlayer?.setSurface(Surface(mVideoView.surfaceTexture))
                    Log.i("wangjiasheng","setSurface")
                }
                mediaPlayer?.setOnErrorListener(object :MediaPlayer.OnErrorListener{
                    override fun onError(p0: MediaPlayer?, p1: Int, p2: Int): Boolean {
                        Log.i("wangjiasheng",""+p1)
                        return true;
                    }
                })
                mediaPlayer?.prepareAsync()
                mediaPlayer?.setOnPreparedListener(object :MediaPlayer.OnPreparedListener{
                    override fun onPrepared(p0: MediaPlayer?) {
                        p0?.start()
                        Log.i("wangjiasheng","start")
                    }
                })


            }
            override fun onLayoutComplete() {

            }
        })
    }
}