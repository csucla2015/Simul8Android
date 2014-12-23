/*
* Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.animationsdemo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.android.animationsdemo.SplashScreen.MyTask;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Demonstrates a "screen-slide" animation using a {@link ViewPager}. Because {@link ViewPager}
 * automatically plays such an animation when calling {@link ViewPager#setCurrentItem(int)}, there
 * isn't any animation-specific code in this sample.
 *
 * <p>This sample shows a "next" button that advances the user to the next step in a wizard,
 * animating the current screen out (to the left) and the next screen in (from the right). The
 * reverse animation is played when the user presses the "previous" button.</p>
 *
 * @see ScreenSlidePageFragment
 */
public class ScreenSlideActivity extends FragmentActivity implements
ActionBar.OnNavigationListener  {
	MyTask objMyTask;

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 8;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ActionBar actionBar;

	private MenuItem refreshMenuItem;
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		// Enabling Spinner dropdown navigation
		//actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                invalidateOptionsMenu();
            }
        });
    }

	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
    	//super.onCreateOptionsMenu(menu);
       // getMenuInflater().inflate(R.menu.activity_screen_slide, menu);


        // Add either a "next" or "finish" button to the action bar, depending on which page
        // is currently selected.
        /*
        MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
                (mPager.getCurrentItem() == mPagerAdapter.getCount() - 1)
                        ? R.string.action_finish
                        : R.string.action_next);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);*/
        MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.activity_main_actions, menu);
    	return super.onCreateOptionsMenu(menu);
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate "up" the demo structure to the launchpad activity.
                // See http://developer.android.com/design/patterns/navigation.html for more.
                NavUtils.navigateUpTo(this, new Intent(this, ScreenSlideActivity.class));
                return true;

            case R.id.action_previous:
                // Go to the previous step in the wizard. If there is no previous step,
                // setCurrentItem will do nothing.
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                return true;

            case R.id.action_next:
                // Advance to the next step in the wizard. If there is no next step, setCurrentItem
                // will do nothing.
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                return true;
            case R.id.refresh:
        	{
        		MyTask mytask = new MyTask();
        		mytask.execute();
        		
        	}
        }
        SharedPreferences settings1 = getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);

        switch (item.getItemId()) 
        {

    	
        case R.id.powell:
        {	
        	String pos = settings1.getString("Powell Librarynew", "yolo");
    		mPager.setCurrentItem(Integer.parseInt(pos));
    		return true;
        }	
    	case R.id.yrl:
    	{	
    		String pos = settings1.getString("Young Research Librarynew", "yolo");
    		mPager.setCurrentItem(Integer.parseInt(pos));
    		return true;
    	}
    	case R.id.music:
    	{
    		String pos = settings1.getString("Music Librarynew", "yolo");
    		mPager.setCurrentItem(Integer.parseInt(pos));    		
    		return true;
    	}
    	case R.id.management:
    	{
    		String pos = settings1.getString("Management Librarynew", "yolo");
    		mPager.setCurrentItem(Integer.parseInt(pos));    		
    		return true;
    	}
    	case R.id.arts:
    	{
    		String pos = settings1.getString("Arts Librarynew", "yolo");
    		mPager.setCurrentItem(Integer.parseInt(pos));    		
    		return true;
    	}
    	case R.id.law:
    	{
    		String pos = settings1.getString("Law Librarynew", "yolo");
    		mPager.setCurrentItem(Integer.parseInt(pos));    		
    		return true;
    	}
    	case R.id.biomed:
    	{
    		String pos = settings1.getString("Biomedical Librarynew", "yolo");
    		mPager.setCurrentItem(Integer.parseInt(pos));    		
    		return true;
    	}
    	case R.id.sel:
    	{
    		String pos = settings1.getString("Science and Engineering Librarynew", "yolo");
    		mPager.setCurrentItem(Integer.parseInt(pos));    		
    		return true;
    	}
    	case R.id.settings:
    	{
    		Intent i = new Intent(ScreenSlideActivity.this,ListViewDraggingAnimation.class);
			startActivity(i);
			return true;
			//finish();
    	}
    	
    	default:
            return true;
    	}
        
    }

    /**
     * A simple pager adapter that represents 5 {@link ScreenSlidePageFragment} objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.create(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}
	//////////////////////////////////////////////////////////////////
	/////////THIS IS THE REFRESH TASK/////////////////////////////////
	//////////////////////////////////////////////////////////////////
	class MyTask extends AsyncTask<Void, Integer, Void> {

		Dialog dialog;
		ProgressBar progressBar;
		

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new Dialog(ScreenSlideActivity.this);
			dialog.setCancelable(false);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.progressdialog);

			progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar1);

			

			dialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			InputStream is = null;
			JSONObject jObj = null;
			String json = "";
			Log.v("try","try");
			final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;


			Calendar calendar = Calendar.getInstance();
			Date today = calendar.getTime();
			
			

		
			Log.v("meet", today.toString());
			int diffInDays = 0;
    		SharedPreferences settings2 = getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
    		String lastUpDate = settings2.getString("date", "nothingexists");
    		Date lastUpdated = null;
    		   final String[] libraries = {
    	            "Powell Library", "Young Research Library",
    	            "Science and Engineering Library", "Music Library", "Management Library", "Arts Library", "Law Library",
    	            "Biomedical Library"
    	    };
    		if(lastUpDate.equalsIgnoreCase("nothingexists"))
    		{
    			SharedPreferences settings3 = getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
        		SharedPreferences.Editor editor3 = settings3.edit();
        		editor3.putString("date", today.toString());
        		editor3.apply();
        		diffInDays = 8;
        		for(int i = 0 ; i < 8 ; i ++)
        		{
        			SharedPreferences settings31 = getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
            		SharedPreferences.Editor editor31 = settings31.edit();
            		editor31.putString(String.valueOf(i), libraries[i]);
            		editor31.putString( libraries[i]+"new",String.valueOf(i));
            		editor31.apply();
        		}
        		
    		}
    	
			Log.v("meet", "date " + String.valueOf(diffInDays));

				
				SharedPreferences settings3 = getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
        		SharedPreferences.Editor editor3 = settings3.edit();
        		editor3.putString("date", today.toString());
        		editor3.apply();
			 try 
				{
			    	Log.v("here","here");
					HttpClient httpClient = new DefaultHttpClient();
					HttpGet get = new HttpGet("http://webservices.library.ucla.edu/libservices/hours/json");
					get.setHeader("Content-Type", "application/json");
					HttpResponse httpResponse = httpClient.execute(get);
					HttpEntity httpEntity = httpResponse.getEntity();
					is = httpEntity.getContent();
					Log.v("tryworked","tryworked");
					BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
					StringBuilder sb = new StringBuilder();
					String line = null;
					while ((line = reader.readLine()) != null) 
					{
						sb.append(line + "\n");
					}
					is.close();
					json = sb.toString();

				}  
				catch (ClientProtocolException e) 
				{
					e.printStackTrace();
					
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				} 
			    //JSON Parsking
				Log.v("try1","try1");
				int len = json.length();
				String json1 = json.substring(2,len-1);
				int ind = json.indexOf("[");
				int ind1 = json.indexOf("]");
				Log.v("Pos", String.valueOf(ind)+ String.valueOf(ind1));
				json = json1;
				 try {
					 	Log.v("meet","here");
					 	Log.v(String.valueOf(json.length()),"he");
		            	JSONArray hours = new JSONArray(json);
		            	Log.v("meet","Length + " + String.valueOf(hours.length()));
		            	Log.v("com.example.android",String.valueOf(hours.length()));
		            	String FILENAME = "hello_file";
		            	String string = "hello world!";
	            		JSONObject temp = null;

		            	for(int i = 0 ; i < hours.length(); i++)
		            	{
		            		temp = new JSONObject(hours.get(i).toString());
		            		String dailyHours = "Monday - Thursday : " + temp.getString("monThu") + "\n" + "Friday : " + temp.getString("fri") + "\n"+ "Saturday : " +temp.getString("sat") + "\n"  + "Sunday : " +temp.getString("sun");
		            		String libName = temp.getString("library");
		            		Log.v("meet", libName);
		            		Log.v("meet",dailyHours);
		            		SharedPreferences settings = getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
		            		SharedPreferences.Editor editor = settings.edit();
		            		editor.putString(libName, dailyHours);
		            		editor.apply();
		            		SharedPreferences settings1 = getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
		            		String newhours = settings1.getString(libName, null);
		            		Log.v("meet", "newhours"+newhours);


		            	}
		            	FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
		            	fos.write(string.getBytes());
		            	fos.close();
		            	
		            	
		            	
		        } catch (JSONException e) {
		        	Log.v(json,json);
		        	Log.e("JSON Parser", "Error parsing data " + e.toString());
		        } catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			 
				//////////////////////////////////////////////
				///////GET LAPTOPS////////////////////////////
				//////////////////////////////////////////////
				 try 
					{
				    	Log.v("here","here");
						HttpClient httpClient = new DefaultHttpClient();
						HttpGet get = new HttpGet("http://webservices.library.ucla.edu/laptops/available/");
						get.setHeader("Content-Type", "application/json");
						HttpResponse httpResponse = httpClient.execute(get);
						HttpEntity httpEntity = httpResponse.getEntity();
						is = httpEntity.getContent();
						Log.v("tryworked","tryworked");
						BufferedReader reader = new BufferedReader(new InputStreamReader(
						is, "iso-8859-1"), 8);
						StringBuilder sb = new StringBuilder();
						String line = null;
						while ((line = reader.readLine()) != null) 
						{
							sb.append(line + "\n");
						}
						is.close();
						json = sb.toString();

					}  
					catch (ClientProtocolException e) 
					{
						e.printStackTrace();
						
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					} 
				 	Log.v("meet", "laptops" + json);
				 	
				 	
				 	try 
				 	{
				 		JSONObject laptops = new JSONObject(json);
		            	JSONArray laptopsAvail = laptops.getJSONArray("laptops");
		            	for(int i = 0 ; i < laptopsAvail.length();i++)
		            	{	
		            		JSONObject temp = new JSONObject(laptopsAvail.get(i).toString());
		            		String libName = temp.getString("publicName");
		            		String count = temp.getString("availableCount");
		            		SharedPreferences settings = getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
		            		SharedPreferences.Editor editor = settings.edit();
		            		editor.putString(libName+ " Laptops", count);
		            		editor.apply();
		            		SharedPreferences settings1 = getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
		            		String newhours = settings1.getString(libName+ " Laptops", null);
		            		Log.v("meet", "newhours"+libName);
		            		DateFormat dateFormat = new SimpleDateFormat("E yyyy.MM.dd ',' hh:mm:ss a zzz");
		                	Date date = new Date();
		                	String datestring = dateFormat.format(date);
		            		editor.putString("asof", datestring);
		            		Log.v("meet", "newhours"+newhours);

		            	}	
				 	}
		            	
		            catch (JSONException e) 
		            {
			        	Log.v(json,json);
			        	Log.e("JSON Parser", "Error parsing data " + e.toString());
		            }
			return null;
		}


		

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			dialog.dismiss();
			Intent i = new Intent(ScreenSlideActivity.this, ScreenSlideActivity.class);
			startActivity(i);
			finish();
			//AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
					//.create();

			//alert.setTitle("Completed!!!");
			//alert.setMessage("Your Task is Completed SuccessFully!!!");
			//alert.setButton("Dismiss", new DialogInterface.OnClickListener() {

				
			
		}
	}
}
