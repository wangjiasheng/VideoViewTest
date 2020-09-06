package com.fourkgarden.myapplication

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.fourkgarden.myapplication.LayoutManager.OnViewPagerListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mRecycleView=findViewById<RecyclerView>(R.id.mRecycleView);
        var layoutManager=LayoutManager(this);
        mRecycleView.layoutManager =layoutManager
        NetworkUtils.requestNetwork(Handler(Looper.getMainLooper())) {
            mRecycleView.adapter=ListAdapter(this@MainActivity,mRecycleView,layoutManager,it)
        }
    }
}