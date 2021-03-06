package com.example.ideachat;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			this.openSetting();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void openSetting() {
		// TODO Auto-generated method stub
		
    	// Switch to Settings Activity
    	Intent intent = new Intent(this, SettingsActivity.class);
    	startActivity(intent);    		
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_profile,
					container, false);
						
			return rootView;
		}
		
		@Override
		public void onResume () {
			super.onResume();
			// Update the profile page on resume
			this.updateProfile(this.getView());
		}
		
		private void updateProfile (View rootView) {
			
			// TODO: Should only update profile if anything has changed.  A flag to store the state of the profile?
			// TODO: Should use the owner profile to fill in contact information using the ContactsContract.Profile.CONTENT_URI
			
			// Read in the name from the settings menu. 
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
			String settings_name = preferences.getString("display_name", "empty");
			
			// Fill out the textbox in the Profile Layout
			// if using a TextView, make sure to make it android:edittable
			TextView profile_name = (TextView) rootView.findViewById(R.id.profile_name);
			profile_name.setText(settings_name);
			
			return;
		}
	}

}
