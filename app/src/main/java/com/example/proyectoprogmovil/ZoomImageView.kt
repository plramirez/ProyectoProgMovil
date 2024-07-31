package com.example.proyectoprogmovil

import android.content.Context
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.ScaleGestureDetector
import android.view.ViewTreeObserver
import androidx.appcompat.widget.AppCompatImageView

class ZoomImageView(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {

    private val scaleGestureDetector = ScaleGestureDetector(context, ScaleListener())
    private var scaleFactor = 1.0f
    private val matrix = Matrix()
    private var minScale = 1.0f
    private var maxScale = 5.0f

    init {
        scaleType = ScaleType.MATRIX

        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (drawable != null) {
                    val imageWidth = drawable.intrinsicWidth
                    val imageHeight = drawable.intrinsicHeight
                    val viewWidth = width.toFloat()
                    val viewHeight = height.toFloat()

                    val scale: Float = if (imageWidth > imageHeight) {
                        viewWidth / imageWidth
                    } else {
                        viewHeight / imageHeight
                    }

                    val dx = (viewWidth - imageWidth * scale) / 2f
                    val dy = (viewHeight - imageHeight * scale) / 2f

                    matrix.setScale(scale, scale)
                    matrix.postTranslate(dx, dy)
                    imageMatrix = matrix

                    minScale = scale
                    maxScale = 5 * scale
                }
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    override fun onTouchEvent(event: android.view.MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        return true
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scaleFactor *= detector.scaleFactor
            scaleFactor = Math.max(minScale, Math.min(scaleFactor, maxScale))
            matrix.setScale(scaleFactor, scaleFactor, detector.focusX, detector.focusY)
            imageMatrix = matrix
            return true
        }
    }
}