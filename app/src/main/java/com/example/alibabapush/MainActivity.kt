package com.example.alibabapush

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.View.OnLongClickListener
import android.widget.TextView
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

import com.alipay.mobile.common.transport.config.DtnConfigItem.KEY_LOG

import com.alipay.mobile.common.transport.config.DtnConfigItem
import com.mpaas.mps.adapter.api.MPPush


class MainActivity : AppCompatActivity() {
    companion object{
        const val ACTION_P_LOG = "com.example.alibabapush"
        const val KEY_LOG = "key_log"
        const val userId = "mpaas_push_demo"
    }

    lateinit var mTvLog: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        registerReceiverToPrintPushLog()

        MPPush.init(this)

    }

    private fun initView() {
        mTvLog = findViewById(R.id.tv_log)
        mTvLog.setOnLongClickListener(OnLongClickListener {
            val log = mTvLog.text.toString()
            if (!TextUtils.isEmpty(log)) {
                val clipboard: ClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText(null, log)
                clipboard.setPrimaryClip(clipData)
                Toast.makeText(this@MainActivity, "日志已复制", Toast.LENGTH_SHORT).show()
                return@OnLongClickListener true
            }
            false
        })
    }

    private fun registerReceiverToPrintPushLog() {
        LocalBroadcastManager.getInstance(this).registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                if (!ACTION_P_LOG.equals(intent.action)) {
                    return
                }
                val log = intent.getStringExtra(DtnConfigItem.KEY_LOG)
                var text: String? = mTvLog.text.toString()
                text = if (TextUtils.isEmpty(text)) {
                    log
                } else {
                    """
    $text
     $log
     """.trimIndent()
                }
                mTvLog.text = text
            }
        }, IntentFilter(ACTION_P_LOG))
    }

}