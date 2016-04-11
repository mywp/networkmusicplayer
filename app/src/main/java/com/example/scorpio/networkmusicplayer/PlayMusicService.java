package com.example.scorpio.networkmusicplayer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PlayMusicService extends Service {
    public PlayMusicService() {
    }

    //绑定服务 调用服务的方法
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
