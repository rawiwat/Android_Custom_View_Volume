package com.example.ballin.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.media.AudioManager
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.Float.min
import java.lang.reflect.Array.get
import kotlin.math.min as min1

interface MovingBallListener {
    fun onTap(view: movingBall)
}

class movingBall(context: Context?, attrs: AttributeSet?): View(context,attrs) {

    private var listener: MovingBallListener? = null

    private val paint = Paint()
    private val paint2 = Paint()
    var startX = 525f
    var startY = 400f

    //private var diffX:Float = 0f
    private var angle = 0f

    fun setListener(listener: MovingBallListener) {
        this.listener = listener
    }

    @SuppressLint("SetTextI18n")
    override fun onTouchEvent(event: MotionEvent): Boolean { // triggered each time the touch state changes


        this.listener?.onTap(this)
        when (event.action) {
            MotionEvent.ACTION_DOWN // triggered when view is touched
            -> {volumeText?.visibility = VISIBLE
                var diffX = 544 - startX
                var diffY = 680 - startY
                var distance = Math.sqrt((diffX*diffX+diffY*diffY).toDouble())
                val radius = min1(width,height) /2
                var angle = Math.atan2(diffY.toDouble(),diffX.toDouble())
                startX = (544 - (radius - radius*.15) * Math.cos(angle)).toFloat()
                startY = (680 - (radius - radius*.15) * Math.sin(angle)).toFloat()

                volumeText?.text = "x:"+startX+"y:"+startY
            }
            MotionEvent.ACTION_MOVE // triggered after ACTION_DOWN but when touch is moved
            ->{
//                diffX = startX - event.x
//                diffY =  startY - event.y
                startX = event.x
                startY = event.y
                positionAdjustment()

                println("x:"+startX)
                println("y:"+startY)
                volumeText?.text = "x:"+startX+"y:"+startY
            //audioManager.adjustVolume(angle.toInt(),0)
                }
                // get end point and calculate total distance traveled
                // use the total distance traveled to calculate the desired change in rotation
                // apply that change to your rotation variable
                // you may want to use a minimum and maximum rotation value to limit the rotation
                // use the new rotation to convert to the desired volume setting
                // this will cause the onDraw method to be called again with your new values
            MotionEvent.ACTION_UP // triggered when touch ends
         -> {fun audio(context: Context?) {
                val audioManager = context?.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                var volume = Math.abs(angle*50)
                audioManager.adjustVolume(volume.toInt(),AudioManager.FLAG_PLAY_SOUND)
                val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
                println(volume)
                volumeText?.visibility = INVISIBLE
         }}
        }
        invalidate() // get and store start point with event.getX()
        return true // this indicates that the event has been processed
    }

//    fun tapListener() {
//
//    }

    private fun positionAdjustment() {
        var diffX = 544 - startX
        var diffY = 680 - startY
        var distance = Math.sqrt((diffX * diffX + diffY * diffY).toDouble())
        val radius = min1(width, height) / 2
        var angle = Math.atan2(diffY.toDouble(), diffX.toDouble())
        println("angle" + angle)
        if (distance > radius - radius * .15) {

            println(angle)
            startX = (544 - (radius - radius * .15) * Math.cos(angle)).toFloat()
            startY = (680 - (radius - radius * .15) * Math.sin(angle)).toFloat()

        } else if (distance < radius - radius * .15) {

            val volume: Int

            startX = (544 - (radius - radius * .15) * Math.cos(angle)).toFloat()
            startY = (680 - (radius - radius * .15) * Math.sin(angle)).toFloat()
        }
    }

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        val width = width.toFloat()
        val height = height.toFloat()
        val radius = min1(width,height) /2

        paint.color = Color.BLUE
        paint2.color = Color.YELLOW

        canvas.drawCircle(width/2,height/2,radius,paint)
        canvas.drawCircle(startX, startY,radius*.15.toFloat(),paint2)
    }
}