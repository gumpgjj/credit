package com.fastcnt.fpad;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.fastcnt.fpad.ui.ProgressWheel;

import java.util.Random;

public class FullActivity extends ActionBarActivity implements View.OnClickListener,ItemFragment.OnFragmentInteractionListener {
    boolean wheelRunning;
    int wheelProgress = 0;
    private ProgressWheel pwTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_activiy);
        Button start = (Button) findViewById(R.id.startService);
        start.setOnClickListener(this);
        findViewById(R.id.stopService).setOnClickListener(this);
        findViewById(R.id.bind_service).setOnClickListener(this);
        findViewById(R.id.unbind_service).setOnClickListener(this);
        Log.d("MyService", "MainActivity thread id is " + Thread.currentThread().getId());
        Button startBtn = (Button) findViewById(R.id.start_load);
        pwTwo = (ProgressWheel) findViewById(R.id.progress_bar_two);
        new Thread(r).start();
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*if (!wheelRunning) {*/
                    wheelProgress = 0;
                    pwTwo.resetCount();
                    new Thread(r).start();
                /*}*/
            }
        });
    }
    final Runnable r = new Runnable() {
        public void run() {
            wheelRunning = true;
            while (wheelProgress < 361) {
                Random random = new Random();
                int temp = random.nextInt(10);
                if(wheelProgress+temp>=360){
                    pwTwo.incrementProgress(360-wheelProgress);
                    wheelProgress=361;
                }else{
                    pwTwo.incrementProgress(temp);
                }
                wheelProgress+=temp;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            wheelRunning = false;
        }
    };

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder myBinder = (MyService.MyBinder) service;
            myBinder.startDownload();
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startService:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stopService:
                Log.d("MyService", "click Stop Service button");
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                Log.d("MyService", "click Unbind Service button");
                unbindService(connection);
                break;
            default:
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.full_activiy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String id) {
        Log.e("","=============="+id);
    }
}
