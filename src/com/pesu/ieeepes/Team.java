package com.pesu.ieeepes;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class Team extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team);
		setTitle("The PES IEEE team");
		((TextView)findViewById(R.id.textView1)).setMovementMethod(new ScrollingMovementMethod());
	}
}
