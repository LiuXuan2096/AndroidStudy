package com.maniu.phone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

import com.maniu.aidlphone.IMyService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {
        Intent serviceIntent;
        serviceIntent=new Intent();
        serviceIntent.setComponent(new ComponentName("com.maniu.aidlphone", "com.maniu.aidlphone.ManiuService"));
        bindService(serviceIntent, new ManiuServiceConnection(), Context.BIND_AUTO_CREATE);
    }
    class ManiuServiceConnection implements ServiceConnection {

//xitong
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//            服务端的引用 客户端发送信息
            IMyService iRequest =IMyService.Stub.asInterface(iBinder);
            try {
                Toast.makeText(MainActivity.this, "  " + iRequest.request("data"), Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}
