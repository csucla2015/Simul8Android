/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.animationsdemo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This application creates a listview where the ordering of the data set
 * can be modified in response to user touch events.
 *
 * An item in the listview is selected via a long press event and is then
 * moved around by tracking and following the movement of the user's finger.
 * When the item is released, it animates to its new position within the listview.
 */
public class ListViewDraggingAnimation extends Activity implements
ActionBar.OnNavigationListener {
	public static Context contextOfApplication;


	 private ActionBar actionBar;
	 DynamicListView listView;
	private MenuItem refreshMenuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	contextOfApplication = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        actionBar = getActionBar();
  		actionBar.setDisplayShowTitleEnabled(true);
  		// Enabling Spinner dropdown navigation
  		//actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        ArrayList<String>mCheeseList = new ArrayList<String>();
        for (int i = 0; i < Libraries.sCheeseStrings.length; ++i) {
        	SharedPreferences settings1 = getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
            mCheeseList.add(settings1.getString(String.valueOf(i),"yolo"));
        }

        StableArrayAdapter adapter = new StableArrayAdapter(this, R.layout.text_view, mCheeseList);
         listView = (DynamicListView) findViewById(R.id.listview);

        listView.setCheeseList(mCheeseList);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        
    }
    public static Context getContextOfApplication(){
        return contextOfApplication;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
    	
        MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.activity_settings_actions, menu);
    	return super.onCreateOptionsMenu(menu);
        //return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         
        switch (item.getItemId()) {
    	case R.id.home:
    	{
            TextView tv = (TextView) listView.getChildAt(0);
            Log.v("settingspage","Text from textview" + tv.getText());
            int k = listView.getCount();
            Log.v("settingspage","Number of views"+String.valueOf(k));
            String[] libraries = new String[k];
            for(int i = 0 ; i< k ; i++)
            {
                tv = (TextView) listView.getChildAt(i);
            	libraries[i] = (String) tv.getText();
            	Log.v("settingspage","valueoflib"+libraries[i]);
            	SharedPreferences settings3 = getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
        		SharedPreferences.Editor editor3 = settings3.edit();
        		editor3.putString(String.valueOf(i), libraries[i]);
        		editor3.putString(libraries[i]+"new", String.valueOf(i));

        		editor3.apply();
            }
    		Intent i = new Intent(ListViewDraggingAnimation.this,ScreenSlideActivity.class);
			startActivity(i);
			finish();
    	}
    	case R.id.refresh:
    	{
    		Intent i = new Intent(ListViewDraggingAnimation.this,ScreenSlideActivity.class);
			startActivity(i);
			finish();
    	}
    	
    	
    	default:
    		return true;
    	}
        
    }
    @Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) 
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
        	 TextView tv = (TextView) listView.getChildAt(0);
             Log.v("settingspage","Text from textview" + tv.getText());
             int k = listView.getCount();
             Log.v("settingspage","Number of views"+String.valueOf(k));
             String[] libraries = new String[k];
             for(int i = 0 ; i< k ; i++)
             {
                 tv = (TextView) listView.getChildAt(i);
             	libraries[i] = (String) tv.getText();
             	Log.v("settingspage","valueoflib"+libraries[i]);
             	SharedPreferences settings3 = getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
         		SharedPreferences.Editor editor3 = settings3.edit();
         		editor3.putString(String.valueOf(i), libraries[i]);
         		editor3.putString(libraries[i]+"new", String.valueOf(i));

         		editor3.apply();
             }
     		Intent i = new Intent(ListViewDraggingAnimation.this,ScreenSlideActivity.class);
 			startActivity(i);
 			finish();
        }
        return false;
    }
}
