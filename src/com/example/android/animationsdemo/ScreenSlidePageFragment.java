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

import com.npi.blureffect.Blur;
import com.npi.blureffect.ImageUtils;
import com.npi.blureffect.MySimpleArrayAdapter;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
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
	private static final int TOP_HEIGHT = 670;
	private static final String tg = "nitin";
	View rootView;
    public static final String ARG_PAGE = "page";

   
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
        super.onCreate(savedInstanceState);
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
		headerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, TOP_HEIGHT));
		String[] strings = getResources().getStringArray(R.array.list_content);
		strings[0]= "Hours : /n Monday : 7:30 am - 11:00 pm \n Tuesday : 7:30 am - 11:00 pm \n Wednesday : 7:30 am - 11:00 pm \n Thursday 7:30 am - 11:00 pm \n Friday 7:30 am - 11:00 pm";
		String[] headers = new String[10];
		headers[2] = "Hours";
		headers[4] = "Laptop Availability";
		headers[6] = "Contact";
		headers[8] = "Room Reservations";
		strings[0] = "Open Now \nStudy Rooms Available : 17 \nLaptops Not Available";
		strings[2]= "Monday : 7:30 am - 11:00 pm \nTuesday : 7:30 am - 11:00 pm \nWednesday : 7:30 am - 11:00 pm \nThursday 7:30 am - 11:00 pm \nFriday 7:30 am - 11:00 pm";
		strings[4] = "Laptops Not Available" ;
		strings[6] = "310.825.3138";
		strings[8] = "Study Rooms Available : 17";


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
		if(mPageNumber == 2)
		{	
			rootView.setBackgroundResource(R.drawable.powell);
			headers[0] =  getString(R.string.powell);
		}
		else if(mPageNumber == 1)
		{	
			rootView.setBackgroundResource(R.drawable.yrl);
			headers[0] =  getString(R.string.yrl);
		}
		else if(mPageNumber == 3)
		{	
			rootView.setBackgroundResource(R.drawable.mgmt);
			headers[0] =  getString(R.string.mgmt);
		}
		else if(mPageNumber == 4)
		{	
			rootView.setBackgroundResource(R.drawable.seml);
			headers[0] =  getString(R.string.seml);
		}	
		else if(mPageNumber == 0)
		{	
			rootView.setBackgroundResource(R.drawable.music);
			headers[0] =  getString(R.string.music);
		}

		// Get the screen width
		final int screenWidth = ImageUtils.getScreenWidth(getActivity());

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
}
