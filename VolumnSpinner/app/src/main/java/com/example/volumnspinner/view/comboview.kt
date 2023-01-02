package com.example.volumnspinner.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.volumnspinner.R


class comboview :LinearLayout {

    constructor(context:Context):super(context)

    constructor(context:Context,attrs:AttributeSet):super(context,attrs)

    constructor(context:Context,attrs:AttributeSet,defStyleAttr:Int):super(context,attrs,defStyleAttr)

    constructor(context:Context,attrs:AttributeSet,defStyleAttr:Int,defStyleRes: Int): super(context,attrs, defStyleAttr,defStyleRes)

    val listItem = listOf("xBarkadeer"," Brethren"," of"," the"," Coast"," Sea"," Legs"," gibbet"," overhaul"," ye"," topgallant"," Gold", "Road")

    var pointer = 0
    fun incrementPointer() {
        pointer++
        if (pointer >= listItem.size){
            pointer = 0
    }
    }fun decrementPointer() {
        pointer--
        if (pointer < 0){
            pointer = listItem.size - 1
    }
    }

    init{
    /*this is just an example for reference

    val text = TextView(context)
    addView(text)*/
        val textLayoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT,8f)
        val textView = LayoutInflater.from(context).inflate(R.layout.comboview_layout,this,false) as TextView
        textView.text = listItem[pointer]

        val imageLayoutParams = LinearLayout.LayoutParams(LayoutParams(0,LayoutParams.WRAP_CONTENT,1f))

        val previousImageView = ImageView(context)
        previousImageView.layoutParams = imageLayoutParams
        previousImageView.setImageDrawable(ContextCompat.getDrawable(context,android.R.drawable.ic_media_previous))
        previousImageView.setOnClickListener{
            decrementPointer()
            textView.text = listItem[pointer]
        }

        val nextImageView = ImageView(context)
        nextImageView.layoutParams = imageLayoutParams
        nextImageView.setImageDrawable(ContextCompat.getDrawable(context,android.R.drawable.ic_media_next))
        nextImageView.setOnClickListener{
            incrementPointer()
            textView.text = listItem[pointer]

        }
        orientation = HORIZONTAL
        addView(previousImageView)
        addView(textView)
        addView(nextImageView)

}
}