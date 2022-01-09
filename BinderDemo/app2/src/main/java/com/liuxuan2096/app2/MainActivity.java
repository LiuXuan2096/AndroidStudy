package com.liuxuan2096.app2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.liuxuan2096.binderdemo.IMyService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Messenger mMessenger;
    private EditText binderEditText;
    private EditText messengerEditText;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1000:
                    Log.i(TAG, "收到服务端消息: " + msg.getData().get("rep").toString());
                    Toast.makeText(MainActivity.this, msg.getData().get("rep").toString(),
                            Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binderEditText = (EditText) findViewById(R.id.binder_editText);
        messengerEditText = (EditText) findViewById(R.id.messenter_editText);
    }

    public void start(View view) {
        Intent serviceIntent;
        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("com.liuxuan2096.binderdemo",
                "com.liuxuan2096.binderdemo.DemoService"));
        bindService(serviceIntent, new DemoServiceConnection(), Context.BIND_AUTO_CREATE);
    }

    public void startMessengerService(View view) {
        Log.i(TAG, "开始绑定Messenger");
        Intent serviceIntent;
        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("com.liuxuan2096.binderdemo",
                "com.liuxuan2096.binderdemo.MessengerService"));
        bindService(serviceIntent, new MessengerServiceConnection(), Context.BIND_AUTO_CREATE);
    }

    class DemoServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IMyService IRequest = IMyService.Stub.asInterface(iBinder);
            try {
                Toast.makeText(MainActivity.this, " " + IRequest.request(binderEditText.getText().toString()),
                        Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }

    class MessengerServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            Log.i(TAG, "Messenger绑定成功");
            mMessenger = new Messenger(service);
            Message mMessage = Message.obtain(null, 1000);
            Bundle mBundle = new Bundle();
            mBundle.putString("msg", messengerEditText.getText().toString());
            mMessage.setData(mBundle);
            // 将Messenger传递给服务端
            mMessage.replyTo = new Messenger(mHandler);
            try {
                mMessenger.send(mMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }

}