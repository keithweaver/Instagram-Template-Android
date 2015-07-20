package com.weaverprojects.insta;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;


public class InstaTestMainActivity extends Activity {
    public static String TAG = "WEAVER_MainActivity_";

    Context mContext;
    Bitmap bitmap;
    ImageView img;

    ImageAdapter mainAdapter;
    public static ArrayList<UserPost> mainList;
    ListView mainListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this.getApplicationContext();

        Log.v(TAG, "Size of main list:" + mainList.size());


        mainListview = (ListView) findViewById(R.id.mainListview);
        mainAdapter = new ImageAdapter(this, R.layout.single_img,  mainList);
        mainListview.setAdapter(mainAdapter);
        //img = (ImageView)findViewById(R.id.img);
        Log.v(TAG, "MainAdapter Size:" + mainAdapter.getCount());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
