package com.example.testapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import androidx.appcompat.widget.AppCompatImageView


class MyView constructor(context: Context)
    : AppCompatImageView(context) {
    var p = Paint()
    var x = 0
    var y: Int = 0
    var i: Int = 1
    var j: Int = 1
    var inc: Int = 0
    var fps = 30
    var h = Handler(Looper.getMainLooper())
    val r = Runnable { invalidate() }

    init {
        p.color = Color.RED
        p.style = Paint.Style.FILL_AND_STROKE
        p.isDither = false
        p.strokeWidth = 3f
        p.isAntiAlias = true
        p.color = Color.parseColor("#CD5C5C")
        p.strokeWidth = 15f
        p.strokeJoin = Paint.Join.ROUND
        p.strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        x += i
        y += j
        if (x > 300) i = -1
        if (y > 300) j = -1
        if (x < 0) i = 1
        if (y < 0) j = 1
        canvas.drawPoint(x.toFloat(), y.toFloat(), p)
        h.postDelayed(r, fps.toLong())
    }
}