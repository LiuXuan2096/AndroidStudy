package com.maniu.aidlphone;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

public class ManiuService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IMyService.Stub() {
            @Override
            public String request(String data) throws RemoteException {
                return "码牛";
            }
        };
    }
}
