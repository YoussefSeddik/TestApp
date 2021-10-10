package com.example.testapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.graphics.*
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class dd {
//    class MainActivity : AppCompatActivity() {
//        private val paint = Paint()
//        private val path = Path()
//        private val canvas = Canvas()
//        override fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//            setContentView(R.layout.activity_main)
//
//            paint.isAntiAlias = true
//            path.reset()
//            paint.color = Color.BLACK
//            paint.style = Paint.Style.STROKE
//            var bezierFactor = 2f
//            val x0 = 370f
//            val y0 = 1260f
//            val xEnd = 370f
//            val yEnd = 0f
//            val size = 600f
//            val cPoint1 = Point((x0 - size / bezierFactor).toInt(), (y0 - size / 6).toInt())
//            val cPoint2 = Point((xEnd + size / bezierFactor).toInt(), (yEnd + size / 6).toInt())
//            path.moveTo(x0, y0)
//            path.cubicTo(cPoint1.x.toFloat(), cPoint1.y.toFloat(), cPoint2.x.toFloat(), cPoint2.y.toFloat(), xEnd, yEnd)
//            canvas.drawPath(path, paint)
//            val pathMeasure = PathMeasure(path, false)
//            val point = FloatArray(2)
//            point[0] = x0
//            point[1] = y0
//
//            findViewById<Button>(R.id.startButton).setOnClickListener {
//                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.heart)
//                val pathAnimator = ValueAnimator.ofFloat(0.1f, 1.0f)
//                pathAnimator.addUpdateListener { animation ->
//                    val animatedFraction = animation.animatedFraction
//                    pathMeasure.getPosTan(pathMeasure.length * animatedFraction, point, null)
//                    val dest = Rect().apply {
//                        left = (point[0] - size / 4 * animatedFraction).toInt()
//                        right = (point[0] + size / 4 * animatedFraction).toInt()
//                        top = (point[1] - size / 2 * animatedFraction).toInt()
//                        bottom = point[1].toInt()
//                    }
//                    val src = Rect(0, 0, size.toInt(), size.toInt())
//                    canvas.drawBitmap(bitmap, src, dest, paint)
//                }
//                pathAnimator.addListener(object : AnimatorListenerAdapter() {
//                    override fun onAnimationStart(animation: Animator?) {
//                        super.onAnimationStart(animation)
//                    }
//                })
//                pathAnimator.interpolator = AccelerateDecelerateInterpolator()
//                pathAnimator.duration = 2000
//                pathAnimator.start()
//            }
//        }
//
//        private fun getRandomNumber(min: Float, max: Float): Float {
//            return Random().nextInt((max - min + 1).toInt()) + min
//        }
//    }
}