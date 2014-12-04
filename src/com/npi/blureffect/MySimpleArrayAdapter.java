package com.npi.blureffect;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.android.animationsdemo.R;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
  private final Context context;
  private final String[] values;
  private final String[] headers;
  public int pos = 0;

  public MySimpleArrayAdapter(Context context, String[] values, String[] headers) {
    super(context, R.layout.list_item, values);
    this.context = context;
    this.values = values;
    this.headers= headers; 
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
	  pos = position;
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.list_item, parent, false);
    TextView textView = (TextView) rowView.findViewById(R.id.textView1);
    TextView textView3 = (TextView) rowView.findViewById(R.id.textView3);
    TextView textView4 = (TextView) rowView.findViewById(R.id.textView4);
    ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView1);
	imageView.setVisibility(View.GONE);

    Log.v("meet","Value position is"+String.valueOf(position));
    Log.v("meet","Value is"+values[position]);
    if(headers[position].equalsIgnoreCase("Contact"))
    {
        imageView.setPadding(10,0,0,0);

    	textView.setVisibility(View.GONE);
        final String phonenum = values[position];
    	imageView.setVisibility(View.VISIBLE);
    	imageView.setImageResource(R.drawable.dialer);
    	imageView.setOnClickListener(new View.OnClickListener() {
    	    @Override
    	    public void onClick(View v) {
    	    	Intent intent = new Intent(Intent.ACTION_DIAL);
    	    	intent.setData(Uri.parse("tel:"+phonenum));
    	    	context.startActivity(intent); 
    	    }
    	});
    }
    if(headers[position].equalsIgnoreCase("Directions"))
    {	
    final String directions = values[position];
    textView.setVisibility(View.GONE);
    imageView.setPadding(0,10,0,0);
    imageView.setImageResource(R.drawable.maps);
	imageView.setVisibility(View.VISIBLE);
	imageView.getLayoutParams().height = 115;
	imageView.getLayoutParams().width = 125;
	imageView.requestLayout();
	imageView.setOnClickListener(new View.OnClickListener(){
	    public void onClick(View v){
	        Intent intent = new Intent();
	        intent.setAction(Intent.ACTION_VIEW);
	        intent.addCategory(Intent.CATEGORY_BROWSABLE);
	        intent.setData(Uri.parse(directions));
	        context.startActivity(intent);
	    }
	});
    textView.setText( Html.fromHtml("<a href=\""+values[position]+"\">Click here for directions</a>"));
    textView. setMovementMethod(LinkMovementMethod.getInstance());
    }
    else
    {	
    textView.setText(values[position]);
    Linkify.addLinks(textView, Linkify.ALL);
    }
    //textView.setAutoLinkMask(Linkify.ALL);  
    //textView.setAutoLinkMask(Linkify.PHONE_NUMBERS);
    TextView textView1 = (TextView) rowView.findViewById(R.id.textView2);
    Log.v("meet","Header position is"+String.valueOf(position));
    Log.v("meet","Header is"+headers[position]);
    textView1.setText(headers[position]);
    TextView test = new TextView(context, null);
  
    for(int i = 0 ; i < headers.length; i++)
    	Log.v("meet", "headers" + headers[i]);
    for(int i = 0 ; i < values.length; i++)
    	Log.v("meet", "values" + values[i]);
    
    LinearLayout l1 = (LinearLayout) rowView.findViewById(R.id.linearLayout1);
   
   
    if(position==0 )
    {
    	/*textView1.setBackgroundResource(R.color.trans1);
    	textView1.setPadding(0, 0,0,0);
    	textView.setPadding(0,0,0,0);
    	textView.setBackgroundResource(R.color.trans1);*/
    	l1.setBackgroundResource(R.color.trans1);
    	l1.setPadding(10, 10, 10, 10);
    	textView1.setTextSize(42);
    	textView.setTextSize(28);
    	if(textView.getText().equals("Open Now"))
    		textView.setTextColor(Color.parseColor("#88ff88"));
    	else
    		textView.setTextColor(Color.parseColor("#FF4C4C"));

    	textView4.setVisibility(View.GONE);

    }
    else if(position%2==1)
    {    	
    	l1.setBackgroundResource(R.color.trans1);
    	textView.setWidth(0);
    	textView1.setWidth(0);
    	textView.setPadding(0, 0,0,0);
    	textView1.setPadding(0, 0,0,0);
    	textView.setVisibility(View.GONE);
    	textView1.setVisibility(View.GONE);
    	textView3.setBackgroundResource(R.color.trans1);
    	textView4.setBackgroundResource(R.color.trans1);
    //	textView3.setVisibility(View.GONE);

    }
    else
    {
    	l1.setPadding(10, 10, 10, 10);
    	textView4.setVisibility(View.GONE);

    	 @SuppressWarnings("deprecation")
    		LayoutParams lparams = new LayoutParams(
    	    		   LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
    	 //THIS IS HOW YOU CAN ADD A TEXT VIEW
    	    	/*	test.setLayoutParams(lparams);
    	    		test.setTextColor(Color.parseColor("#eeeeee"));
    	    		test.setBackgroundResource(R.color.trans);
    	    		test.setText("test");
    	    		l1.addView(test);*/
    }
    
    return rowView;
  }
  

} 