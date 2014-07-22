package com.fastcnt.fpad;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;

import com.fastcnt.fpad.fragment.Fragment1;
import com.fastcnt.fpad.fragment.Fragment2;
//http://www.anyidai.com/index_product03.html

public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
       /* Display display = getWindowManager().getDefaultDisplay();
        if (display.getWidth() > display.getHeight()) {
            Fragment1 fragment1 = new Fragment1();
            getFragmentManager().beginTransaction().replace(R.id.main_layout, fragment1).commit();
        } else {
            Fragment2 fragment2 = new Fragment2();
            getFragmentManager().beginTransaction().replace(R.id.main_layout, fragment2).commit();
        }*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
}
