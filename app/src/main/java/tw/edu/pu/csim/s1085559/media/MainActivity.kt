package tw.edu.pu.csim.s1085559.media

import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView

class MainActivity : AppCompatActivity() {
    lateinit var mper: MediaPlayer
    lateinit var vdv: VideoView
    lateinit var vidControl: MediaController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mper = MediaPlayer()

        //不要自動休眠
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        //設定螢幕水平顯示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        //隱藏狀態列
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let {
                it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        vdv = findViewById<VideoView>(R.id.vdv)
        vidControl = MediaController(this)
        vdv.setMediaController(vidControl)




        var btn1: Button = findViewById(R.id.btn1)
        btn1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mper.reset()
                mper = MediaPlayer.create(baseContext, R.raw.tcyang)
                mper.start()
            }
        })
        var btn2: Button = findViewById(R.id.btn2)
        btn1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mper.reset()
                mper = MediaPlayer.create(baseContext, R.raw.fly)
                mper.start()
            }

        })
        var btn3: Button = findViewById(R.id.btn3)
        btn3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //vdv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.love))
                vdv.setVideoPath("http://www.ebookfrenzy.com/android_book/movie.mp4")

                vdv.start()
            }
    })
    }
        override fun onDestroy() {
            super.onDestroy()
            if (mper != null) {
                mper.release()
            }
        }

        override fun onPause() {
            super.onPause()
            if (mper != null && mper.isPlaying()) {
                mper.pause()
            } else {
                mper.reset()
            }
        }

        override fun onResume() {
            super.onResume()
            if (mper != null) {
                mper.start()
            }
        }

    }





