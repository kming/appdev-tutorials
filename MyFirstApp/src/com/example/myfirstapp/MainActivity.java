package com.example.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

	public final static String INPUT_MSG_KEY = "com.example.myfirstapp.INPUT_MESSAGE";
	public final static String INPUT_TITLE_KEY = "com.example.myfirstapp.TITLE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
        	case R.id.action_search:
        		openSearch();
        		return true;
        	case R.id.action_settings:
        		openSetting();
        		return true;
        	case R.id.action_enable:
        		openEnable();
        		return true; 
        	default:
        		return super.onOptionsItemSelected(item);
        }
    }

    private void openEnable() {
		// TODO Auto-generated method stub
		// TODO Enables Application

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isEnabled = preferences.getBoolean("enabled",  false);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("enabled", !isEnabled);
        editor.commit();
       
	}


	private void openSetting() {
		// TODO Auto-generated method stub
		
    	// Switch to Settings Activity
    	Intent intent = new Intent(this, SettingsActivity.class);
    	startActivity(intent);    		
	}


	private void openSearch() {
		// TODO Auto-generated method stub
		// TODO Temporary debug function of displaying a manual message
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
	    boolean isDebugMode = preferences.getBoolean("DebugMode",  false);
	    if (isDebugMode) {
	    	// if Debug --> print message
	    	
			// Setup the intent which switches to another activity
	    	Intent intent = new Intent(this, DisplayMessageActivity.class);
	    	
	    	// Find the input text view and save the message
	    	String title = "OpenSearch";
	    	String message = "Searching...";
	    	
	    	// Pass as a key/value pair to the child intent
	    	intent.putExtra(INPUT_MSG_KEY, message); 
	    	intent.putExtra(INPUT_TITLE_KEY, title);
	    	startActivity(intent);    
		}
	
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    /** Called when user enters a message **/
    public void processMessage (View view) {
    	// Setup the intent which switches to another activity
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	
    	// Find the input text view and save the message
    	EditText text = (EditText) findViewById(R.id.edit_message);
    	String message = text.getText().toString();
    	
    	// Pass as a key/value pair to the child intent
    	intent.putExtra(INPUT_MSG_KEY, message);
    	startActivity(intent);
    }

}
