package com.example.starfood.ui
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin
class WheelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF: RectF = RectF()
    private var startAngle = 90f // Start the wheel at the top (90 degrees)
    private var sweepAngle = 360f
    private var rotationAngle = 0f
    public var segments = emptyArray<String>()
    public var segmentsToSelect= emptyArray<String>()
    private val colors = intArrayOf(
        Color.RED, Color.GREEN, Color.BLUE, Color.rgb(4, 124, 253),
        Color.CYAN, Color.MAGENTA, Color.DKGRAY, Color.rgb(23, 45, 66),
        Color.rgb(23, 45, 152), Color.rgb(23, 45, 255)
    )
    init {
        init()
    }
    private fun init() {
        // No additional initialization needed as paint and rectF are already initialized
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val minDimension = min(w, h)
        val padding = minDimension / 10
        rectF.set(padding.toFloat(), padding.toFloat(), (minDimension - padding).toFloat(), (minDimension - padding).toFloat())
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.rotate(rotationAngle, width / 2f, height / 2f)
        val segmentAngle = sweepAngle / segments.size
        for (i in segments.indices) {
            // Draw the arc segment
            paint.color = colors[i]
            canvas.drawArc(rectF, startAngle + i * segmentAngle, segmentAngle, true, paint)
            // Set up text paint
            paint.color = Color.WHITE
            paint.textSize = 30f
            paint.textAlign = Paint.Align.CENTER
            val midAngle = Math.toRadians((startAngle + (i + 0.5) * segmentAngle).toDouble())
            val radius = rectF.width() / 2 * 0.75f
            val x = (width / 2 + radius * cos(midAngle)).toFloat()
            val y = (height / 2 + radius * sin(midAngle)).toFloat()
            canvas.save()
            canvas.rotate(
                180 + startAngle + i * segmentAngle + segmentAngle / 2,
                x,
                y)
            canvas.drawText(segments[i], x, y, paint)
            canvas.restore()
        }
    }
    fun spin(onSpinComplete: (String) -> Unit) {
        val animator = ValueAnimator.ofFloat(0f, 360f * 5 + (Math.random() * 360).toFloat())
        animator.duration = 5000
        animator.interpolator = DecelerateInterpolator()
        animator.addUpdateListener { animation ->
            rotationAngle = animation.animatedValue as Float
            invalidate()
        }
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                val selectedSegmentIndex = ((rotationAngle % 360) / (360 / segmentsToSelect.size)).toInt()
                onSpinComplete(segmentsToSelect[selectedSegmentIndex])
            }
        })
        animator.start()
    }
}





































