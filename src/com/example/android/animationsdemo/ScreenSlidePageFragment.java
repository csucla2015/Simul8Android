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

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.npi.blureffect.MySimpleArrayAdapter;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsListView.OnScrollListener;

public class ScreenSlidePageFragment extends Fragment {

	private ListView mList;
	private ImageView mBlurredImage;
	private ImageView mNormalImage;
	private float alpha;
	private View headerView;
	private static int TOP_HEIGHT = 800;
	private static final String tg = "nitin";
	View rootView;
    public static final String ARG_PAGE = "page";
    public static String[] headers;
    public static String[] strings;
    int width ;
    int height;
    private int mPageNumber;

    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.v("meet1","we are in fragments");
        super.onCreate(savedInstanceState);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        Log.v("meet2", "height is "+String.valueOf(height));
	    TOP_HEIGHT = height-height/3*2;
	        
        mPageNumber = getArguments().getInt(ARG_PAGE);
        System.out.println(mPageNumber);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
    	rootView = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);

        mList = (ListView) rootView.findViewById(R.id.list);
		//mNormalImage = (ImageView) rootView.findViewById(R.id.normal_imagem);
		System.out.println("here");
		headerView = new View(getActivity());	
		//headerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, TOP_HEIGHT));
	    strings = getResources().getStringArray(R.array.list_content);
		strings[0]= "Hours : /n Monday : 7:30 am - 11:00 pm \n Tuesday : 7:30 am - 11:00 pm \n Wednesday : 7:30 am - 11:00 pm \n Thursday 7:30 am - 11:00 pm \n Friday 7:30 am - 11:00 pm";
		headers = new String[10];
		headers[2] = "Hours";
		headers[4] = "Laptop Availability";
		headers[6] = "Contact";
		headers[8] = "Directions";
		strings[0] = "Open Now";
		strings[8] = "http://maps.google.com/?q=Powell+Library";


		headers[1] = "";
		headers[3] = "";
		headers[5] = "";
		headers[7] = "";
		headers[9] = "";
		strings[1] = "";
		strings[3]= "";
		strings[5] = "";
		strings[7] = "";
		strings[9] = "";
	    SharedPreferences settings1 = getActivity().getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);

		setLibraryInfo(settings1.getString(String.valueOf(mPageNumber), "yolo"));
		// Get the screen width
		//final int screenWidth = ImageUtils.getScreenWidth(getActivity());

		// Try to find the blurred image
	
		MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(getActivity(), strings, headers);
		mList.addHeaderView(headerView);
		mList.setAdapter(adapter); 
		mList.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

				
			}
		});	
        return rootView;
    }
   

    public int getPageNumber() {
        return mPageNumber;
    }
    public void setLibraryInfo(String name)
    {
    	DateFormat dateFormat = new SimpleDateFormat("hh a");
    	Date date = new Date();
    	 String datestring = dateFormat.format(date);
     	Log.v("meet1", "date is"+datestring);

    	 String backdatestring = datestring;

    	 int index = datestring.indexOf(' ');
    	 datestring = datestring.substring(0,index);
    
    	 String ampm = backdatestring.substring(index+1, backdatestring.length());
     	DateFormat dateFormat1 = new SimpleDateFormat("E");
     	Date date1 = new Date();
     	 String day = dateFormat1.format(date1);
     	 Log.v("meet1", "day" + day);
    	Log.v("meet1", "date is"+datestring);
    	Log.v("meet1", "am or pm is"+ampm+ " " + String.valueOf(ampm.length()));
    	if(name.equalsIgnoreCase("Powell Library"))
		{	

			strings[6] = "(310) 825 1938";
			TOP_HEIGHT = height-height/100*55 + 115;
			headerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, TOP_HEIGHT));

			SharedPreferences settings1 = getActivity().getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
    		String newhours = settings1.getString("College Library", null);
			strings[2]= newhours;
			/*
			if(isClosed(newhours, ampm, datestring, day))
			{
				strings[0] = "Closed";
				Log.v("meet1","in here");
			}
			*/
			 
			rootView.setBackgroundResource(R.drawable.powell);
			headers[0] =  getString(R.string.powell);
			String laptops = settings1.getString("Powell Library Laptops",null);
			strings[4] = laptops ;
			strings[8] = "http://maps.google.com/?q=Powell+Library";
			headers[0] = settings1.getString(String.valueOf(mPageNumber), "yolo");


		}
		else if(name.equalsIgnoreCase("Young Research Library"))
		{	
    	    TOP_HEIGHT = height-height/5*3 + 115;
			headerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, TOP_HEIGHT));

			strings[6] = "(310) 825-4732";
			SharedPreferences settings1 = getActivity().getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
    		String newhours = settings1.getString("Research Library", null);
			strings[2]= newhours;
			rootView.setBackgroundResource(R.drawable.yrlnew);
			headers[0] =  getString(R.string.yrl);
			String laptops = settings1.getString("Research Library Laptops",null);
			strings[4] = laptops ;
			strings[8] = "http://maps.google.com/?q=UCLA Charles E. Young Research Library";
			headers[0] = settings1.getString(String.valueOf(mPageNumber), "yolo");
			if(isClosed(newhours, ampm, datestring, day))
			{
				strings[0] = "Closed";
				Log.v("meet1","in here");
			}


		}
		else if(name.equalsIgnoreCase("Management Library"))
		{	
    	    TOP_HEIGHT = height-height/5*3 + 115;
			headerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, TOP_HEIGHT));

			strings[8] = "http://maps.google.com/?q=Rosenfeld Library";

			strings[6] = "(310) 825-3138";
			strings[4] = "Laptops Not Available" ;

			SharedPreferences settings1 = getActivity().getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
    		String newhours = settings1.getString("Management Library", null);
			strings[2]= newhours;
			rootView.setBackgroundResource(R.drawable.mgmt);
			//headers[0] =  getString(R.string.mgmt);
			headers[0] = settings1.getString(String.valueOf(mPageNumber), "yolo");
			if(isClosed(newhours, ampm, datestring, day))
			{
				strings[0] = "Closed";
				Log.v("meet1","in here");
			}

		}
		else if(name.equalsIgnoreCase("Science and Engineering Library"))
		{	
			TOP_HEIGHT = height-height/10*7 + 115;
			headerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, TOP_HEIGHT));

			strings[6] = "(310) 825-4951";

			
			SharedPreferences settings1 = getActivity().getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
    		String newhours = settings1.getString("SEL Eng & Math Sci", null);
			strings[2]= newhours;
			rootView.setBackgroundResource(R.drawable.seml);
			headers[0] =  getString(R.string.seml);
			String laptops = settings1.getString("SEL / Boelter Laptops",null);
			strings[4] = laptops ;
			strings[8] = "http://maps.google.com/?q=Science and Engineering Library/ Engineering and Mathematical Sciences";
			headers[0] = settings1.getString(String.valueOf(mPageNumber), "yolo");
			Log.v("meet1", newhours + " " + ampm + " " + datestring + " " + day);
			if(isClosed(newhours, ampm, datestring, day))
			{
				strings[0] = "Closed";
				Log.v("meet1","in here");
			}


		}	
		else if(name.equalsIgnoreCase("Music Library"))
		{	
			TOP_HEIGHT = height-height/100*55 + 115;
			headerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, TOP_HEIGHT));

			strings[6] = "(310) 825-4882";

			strings[8] = "http://maps.google.com/?q=Shoenberg Music Bldg";

			SharedPreferences settings1 = getActivity().getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
    		String newhours = settings1.getString("Music Library", null);
    		String laptops = settings1.getString("Music Library Laptops",null);
			strings[4] = laptops ;

			strings[2]= newhours;
			rootView.setBackgroundResource(R.drawable.music);
			headers[0] =  getString(R.string.music);
			headers[0] = settings1.getString(String.valueOf(mPageNumber), "yolo");
			if(isClosed(newhours, ampm, datestring, day))
			{
				strings[0] = "Closed";
				Log.v("meet1","in here");
			}


		}
		else if(name.equalsIgnoreCase("Arts Library"))
		{	
			TOP_HEIGHT = height-height/100*55 + 115;
			headerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, TOP_HEIGHT));

			strings[6] = "(310) 206-5425";
			strings[8] = "http://maps.google.com/?q=Arts Library";

			SharedPreferences settings1 = getActivity().getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
    		String newhours = settings1.getString("Arts Library", null);
			strings[2]= newhours;
			rootView.setBackgroundResource(R.drawable.arts);
			headers[0] =  getString(R.string.arts);
			String laptops = settings1.getString("Arts Library Laptops",null);
			strings[4] = laptops ;
			headers[0] = settings1.getString(String.valueOf(mPageNumber), "yolo");
			if(isClosed(newhours, ampm, datestring, day))
			{
				strings[0] = "Closed";
				Log.v("meet1","in here");
			}


		}
		else if(name.equalsIgnoreCase("Law Library"))
		{	
			TOP_HEIGHT = height-height/100*55  + 115;
			headerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, TOP_HEIGHT));

			strings[6] = "(310) 825-6414";
			strings[8] = "http://maps.google.com/?q=Hugh and Hazel Darling Law Library";

			
			SharedPreferences settings1 = getActivity().getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
    		String newhours = settings1.getString("Arts Library", null);
			strings[2]= newhours;
			rootView.setBackgroundResource(R.drawable.law);
			headers[0] =  getString(R.string.law);
			strings[4] = "Laptops Not Available" ;
			headers[0] = settings1.getString(String.valueOf(mPageNumber), "yolo");
			if(isClosed(newhours, ampm, datestring, day))
			{
				strings[0] = "Closed";
				Log.v("meet1","in here");
			}


		}
		else if(name.equalsIgnoreCase("Biomedical Library"))
		{	
    	    TOP_HEIGHT = height-height/5*3 + 115;
			headerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, TOP_HEIGHT));

			strings[6] = "(310) 825-4904";
			strings[8] = "http://maps.google.com/?q=UCLA Louise M. Darling Biomedical Library";

			
			SharedPreferences settings1 = getActivity().getApplicationContext().getSharedPreferences("com.example.android.animationsdemo", 0);
    		String newhours = settings1.getString("Arts Library", null);
			strings[2]= newhours;
			rootView.setBackgroundResource(R.drawable.biomed);
			headers[0] =  getString(R.string.biomed);
			strings[4] = "Laptops Not Available" ;
			headers[0] = settings1.getString(String.valueOf(mPageNumber), "yolo");
			if(isClosed(newhours, ampm, datestring, day))
			{
				strings[0] = "Closed";
				Log.v("meet1","in here");
			}


		}
    }
    boolean isClosed(String hours, String ampm, String datestring, String day)
    {
    	String newhours = "";
    	if(day.equalsIgnoreCase("Mon") || day.equalsIgnoreCase("Tue") || day.equalsIgnoreCase("Wed") || day.equalsIgnoreCase("Thur"))
    	{	
	    	int index = 0;
	    	if(ampm.equalsIgnoreCase("PM"))
	    		index = hours.indexOf("PM");
	    	else
	    		index = hours.indexOf("AM");
	    	Log.v("meet1", "index is "+ String.valueOf(index));
		    newhours = hours.substring(index-6,index);
			Log.v("meet1","new hrs is " + newhours);
			index = newhours.indexOf(':');
			newhours = newhours.substring(index-2,index);
			newhours = newhours.trim();
			Log.v("meet1","d1ate is "+newhours+" "+String.valueOf(newhours.length()));
    	}
    	if(day.equalsIgnoreCase("Fri"))
    	{
    		int tempind = hours.indexOf("Friday");
    		hours = hours.substring(tempind);
    				
    		int index = 0;
	    	if(ampm.equalsIgnoreCase("PM"))
	    		index = hours.indexOf("PM");
	    	else
	    		index = hours.indexOf("AM");
	    	Log.v("meet1", "index is "+ String.valueOf(index));
			newhours = hours.substring(index-6,index);
			Log.v("meet1","new hrs is " + newhours);
			index = newhours.indexOf(':');
			newhours = newhours.substring(index-2,index);
			newhours = newhours.trim();
			Log.v("meet1","d1ate is "+newhours+" "+String.valueOf(newhours.length()));

    		
    	}	
    	if(day.equalsIgnoreCase("Sat"))
    	{
    		int tempind = hours.indexOf("Saturday");
    		hours = hours.substring(tempind);
    				
    		int index = 0;
	    	if(ampm.equalsIgnoreCase("PM"))
	    		index = hours.indexOf("PM");
	    	else
	    		index = hours.indexOf("AM");
	    	Log.v("meet1", "index is "+ String.valueOf(index));
			newhours = hours.substring(index-6,index);
			Log.v("meet1","new hrs is " + newhours);
			index = newhours.indexOf(':');
			newhours = newhours.substring(index-2,index);
			newhours = newhours.trim();
			Log.v("meet1","d1ate is "+newhours+" "+String.valueOf(newhours.length()));
    	}
    	if(day.equalsIgnoreCase("Sun"))
    	{
    		int tempind = hours.indexOf("Sunday");
    		hours = hours.substring(tempind);
    				
    		int index = 0;
	    	if(ampm.equalsIgnoreCase("PM"))
	    		index = hours.indexOf("PM");
	    	else
	    		index = hours.indexOf("AM");
	    	Log.v("meet1", "index is "+ String.valueOf(index));
			newhours = hours.substring(index-6,index);
			Log.v("meet1","new hrs is " + newhours);
			index = newhours.indexOf(':');
			newhours = newhours.substring(index-2,index);
			newhours = newhours.trim();
			Log.v("meet1","d1ate is "+newhours+" "+String.valueOf(newhours.length()));
    	}
		int close = Integer.parseInt(newhours);
		int current = Integer.parseInt(datestring);
		Log.v("meet1","close and cur"+ " " + String.valueOf(close)+ " " + String.valueOf(current));
		if(current>=close && ampm.equalsIgnoreCase("PM"))
		{
			strings[0] = "Closed";
			Log.v("meet1","in here1");
			return true;
		}
		if(current<=close && ampm.equalsIgnoreCase("AM"))
		{
			strings[0] = "Closed";
			Log.v("meet1","in here1");
			return true;
		}
    	return false;
    }
    
    
}
