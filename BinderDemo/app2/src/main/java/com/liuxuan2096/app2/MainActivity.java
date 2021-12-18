package com.liuxuan2096.app2;

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

import com.liuxuan2096.binderdemo.IMyService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {
        Intent serviceIntent;
        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("com.liuxuan2096.binderdemo",
                "com.liuxuan2096.binderdemo.DemoService"));
        bindService(serviceIntent, new DemoServiceConnection(), Context.BIND_AUTO_CREATE);
    }

    class DemoServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IMyService IRequest = IMyService.Stub.asInterface(iBinder);
            try {
                Toast.makeText(MainActivity.this, " " + IRequest.request("data"),
                        Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}