package com.example.alibabapush

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LandingTargetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_target)

        intent?.let {
            findViewById<TextView>(R.id.uri).text = intent.getStringExtra("uri")
            findViewById<TextView>(R.id.msg).text = intent.getStringExtra("mp_push_msg")
        }
    }
}