package com.example.speedometer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class EntryScreen extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
   
    public static final int SETTINGS_NUMBER = 3;
    

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_screen);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
    	// Selects the item on the navigation drawer
    	switch (position) {
    	case SETTINGS_NUMBER:
    		this.switchToSettings();
    		break;
    	default:
    		// position starts from 0
	    	// update the main content by replacing fragments
	        FragmentManager fragmentManager = getSupportFragmentManager();
	        fragmentManager.beginTransaction()
	                .replace(R.id.container, ScreenFragment.newInstance(position + 1))
	                .commit();
	        break;
    	}
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
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
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.entry_screen, menu);
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return switchToSettings();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean switchToSettings() {
    	// Switch to Settings Activity
    	Intent intent = new Intent(this, SettingsActivity.class);
    	startActivity(intent);    
    	return true;
	}

	/**
     * A screen fragment which implements the view for all the difference settings
     */
    public static class ScreenFragment extends Fragment implements SensorEventListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
    	private SensorManager mSensorManager;
    	private Sensor mAccelerometer;
    	private TextView textView;
    	private int sectionId;
    	private float[] curAccelValues = new float [3];
    	private float[] prevAccelValues = new float [3];
    	
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ScreenFragment newInstance(int sectionNumber) {
        	ScreenFragment fragment = new ScreenFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public ScreenFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Setup context needed to assosciate the sensor with
        	Context mContext = getActivity();
        	assert (mContext != null); // makes sure that the context isn't null.
        	
        	// Initiates accelerometer
            mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mSensorManager.registerListener(this, mAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
        }
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
        	Sensor mySensor = sensorEvent.sensor;
        	 
            if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                
            	curAccelValues[0] = sensorEvent.values[0];
                curAccelValues[1] = sensorEvent.values[1];
                curAccelValues[2] = sensorEvent.values[2];
                outputScreen();
            }
        }
     
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
     
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	// Setup the view related constants which determine what each of the views are. 
            View rootView = inflater.inflate(R.layout.fragment_entry_screen, container, false);
            textView = (TextView) rootView.findViewById(R.id.section_label);
            sectionId = getArguments().getInt(ARG_SECTION_NUMBER);
            return rootView;
        }
        

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((EntryScreen) activity).onSectionAttached(
            		getArguments().getInt(ARG_SECTION_NUMBER));
        }
        
        private void outputScreen () {
        	String outputText;
            switch (sectionId) {
            case 1: // Acceleration
            	outputText = "X Acceleration: " + Float.toString(curAccelValues[0]) + "\n";
            	outputText += "Y Acceleration: " + Float.toString(curAccelValues[1]) + "\n";
            	outputText += "Z Acceleration: " + Float.toString(curAccelValues[2]) + "\n";
            	break;
            case 2:	// Velocity
            	outputText = "2";
            	break;
            case 3: // Distance
            	outputText = "3";
            	break;
            default:
            	outputText = "default";
           		break;
            }
            textView.setText(outputText);
        }
        
    }

}
