package com.example.ballin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.ballin.R
import android.view.View
import com.example.ballin.view.MovingBallListener
import com.example.ballin.view.movingBall
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovingBallListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        }

    override fun onTap(view: movingBall) {
    val inflater = LayoutInflater.from(this).inflate(R.layout.activity_main,null)
    }
    /*blueball.setOnClickListener{
        val intent = Intent(this,movingBall)
        startActivity(Intent())*/


}