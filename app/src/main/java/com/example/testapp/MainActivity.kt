package com.example.testapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.graphics.*
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import java.util.concurrent.ThreadLocalRandom

class MainActivity : AppCompatActivity() {
    private lateinit var stopButton: Button
    private lateinit var startButton: Button
    private var counter: CountDownTimer? = null
    private val paint = Paint()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        paint.isAntiAlias = true
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            startAnimation()
        }
        stopButton = findViewById<Button>(R.id.stopButton)
        stopButton.setOnClickListener {
            counter?.onFinish()
        }
    }

    private fun startAnimation() {
        val point = FloatArray(2)
        val canvas = Canvas()
        val x0 = startButton.x - startButton.width.div(2)
        val y0 = startButton.y
        val yEnd = 0f
        val size = 600f
        var bezierFactor = 4f
        bezierFactor = getRandomNumber(bezierFactor, 6f)
        val cPoint1 = Point((x0 - size / bezierFactor).toInt(), (y0 - size / bezierFactor).toInt())
        val cPoint2 =
            Point((x0 + size / bezierFactor).toInt(), (yEnd + size / bezierFactor).toInt())
        val path = Path()
        path.moveTo(x0, y0)
        path.cubicTo(
            cPoint1.x.toFloat(),
            cPoint1.y.toFloat(),
            cPoint2.x.toFloat(),
            cPoint2.y.toFloat(),
            getRandomNumber(x0, x0 + 100),
            yEnd
        )
        canvas.drawPath(path, paint)
        val pathMeasure = PathMeasure(path, false)
        val objectToMovImageView = ImageView(this)
        objectToMovImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.heart))
        val params = ViewGroup.LayoutParams(50, 50)
        objectToMovImageView.layoutParams = params
        findViewById<ConstraintLayout>(R.id.container).addView(objectToMovImageView)
        val pathAnimator = ValueAnimator.ofFloat(0.1f, 1.0f)

        pathAnimator.addUpdateListener { animation ->
            val animatedFraction = animation.animatedFraction
            pathMeasure.getPosTan(pathMeasure.length * animatedFraction, point, null)
            objectToMovImageView.x = getRandomNumber(point[0], point[0] + 2)
            objectToMovImageView.y = point[1]
            val newAlpha = 1 - animatedFraction
            objectToMovImageView.alpha = newAlpha
        }
        pathAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                objectToMovImageView.scaleX = .5f
                objectToMovImageView.scaleY = .5f
                objectToMovImageView.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
                objectToMovImageView.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                findViewById<ConstraintLayout>(R.id.container).removeView(objectToMovImageView)
            }
        })
        pathAnimator.duration = 1500
        pathAnimator.start()
    }

    private fun getRandomNumber(min: Float, max: Float): Float {
        return ThreadLocalRandom.current().nextDouble(min.toDouble(), max.toDouble()).toFloat()
    }
}
