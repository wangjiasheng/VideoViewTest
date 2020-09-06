package com.fourkgarden.myapplication

import android.os.Handler
import android.os.Message
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import java.util.regex.Pattern

class NetworkUtils {
    companion object {
        fun requestNetwork(handler: Handler?,obj:(list: MutableList<MovieBean>)->Unit) {
            val list: MutableList<MovieBean> = ArrayList()
            object : Thread() {
                override fun run() {
                    list.add(MovieBean("1","http://video.wenwen.sogou.com/e75aa445e10f4a51cf9b1df72b947e40.mp4.f20.mp4"))
                    list.add(MovieBean("2","http://video.wenwen.sogou.com/8640cc39cd2abaa644dbc9f4eef4b49e.mp4.f20.mp4"))
                    list.add(MovieBean("3","http://video.wenwen.sogou.com/31bc728f5bd22e0cf6aa4898f7f78f0d.mp4.f20.mp4"))
                    list.add(MovieBean("4","http://video.wenwen.sogou.com/8cd0ffa1bea136eef0b42057f305b7b0.mp4.f20.mp4"))
                    list.add(MovieBean("5","http://video.wenwen.sogou.com/4dfd051e27a32ae319588e7066937398.mp4.f20.mp4"))
                    Message.obtain(handler) {
                       obj.invoke(list)
                    }.sendToTarget()
                }
            }.start()
        }
    }
}