package com.liuxuan2096.binderdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



public class MessengerService extends Service {
    public static final String TAG = "MessengerService";
    public static final int MSG_FROMCLIENT = 1000;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case MSG_FROMCLIENT:
                    Log.i(TAG, "收到客户端信息---------" + msg.getData().get("msg"));
                    // 得到客户端传过来的Messenger对象
                    Messenger mMessenger = msg.replyTo;
                    Message mMessage = Message.obtain(null, MessengerService.MSG_FROMCLIENT);
                    Bundle mBundle = new Bundle();
                    mBundle.putString("rep", msg.getData().get("msg").toString());
                    mMessage.setData(mBundle);
                    try {
                        mMessenger.send(mMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"MessengerService::onBind()");
        return new Messenger(mHandler).getBinder();
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "MessengerService::onCreate()");
    }
}
