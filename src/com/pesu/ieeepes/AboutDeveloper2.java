package com.pesu.ieeepes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AboutDeveloper2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_developer2);
		setTitle("About the Developer");
		((Button)findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				/*Intent smsIntent = new Intent(Intent.ACTION_VIEW);
				smsIntent.setType("vnd.android-dir/mms-sms");
				smsIntent.putExtra("address", "8951728363");
				startActivity(smsIntent);*/
				try{
					Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
					smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
					smsIntent.setType("vnd.android-dir/mms-sms");
					smsIntent.setData(Uri.parse("sms:" + "9535112821")); 
					safeOpenActivityIntent(AboutDeveloper2.this,smsIntent);
				}
				catch(Exception e){}
			}
		});
		((Button)findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
						"mailto","girish95k@gmail.com", null));
				startActivity(Intent.createChooser(emailIntent, "Send email..."));
			}
		});
		((Button)findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/kgirish"));
				startActivity(browserIntent);
			}
		});
		((Button)findViewById(R.id.button4)).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/girish95k"));
				startActivity(browserIntent);
			}
		});
		((Button)findViewById(R.id.button5)).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/+GirishKrishnamurthy1/posts"));
				startActivity(browserIntent);
			}
		});
	}
	public static void safeOpenActivityIntent(Context context, Intent activityIntent) {

		// Verify that the intent will resolve to an activity
		if (activityIntent.resolveActivity(context.getPackageManager()) != null) {
			context.startActivity(activityIntent);
		} else {
			Toast.makeText(context, "App not available for request!", Toast.LENGTH_LONG).show();
		}
	}
}
