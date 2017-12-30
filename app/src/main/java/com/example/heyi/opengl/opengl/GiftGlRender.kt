package com.example.heyi.opengl.opengl

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import com.example.heyi.opengl.f
import com.example.heyi.opengl.shape.Rriangle
import com.example.heyi.opengl.shape.Square
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * #### GlSurface的Reanderer
 * @author Heyi
 * @since 1.0.0
 */
class GiftGlRender:GLSurfaceView.Renderer {

    companion object {
        fun loadShader(type: Int, shaderCode: String): Int {
            // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
            // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
            val shader = GLES20.glCreateShader(type)

            // 将源代码添加到着色器并编译它
            GLES20.glShaderSource(shader, shaderCode)
            GLES20.glCompileShader(shader)
            return shader
        }
    }

    val TAG:String=this.javaClass.simpleName
    lateinit private  var mRiangle:Rriangle
    lateinit private var mSquare:Square




    /**
     * #### 设置视图的OpenGL ES环境调用一次
     */
    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
         mRiangle= Rriangle()
         mSquare= Square()

        //绘制背景帧颜色
        GLES20.glClearColor(0f,0f,0f,1f)
    }

    /**
     * #### 每次重绘调用
     */
    override fun onDrawFrame(gl: GL10?) {
        //重绘背景颜色
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        mRiangle.draw()
    }

    /**
     * #### 如果视图的几何形状发生变化（例如，当设备的屏幕方向更改时），则调用
     */
    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0,0,width,height)

    }


}