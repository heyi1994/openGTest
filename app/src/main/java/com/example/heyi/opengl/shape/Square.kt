package com.example.heyi.opengl.shape

import java.nio.FloatBuffer
import java.nio.ShortBuffer
import java.nio.ByteOrder.nativeOrder
import android.R.attr.order
import java.nio.ByteBuffer
import java.nio.ByteBuffer.allocateDirect
import java.nio.ByteOrder


/**
 * #### 定义正方形
 *  - 在opengl中定义三角形是很容易的，但是如果你想得到一个稍微复杂一点的东西呢？
 *    说，一个正方形？有很多方法可以做到这一点，但在OpenGL ES中绘制这样一个形状的典型路径是使用两个三角形绘制在一起;
 *
 *  -
 * @author Heyi
 * @since 1.0.0
 */
class Square {
    private val vertexBuffer: FloatBuffer
    private val drawListBuffer: ShortBuffer

    // 数组中每个顶点的坐标数
    private val COORDS_PER_VERTEX = 3
    private var squareCoords = floatArrayOf(-0.5f, 0.5f, 0.0f, // top left
            -0.5f, -0.5f, 0.0f, // bottom left
            0.5f, -0.5f, 0.0f, // bottom right
            0.5f, 0.5f, 0.0f) // top right

    private val drawOrder = shortArrayOf(0, 1, 2, 0, 2, 3) // 绘制顶点的顺序

    init {
        // initialize vertex byte buffer for shape coordinates
        val bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                squareCoords.size * 4)
        bb.order(ByteOrder.nativeOrder())
        vertexBuffer = bb.asFloatBuffer()
        vertexBuffer.put(squareCoords)
        vertexBuffer.position(0)

        // 初始化绘图列表的字节缓冲区。
        val dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                drawOrder.size * 2)
        dlb.order(ByteOrder.nativeOrder())
        drawListBuffer = dlb.asShortBuffer()
        drawListBuffer.put(drawOrder)
        drawListBuffer.position(0)
    }


}