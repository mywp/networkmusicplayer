package com.example.scorpio.networkmusicplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText et_path;

    private MediaPlayer mediaPlayer;

    private Button bt_play,bt_pause,bt_stop,bt_replay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_path = (EditText) findViewById(R.id.et_path);
        bt_play = (Button) findViewById(R.id.bt_play);
        bt_pause = (Button) findViewById(R.id.bt_pause);
        bt_stop = (Button) findViewById(R.id.bt_stop);
        bt_replay = (Button) findViewById(R.id.bt_replay);
    }

    /*播放*/
    public void play(View view) {
        String filepath = et_path.getText().toString().trim();
        //http://
        if (filepath.startsWith("http://")) {
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(filepath);//设置播放的数据源。
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                //mediaPlayer.prepare();//同步的准备方法
                mediaPlayer.prepareAsync();//异步的准备
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                        bt_play.setEnabled(false);
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        bt_play.setEnabled(true);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "播放失败", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this,"请检查文件的路径",Toast.LENGTH_SHORT).show();
        }
    }

    /*暂停*/
    public void pause(View view) {
        if ("继续".equals(bt_pause.getText().toString())) {
            mediaPlayer.start();
            bt_pause.setText("暂停");
            return;
        }
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            bt_pause.setText("继续");
        }
    }

    /*停止*/
    public void stop(View view) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        bt_pause.setText("暂停");
        bt_play.setEnabled(true);
    }

    /*重播*/
    public void replay(View view) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
        }else {
            play(view);
        }
        bt_pause.setText("暂停");
    }
}
