package com.example.heyi.opengl.opengl

import android.content.Context
import android.opengl.GLSurfaceView

/**
 * #### GlSurfaceView的实现
 *  - [GLSurfaceView.RENDERMODE_WHEN_DIRTY] : 置来更改绘图数据时，将渲染模式设置为仅绘制视图;
 *    **此设置可防止重绘GLSurfaceView框架，直到您调用requestRender() ;**
 *
 * @author Heyi
 * @since 1.0.0
 */
class GiftGlSurfaceView(context: Context):GLSurfaceView(context){

    init {
        setEGLContextClientVersion(2)

        setRenderer(GiftGlRender())

        renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }
}