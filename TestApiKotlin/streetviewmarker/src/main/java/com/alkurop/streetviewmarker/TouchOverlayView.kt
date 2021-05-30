package com.alkurop.streetviewmarker

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

class TouchOverlayView @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = 0) : FrameLayout(context, attr, style) {
    var onTouchListener: ((ev: MotionEvent) -> Unit)? = null

    override fun onTouchEvent(event: MotionEvent): Boolean {
        onTouchListener?.invoke(event)
        return false
    }

    override fun onDraw(canvas: Canvas?) {

    }

}