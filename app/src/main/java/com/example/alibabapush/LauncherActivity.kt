package com.example.alibabapush

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        findViewById<Button>(R.id.demo)
            .setOnClickListener {
                Intent(this, MainActivity::class.java)
                    .apply {
                        startActivity(this)
                    }
            }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent!!)
        setIntent(null)
    }

    private fun handleIntent(intent: Intent?){
        intent?.let {
            val uri = it.data ?: return
            Intent(this, LandingTargetActivity::class.java)
                .apply {
                    putExtra("uri", uri.toString())
                    putExtra("mp_push_msg", intent.getStringExtra("mp_push_msg"))
                    startActivity(this)
                }
        }
    }
}