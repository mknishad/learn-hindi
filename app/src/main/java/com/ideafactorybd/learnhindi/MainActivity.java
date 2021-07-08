package com.ideafactorybd.learnhindi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Find the toolbar and initialize it
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    toolbar.setTitle(R.string.app_name);

    // Find the view pager that will allow the user to swipe between fragments
    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

    // Create an adapter that knows which fragment should be shown on each page
    CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());

    // Set the adapter onto the view pager
    viewPager.setAdapter(adapter);

    // Give the TabLayout the ViewPager
    TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
    tabLayout.setupWithViewPager(viewPager);
  }
}
