package com.example.ideachat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.ideachat.SettingsActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ConvoScreenActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convo_screen);
        
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ContactListFragment())
                    .commit();
            
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.convo_screen, menu);
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
        } else if (id == R.id.action_profile) {

           	this.openProfile();
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

	private void openProfile() {
		// TODO Auto-generated method stub
		
    	// Switch to Profile Activity
    	Intent intent = new Intent(this, ProfileActivity.class);
    	startActivity(intent);    		
	}

    /**
     * A Contact List fragment containing a simple view.
     */
    public static class ContactListFragment extends ListFragment {
        public ContactListFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_convo_list, container, false);
            ArrayList<String> names = new ArrayList<String> (Arrays.asList("Peter", "Bob", "Keiming", "Peter1", "Bob1", "Keiming1", "Peter2", "Bob2", "Keiming2", "Peter3", "Bob3", "Keiming3"));
            ArrayList<String> descriptions = new ArrayList<String> (Arrays.asList( "Description1", "Description2", "Description3", "Description4", "Description5", "Description6", "Description7", "Description8", "Description9", "Description10", "Description11", "Description12"));
            // use your custom layout
            ConvoPanelAdapter adapter = new ConvoPanelAdapter(this.getActivity(), names, descriptions);
            setListAdapter(adapter);
          return rootView;
        }
        
        @Override
		public void onListItemClick (ListView list, View view, int position, long id) {
        	// TODO Switch to the chat activity for that "conversation"
        	// Switch to Chat Activity with the id of the chat
        	Intent intent = new Intent(this.getActivity(), ChatScreenActivity.class);
        	intent.putExtra("chat_id", id);
        	intent.putExtra("chat_tag", view.getTag().toString());
        	startActivity(intent);    		
        }
    }
    
}
