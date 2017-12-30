package com.example.heyi.opengl.shape

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import com.example.heyi.opengl.opengl.GiftGlRender
import java.nio.ByteBuffer
import java.nio.FloatBuffer
import java.nio.ByteBuffer.allocateDirect
import java.nio.ByteOrder


/**
 * #### 定义一个三角形
 *  - OpenGL ES允许您使用三维空间中的坐标定义绘制对象。所以，在你画三角形之前，你必须定义它的坐标。
 *    在opengl中，这种方法的典型方法是定义一个浮点数的顶点数组作为坐标。为最大限度地提高效率，
 *    你写的这些坐标为 [ByteBuffer],即通过OpenGL ES图形流水线处理。
 *
 *  - 默认情况下，OpenGL ES的假定坐标系在[0,0,0]（x，y，z）指定的glsurfaceview框架中心，[ 1,1,0 ]是右上角的框和[ 1，-1,0 ]是框的左下角。
 *    有关这个坐标系统的示例，请参见OpenGL ES开发人员指南。注意，这个形状的坐标是按逆时针顺序定义的。
 *    绘图顺序很重要，因为它定义了哪一个面是形状的正面，您通常希望绘制它，而背面可以选择使用opengl ES人脸特征来绘制。
 *    有关人脸和裁剪的更多信息，请参见OpenGL ES开发人员指南。
 *
 * #### 绘制图形：
 *      使用OpenGL ES 2绘制定义的形状需要大量的代码，因为您必须向图形呈现管道提供很多细节。具体来说，您必须定义以下内容
 *  - Vertex Shader- OpenGL ES图形代码渲染顶点的形状。
 *  - Fragment Shader-用于渲染具有颜色或纹理的形状的面部ES代码。
 *  - Program ——一个OpenGL ES对象，它包含用于绘制一个或多个形状的着色器。
 *
 *     此时，您准备添加绘制您的形状的实际调用。使用OpenGL ES绘制图形需要指定几个参数，
 *  以告诉渲染管道您想要绘制什么以及如何绘制它。由于绘图选项可以根据形状而变化，所以让您的形状类包含自己的绘图逻辑是一个好主意。
 *  创建用于绘制形状的draw()方法。此代码将位置和颜色值设置为形状的顶点着色器和片段着色器，然后执行绘图功能。
 *
 *
 *
 * @author Heyi
 * @since 1.0.0
 */
class Rriangle {
    private var buffer:FloatBuffer
    private val COORDS_PER_VERTEX = 3
    private var mProgram:Int

    private val vertexShaderCode = "attribute vec4 vPosition;" +
    "void main() {" +
    "  gl_Position = vPosition;" +
    "}"

    private val fragmentShaderCode = (
    "precision mediump float;" +
    "uniform vec4 vColor;" +
    "void main() {" +
    "  gl_FragColor = vColor;" +
     "}")
    private var mPositionHandle: Int = 0
    private var mColorHandle: Int = 0



    /**
     * 数组中每个顶点的坐标数
     *  - 逆时针顺序
     */
    private var triangleCoords = floatArrayOf(
            0.0f, 0.622008459f, 0.0f, // top
            -0.5f, -0.311004243f, 0.0f, // bottom left
            0.5f, -0.311004243f, 0.0f  // bottom right
    )
    // Set color with red, green, blue and alpha (opacity) values
    private var color = floatArrayOf(0.63671875f, 0.76953125f, 0.22265625f, 1.0f)
    private val vertexCount= triangleCoords.size / COORDS_PER_VERTEX
    private val vertexStride = COORDS_PER_VERTEX * 4 // 4 bytes per vertex

    init {

        //为形状坐标初始化顶点字节缓冲区
        val bb = ByteBuffer.allocateDirect(
                // (坐标值的数目*每浮点数4字节)
                triangleCoords.size * 4)

        //使用设备硬件的本机字节顺序
        bb.order(ByteOrder.nativeOrder())

        //创建一个浮点缓冲区从ByteBuffer
        buffer = bb.asFloatBuffer()

        // 添加坐标的FloatBuffer
        buffer.put(triangleCoords)

        // 设置缓冲区以读取第一个坐标;
        buffer.position(0);

        val  vertexShader = GiftGlRender.loadShader(GLES20.GL_VERTEX_SHADER,vertexShaderCode)
        var fragmentShader = GiftGlRender.loadShader(GLES20.GL_FRAGMENT_SHADER,fragmentShaderCode)

        // create empty OpenGL ES Program
        mProgram=GLES20.glCreateProgram()

        // add the vertex shader to program
       GLES20.glAttachShader(mProgram,vertexShader)

        // add the fragment shader to program
        GLES20.glAttachShader(mProgram,fragmentShader)

        // creates OpenGL ES program executables 创建OpenGL ES程序的可执行文件
        GLES20.glLinkProgram(mProgram)

    }


    fun draw(){
       // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram)

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition")

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle)

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, buffer)

        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor")

        // Set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0)

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle)

    }

}