package com.example.android.animationsdemo;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.android.animationsdemo.MainActivity.MyTask;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

public class MainActivityNew extends Activity implements
ActionBar.OnNavigationListener {

// action bar
	private ActionBar actionBar;

	private MenuItem refreshMenuItem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_actionbar);
		actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		// Enabling Spinner dropdown navigation
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.activity_main_actions, menu);
	return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	// Take appropriate action for each action item click
	switch (item.getItemId()) {
	
	
	case R.id.action_help:
		Log.v("meet","HELP CLICKED");
		return true;
	case R.id.action_check_updates:
	{	
		// check for updates action
		Log.v("meet","UPDATES");
	
		return true;
	}	
	default:
		return super.onOptionsItemSelected(item);
	}
	}
	
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
	// Action to be taken after selecting a spinner item
	return false;
	}
}
