package com.yelinaung.tabinab.app;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends ActionBarActivity {

  private MainPageAdapter mAdapter;
  private ViewPager mPager;
  private PagerSlidingTabStrip mTabs;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final ActionBar mActionBar = getSupportActionBar();
    mActionBar.setTitle("");
    mActionBar.setDisplayShowHomeEnabled(false);
    mActionBar.setDisplayShowTitleEnabled(false);

    mAdapter = new MainPageAdapter(getSupportFragmentManager());
    mPager = (ViewPager) findViewById(R.id.pagerContent);
    mPager.setAdapter(mAdapter);
    mPager.setOffscreenPageLimit(3);
    LayoutInflater inflator =
        (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    //inflate the pagerslidingtab strip from a xml layout
    //Put the pagerslidingtabstrip to Linearlayout
    View v = inflator.inflate(R.layout.tabs, null);
    assert v != null;
    v.setLayoutParams(new ViewGroup.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.MATCH_PARENT));
    mTabs =
        (PagerSlidingTabStrip) v.findViewById(R.id.tabs); //find the pagerslidingtabstrip from view
    mTabs.setViewPager(mPager); //set the viewpager to view

    mTabs.setIndicatorColor(Color.WHITE);
    mTabs.setMinimumWidth(10);

    mActionBar.setDisplayShowCustomEnabled(
        true); //don't forget this so we can set custom view to actionbar
    mActionBar.setCustomView(v); //put the view to actionbar
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
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

  public class MainPageAdapter extends FragmentPagerAdapter
      implements PagerSlidingTabStrip.IconTabProvider {

    private final String[] TITLES = {
        "A", "B", "C"
    };

    public MainPageAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return TITLES[position];
    }

    @Override
    public int getCount() {
      return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
      return PlaceholderFragment.newInstance(position);
    }

    @Override public int getPageIconResId(int i) {
      if (i == 0) {
        return R.drawable.ic_action_call;
      } else if (i == 1) {
        return R.drawable.ic_action_delete;
      } else {
        return R.drawable.ic_action_user;
      }
    }
  }
}

