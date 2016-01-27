package com.nikhilsahukar.android.imageviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.nikhilsahukar.android.imageviewer.Object.Image;
import com.nikhilsahukar.android.imageviewer.fragment.ImageFragment;
import com.nikhilsahukar.android.imageviewer.utils.ImageUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageAdapter mImageAdapter;
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mImageAdapter = new ImageAdapter(getSupportFragmentManager());
        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mImageAdapter);
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



    public class ImageAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Image> mImages;

        public ImageAdapter(FragmentManager fm) {
            super(fm);
            mImages = ImageUtils.getDeviceImages(MainActivity.this);
        }

        @Override
        public Fragment getItem(int position) {
            return ImageFragment.newInstance(mImages.get(position));
        }

        @Override
        public int getCount() {
            return mImages.size();
        }
    }

}
