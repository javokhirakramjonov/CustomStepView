package uz.gita.customstepview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class MyStepView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    private var paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }
    private var radius = 0
    private var length = 0
    private var countSteps: Int = 0
    fun setSteps(value: Int) {
        if (value in 2..5) {
            countSteps = value
            currentStep = min(currentStep, countSteps + 1)
            radius = width / (6 * value - 4)
            length = radius * 4
            invalidate()
        }
    }

    private var currentStep: Int = 1
    fun setCurrentPos(value: Int) {
        if (value in 1..(countSteps + 1)) {
            currentStep = value
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawSteps(canvas)
        drawCurrentStep(canvas)
    }

    private fun drawSteps(canvas: Canvas) {
        paint.color = Color.parseColor("#E9F1F4")
        var x = radius.toFloat()
        var y = height / 2f
        canvas.drawCircle(x, y, radius.toFloat(), paint)
        for (i in 1 until countSteps) {
            x += radius
            paint.strokeWidth = 20f
            canvas.drawLine(x - radius, y, x + length + radius, y, paint)
            paint.strokeWidth = 0f
            x += length + radius
            canvas.drawCircle(x, y, radius.toFloat(), paint)
        }
    }

    private fun drawCurrentStep(canvas: Canvas) {
        paint.color = Color.parseColor("#0059A4")
        var x = radius.toFloat()
        val y = height / 2f
        val tick = resources.getDrawable(R.drawable.ic_tick)

        for (i in 1 until currentStep) {
            paint.style = Paint.Style.FILL
            canvas.drawCircle(x, y, radius.toFloat(), paint)
            tick.setBounds(
                (x - radius + 10).toInt(), (y - radius + 10).toInt(), (x + radius - 10).toInt(), (y +
                        radius - 10)
                    .toInt()
            )

            x += radius
            paint.strokeWidth = 20f
            if (i < countSteps)
                canvas.drawLine(x - radius, y, x + length + radius, y, paint)

            tick.draw(canvas)

            paint.strokeWidth = 0f
            x += length + radius
        }
        if (currentStep <= countSteps) {
            paint.strokeWidth = 10f
            paint.style = Paint.Style.STROKE
            canvas.drawCircle(x, y, radius.toFloat() - 5, paint)

            paint.color = Color.WHITE
            paint.strokeWidth = 0f
            paint.style = Paint.Style.FILL
            canvas.drawCircle(x, y, radius.toFloat() - 5, paint)
        }
    }
}