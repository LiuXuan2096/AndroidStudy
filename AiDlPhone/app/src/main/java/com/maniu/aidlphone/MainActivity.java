package com.maniu.aidlphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    class ManiuServiceConnection implements ServiceConnection {

        //xitong  binder  反射   binder
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