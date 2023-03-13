package com.example.alibabapush

import com.alipay.mobile.common.rpc.RpcException
import com.alipay.pushsdk.content.MPPushMsgServiceAdapter
import com.alipay.pushsdk.data.MPPushMsg
import com.alipay.pushsdk.data.PushOsType
import com.alipay.pushsdk.rpc.net.ResultBean
import com.mpaas.mps.adapter.api.MPPush

class MyPushMsgService: MPPushMsgServiceAdapter() {
    lateinit var token: String
    lateinit var channelToken: String
    lateinit var channel: PushOsType


    override fun onTokenReceive(token: String?) {
        this.token = token!!
        PLog().d("Token:: $token")

        try {
            val bindResult = MPPush.bind(applicationContext, MainActivity.userId, token)
            PLog().d("userId:: ${MainActivity.userId}")
            bindResult.let {
                if(it.success) PLog().d("Success!!!!!!") else PLog().d("Error:: ${it.code} Message:: ${it.message}")
            }
        }catch (e: RpcException){
            PLog().d("MPPush.bind Error:: ${e.message}")
            PLog().d("MPPush.bind Error:: ${e.cause}")
            PLog().d("MPPush.bind Error:: ${e.localizedMessage}")
            PLog().d("MPPush.bind Error:: ${e.stackTrace}")
        }
    }

    override fun onChannelTokenReceive(channelToken: String?, channel: PushOsType?) {
        this.channelToken = channelToken.toString()
        this.channel = channel!!
        PLog().d("channelToken:: ${this.channelToken}")
        PLog().d("channel:: ${this.channel}")
    }

    override fun onChannelTokenReport(result: ResultBean?) {
        result?.let {
            if(result.success){
                PLog().d("onChannelTokenReport Result:: $result")
            }else{
                PLog().d("onChannelTokenReport Error:: ${it.code}")
            }
        }
    }

    override fun onMessageReceive(msg: MPPushMsg?): Boolean {
        PLog().d("onMessageReceive:: ${msg.toString()}")
        return false
    }

    override fun onChannelMessageClick(msg: MPPushMsg?): Boolean {
        PLog().d("onChannelMessageClick:: ${msg.toString()}")
        return false
    }
}