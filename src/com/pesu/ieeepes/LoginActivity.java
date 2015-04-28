package com.pesu.ieeepes;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity {
	/**
	 * A dummy authentication store containing known user names and passwords.
	 * TODO: remove after connecting to a real authentication system.
	 */
	private static final String[] DUMMY_CREDENTIALS = new String[] {
		"shubham.khurana@ieee.org:ieeeadmin", "sumukhdesu@gmail.com:ieeeadmin" , "chandra.divya13@gmail.com:ieeeadmin","tejna93@gmail.com:ieeeadmin","dugarab@gmail.com:ieeeadmin","girish.bathala@gmail.com:ieeeadmin","sindhubm93@gmail.com:ieeeadmin","test@gmail.com:test"};
	public static String loggedInAs = null;
	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	// UI references.
	private EditText mEmailView;
	private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;
	public static SharedPreferences settings = null;
	Set<String> email = new HashSet<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.login);	
		//findViewById(R.id.button1).setVisibility(View.INVISIBLE);
		settings = this.getSharedPreferences("MyApp", getApplicationContext().MODE_PRIVATE);
		/*settings = this.getSharedPreferences("MyApp", getApplicationContext().MODE_PRIVATE);
		SharedPreferences.Editor e = LoginActivity.settings.edit();
		for(int i = 0; i < DUMMY_CREDENTIALS.length; ++i) {
			email.add(DUMMY_CREDENTIALS[i]);
		}
		e.putStringSet("email", email);
		e.commit();*/
		// Set up the login form.
		mEmail = getIntent().getStringExtra(EXTRA_EMAIL);
		mEmailView = (EditText) findViewById(R.id.email);
		mEmail = settings.getString("login", null);
		if(mEmail != null)
			mEmailView.setText(mEmail);		
		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView
		.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id,
					KeyEvent keyEvent) {
				if (id == R.id.login || id == EditorInfo.IME_NULL) {
					attemptLogin();
					return true;
				}
				return false;
			}
		});

		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
		findViewById(R.id.button1).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						loggedInAs = "guest";
						Toast.makeText(getApplicationContext(), "Logged in as guest!", Toast.LENGTH_LONG).show();
						Intent mainIntent = new Intent(LoginActivity.this,MainActivity.class);
						LoginActivity.this.startActivity(mainIntent);
						LoginActivity.this.finish();
					}
				});
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mEmailView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mEmail = mEmailView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		} else if (mPassword.length() < 4) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		} else if (!mEmail.contains("@")) {
			mEmailView.setError(getString(R.string.error_invalid_email));
			focusView = mEmailView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);			
			mAuthTask = new UserLoginTask();
			mAuthTask.execute((Void) null);
		}
	}


	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

		private ProgressDialog dialog;
		public UserLoginTask() {		           
			dialog = new ProgressDialog(LoginActivity.this);
		}
		protected void onPreExecute() {
			dialog.setMessage("Logging in!");
			dialog.show();
		}
		protected Boolean doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			try {
				// Simulate network access.
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return false;
			}
			/*Set<String> nEmail = settings.getStringSet("email", null);
			String NEWARR[] = new String[nEmail.size()];*/
			for (String credential : DUMMY_CREDENTIALS) {
				String[] pieces = credential.split(":");
				if (pieces[0].equals(mEmail)) {
					// Account exists, return true if the password matches.					
					//((TextView) rootView.findViewById(R.id.textView2)).setText("logged in as " + pieces[0]);
					if(pieces[1].equals(mPassword)) {
						loggedInAs = pieces[0];
						SharedPreferences.Editor e = LoginActivity.settings.edit();
						e.putString("login", loggedInAs);
						e.commit();
						//((TextView) rootView.findViewById(R.id.textView2)).setText("logged in as " + pieces[0]);
					}

					return pieces[1].equals(mPassword);
				}
			}

			// TODO: register the new account here.
			return false;
		}
		/**
		 * Shows the progress UI and hides the login form.
		 */


		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			if (dialog.isShowing()) {
				dialog.dismiss();
			}

			if (success) {
				Toast.makeText(getApplicationContext(), "Successfully signed in!", Toast.LENGTH_LONG).show();
				Intent mainIntent = new Intent(LoginActivity.this,MainActivity.class);
				LoginActivity.this.startActivity(mainIntent);
				LoginActivity.this.finish();
			} else {
				mPasswordView
				.setError(getString(R.string.error_incorrect_password));
				mPasswordView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
		}
	}
}
