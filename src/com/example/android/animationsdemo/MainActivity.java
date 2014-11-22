package com.example.android.animationsdemo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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



import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class MainActivity extends Activity implements
ActionBar.OnNavigationListener {
	Button btnStart;
	private ActionBar actionBar;

	private MenuItem refreshMenuItem;
	MyTask objMyTask;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_mainnew);
		actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		// Enabling Spinner dropdown navigation
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		MyTask mytask = new MyTask();
		mytask.execute();
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,ScreenSlideActivity.class);
				startActivity(i);
				
			}
		});
		Button btn1 = (Button) findViewById(R.id.button2);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,ListViewDraggingAnimation.class);
				startActivity(i);
				
			}
		});

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	// Take appropriate action for each action item click
		switch (item.getItemId()) 
		{	
			case R.id.powell:
				Log.v("meet","HELP CLICKED");
				return true;
			case R.id.yrl:
			{	
				Log.v("meet","UPDATES");			
				return true;
			}	
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	class MyTask extends AsyncTask<Void, Integer, Void> {

			Dialog dialog;
			ProgressBar progressBar;
			TextView tvLoading,tvPer;
			Button btnCancel;

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = new Dialog(MainActivity.this);
				dialog.setCancelable(false);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.progressdialog);

				progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar1);
				tvLoading = (TextView) dialog.findViewById(R.id.tv1);
				tvPer = (TextView) dialog.findViewById(R.id.tvper);
				btnCancel = (Button) dialog.findViewById(R.id.btncancel);

				btnCancel.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						objMyTask.cancel(true);
						dialog.dismiss();
					}
				});

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
                		editor31.putString( libraries[i],String.valueOf(i));
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

				if(diffInDays > 7)
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

					String json1 = json.substring(2,3739);
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
			protected void onProgressUpdate(Integer... values) {
				super.onProgressUpdate(values);
				progressBar.setProgress(values[0]);
				tvLoading.setText("Loading...  " + values[0] + " %");
				tvPer.setText(values[0]+" %");
			}

			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);

				dialog.dismiss();

				//AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
						//.create();

				//alert.setTitle("Completed!!!");
				//alert.setMessage("Your Task is Completed SuccessFully!!!");
				//alert.setButton("Dismiss", new DialogInterface.OnClickListener() {

					
				
			}
		}
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}
}
