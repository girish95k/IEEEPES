package com.pesu.ieeepes;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements
NavigationDrawerFragment.NavigationDrawerCallbacks {
	String mTabURL;
	ProgressDialog pd;
	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	WebView webview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webview = (WebView) findViewById(R.id.webView1);
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
		.beginTransaction()
		.replace(R.id.container,
				PlaceholderFragment.newInstance(position + 1)).commit();
	}
	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}
	public void loadUrl() {
		webview.setVisibility(View.VISIBLE);
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setVisibility(View.INVISIBLE);
		if(isOnline() == true) {
			pd = new ProgressDialog(webview.getContext());
			pd.setMessage("Please wait loading page...");
			pd.show();
			webview.setWebViewClient(new MyWebViewClient());   	    
			webview.getSettings().setBuiltInZoomControls(true); 
			webview.getSettings().setSupportZoom(true);
			webview.getSettings().setJavaScriptEnabled(true);
			webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);			
			webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);   
			webview.getSettings().setAllowFileAccess(true); 
			webview.getSettings().setDomStorageEnabled(true);
			webview.getSettings().setDatabaseEnabled(true);
			webview.loadUrl(mTabURL); 
		}
		else {
			/*webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);
			webview.loadUrl(mTabURL);*/
			Toast.makeText(getApplicationContext(), "No Internet Connection! Switch on WiFi/Data..", Toast.LENGTH_LONG).show();
		}
	}
	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = "About us";
			findViewById(R.id.webView1).setVisibility(View.INVISIBLE);
			findViewById(R.id.textView1).setVisibility(View.VISIBLE);
			break;
		case 2:
			mTitle = "Calendar of Events";
			mTabURL = "https://m.facebook.com/IEEE.PESIT/events";
			loadUrl();
			break;
		case 3:
			mTitle = "Gallery";
			mTabURL = "https://m.facebook.com/IEEE.PESIT/mediaset?album=pb.390598047635368.-2207520000.1412526171.";
			loadUrl();
			break;
		case 4:
			mTitle = "Facebook Page";
			mTabURL = "https://m.facebook.com/IEEE.PESIT";
			loadUrl();
			break;
		case 5:
			mTitle = "Twitter Page";
			mTabURL = "https://twitter.com/IEEEPESU";
			loadUrl();
			break;
		case 6:
			Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()));
			intent.setType("application/pdf"); 
			String intentToCheck = "com.adobe.reader"; //can be any other intent
			final PackageManager packageManager = getPackageManager();
			final Intent intentx = new Intent(intentToCheck);
			List list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
			final boolean isAvailable = list.size() > 0;

			if (isAvailable) {
				startActivity(intent);
			} else {
				Toast.makeText(getApplicationContext(), "Install Adobe Reader for best viewing!" , Toast.LENGTH_LONG);
				Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pname:com.adobe.reader"));
				startActivity(marketIntent);             	
			}
			break;		
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add("Contact Us - Ask!");
		menu.add("PES IEEE Team");
		menu.add("About Developer 1");
		//menu.add("About Developer 2");
	
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		if(item.getTitle().equals("Contact Us - Ask!")) {
			Intent myIntent = new Intent(MainActivity.this, ContactUsFragment.class);
			MainActivity.this.startActivity(myIntent); 
		}
		else if(item.getTitle().equals("PES IEEE Team")) {
			Intent myIntent = new Intent(MainActivity.this, Team.class);
			MainActivity.this.startActivity(myIntent);
		}
		else if(item.getTitle().equals("About Developer 1")){
			Intent myIntent = new Intent(MainActivity.this, AboutDeveloper.class);
			MainActivity.this.startActivity(myIntent);
		}
		else if(item.getTitle().equals("About Developer 2")){
			Intent myIntent = new Intent(MainActivity.this, AboutDeveloper2.class);
			MainActivity.this.startActivity(myIntent);
		}
		return super.onOptionsItemSelected(item);
	}
	public class MyWebViewClient extends WebViewClient {        
		/* (non-Java doc)
		 * @see android.webkit.WebViewClient#shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String)
		 */


		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);

			if (!pd.isShowing()) {
				pd.show();
			}

			return true;
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			System.out.println("on finish");
			if (pd.isShowing()) {
				pd.dismiss();
			}

		}
	}
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}
	/*
	private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
	private long mBackPressed;

	@Override
	public void onBackPressed()
	{
		if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) 
		{ 
			super.onBackPressed(); 
			return;
		}
		else { Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show(); }

		mBackPressed = System.currentTimeMillis();
	}
	*/
	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode)
            {
            case KeyEvent.KEYCODE_BACK:
                if(webview.canGoBack()){
                    webview.goBack();
                }else{
                    finish();
                }
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

}
