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


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashScreen extends Activity {
	MyTask objMyTask;
	private ProgressBar bar;

	final Context context = this;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
	    bar = (ProgressBar) this.findViewById(R.id.progressBar);

		if(haveInternet(context) == false)
		{
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					context);
            alertDialog.setTitle("No Internet Connection"); // your dialog title 
            alertDialog.setMessage("The current version of the app can not be used without an internet connection");
            alertDialog.setIcon(R.drawable.ic_launcher); // the icon besides the title you have to change it to the icon/image you have. 
            alertDialog
            .setMessage("The current version of the app can not be used without an internet connection") // a message above the buttons
			.setCancelable(false)
			.setPositiveButton("Ok! Got it..",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// if this button is clicked, close
					// current activity
					dialog.cancel();
				
					finish();					
				
				}
			  });
			// create alert dialog
			AlertDialog alertDialog1 = alertDialog.create();

            alertDialog1.show();

		}
		else
		{	
			MyTask mytask = new MyTask();
		mytask.execute();	
		}
			
	}
	
	
	class MyTask extends AsyncTask<Void, Integer, Void> {

		TextView tvLoading,tvPer;
		Button btnCancel;

		@Override
		protected void onPreExecute() {
	        bar.setVisibility(View.VISIBLE);

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
    		else
    		{
				try {
					lastUpdated = new SimpleDateFormat("EE MMM d k:m:s zzz yyyy", Locale.ENGLISH).parse(lastUpDate.toString());
	        		Log.v("meet","new"+lastUpdated); 

				} catch (ParseException e1) {
					Log.v("meet","didnotwork");
					e1.printStackTrace();
				}
			    diffInDays = (int) ((today.getTime() - lastUpdated.getTime())/ DAY_IN_MILLIS );

    		}
			Log.v("meet", "date " + String.valueOf(diffInDays));

			if(diffInDays >= 0 )
			{	
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
		            		editor.apply();

		                 	Log.v("meet1", "date is"+datestring);
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
	        bar.setVisibility(View.GONE);

			super.onPostExecute(result);
			
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					context);
            alertDialog.setTitle("Welcome"); // your dialog title 
            alertDialog.setMessage("Use slide gestures to navigate to the library of your choice. Change the order by navigating to the settings options in the menu"); // a message above the buttons
            alertDialog.setIcon(R.drawable.ic_launcher); // the icon besides the title you have to change it to the icon/image you have. 
            alertDialog
            .setMessage("Use slide gestures to navigate to the library of your choice. Change the order by navigating to the settings options in the menu") // a message above the buttons
			.setCancelable(false)
			.setPositiveButton("Ok! Got it..",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// if this button is clicked, close
					// current activity
					dialog.cancel();
					Intent i = new Intent(SplashScreen.this, ScreenSlideActivity.class);
					startActivity(i);
					finish();					
				
				}
			  });
			// create alert dialog
			AlertDialog alertDialog1 = alertDialog.create();

            alertDialog1.show();
            /*

			final Dialog dialog = new Dialog(context);
			dialog.setContentView(R.layout.custom_dialog);
			dialog.setTitle("Title...");
			// set the custom dialog components - text, image and button
			TextView text = (TextView) dialog.findViewById(R.id.text);
			text.setText("Android custom dialog example!");
			ImageView image = (ImageView) dialog.findViewById(R.id.image);
			image.setImageResource(R.drawable.ic_launcher);

			Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
			// if button is clicked, close the custom dialog
			dialogButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();

					Intent i = new Intent(SplashScreen.this, ScreenSlideActivity.class);
					startActivity(i);
					finish();
				}
			});

			dialog.show();
	*/
				
			
		}
	}
	public static boolean haveInternet(Context ctx) {

	    NetworkInfo info = (NetworkInfo) ((ConnectivityManager) ctx
	            .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

	    if (info == null || !info.isConnected()) {
	        return false;
	    }
	    if (info.isRoaming()) {
	        // here is the roaming option you can change it if you want to
	        // disable internet while roaming, just return false
	        return false;
	    }
	    return true;
	}
}