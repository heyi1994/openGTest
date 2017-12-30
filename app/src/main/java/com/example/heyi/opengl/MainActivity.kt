package com.example.heyi.opengl

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.heyi.opengl.opengl.GiftGlSurfaceView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(GiftGlSurfaceView(this))
    }
}
