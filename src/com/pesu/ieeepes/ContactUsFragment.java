package com.pesu.ieeepes;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactUsFragment extends Activity {
	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}
	 public void onCreate(Bundle icicle) {
		 
	    super.onCreate(icicle);
	    setContentView(R.layout.contactus);
		Button send = (Button) findViewById(R.id.button1); 
		final List <String> email = new ArrayList<String>();
		email.add("girish95k@gmail.com");
		//email.add("droidappsdevs@gmail.com");
		send.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {/*
				if(isOnline() == true) {
					new SendMailTask(ContactUsFragment.this).execute("gkggkkgk@gmail.com",
							"gk123456", email ,"Suggestions & Queries from Manu Kothari's app", "Name: "+(((EditText)findViewById(R.id.editText1)).getText().toString())+ "\n\n" + System.getProperty("line.separator") + "\n\nBranch and Semester: " + (((EditText)findViewById(R.id.editText2)).getText().toString()) + System.getProperty("line.separator") + "\n\nSuggestion/Query: " + (((EditText)findViewById(R.id.editText3)).getText().toString()));
					((EditText)findViewById(R.id.editText1)).setText("");
					((EditText)findViewById(R.id.editText2)).setText("");
					((EditText)findViewById(R.id.editText3)).setText("");            	
				}            
				else{
					Toast.makeText(getApplicationContext(), "No Internet Connection! Switch on WiFi/Data..", Toast.LENGTH_LONG).show();
				}*/
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822");
				i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"ieeepesusb@gmail.com"});
				i.putExtra(Intent.EXTRA_SUBJECT, "Suggestions and Queries from IEEE PESU App");
				i.putExtra(Intent.EXTRA_TEXT   , "Name: "+(((EditText)findViewById(R.id.editText1)).getText().toString())+ "\n\n" + System.getProperty("line.separator") + "\n\nBranch and Semester: " + (((EditText)findViewById(R.id.editText2)).getText().toString()) + System.getProperty("line.separator") + "\n\nSuggestion/Query: " + (((EditText)findViewById(R.id.editText3)).getText().toString()));
				try {
				    startActivity(Intent.createChooser(i, "Send mail..."));
				} catch (android.content.ActivityNotFoundException ex) {
				    Toast.makeText(ContactUsFragment.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

}
