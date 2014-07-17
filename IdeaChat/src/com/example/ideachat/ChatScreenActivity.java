package com.example.ideachat;

import java.util.ArrayList;
import java.util.Arrays;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Build;

public class ChatScreenActivity extends ActionBarActivity {
	private long chat_id;
	private String chat_tag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat_screen);
		
		// Determine the ID/tag information
		Intent intent = getIntent();
		this.chat_id = intent.getLongExtra("chat_id", -1);
		this.chat_tag = intent.getStringExtra("chat_tag");
		assert (this.chat_id != -1);
		
		//TextView chat_screen_text = (TextView) this.findViewById(R.id.chat_screen_text);
		String chat_screen_txt_string = "Chat ID: " + Long.toString(this.chat_id) + " || Chat Tag: " + this.chat_tag;
		//chat_screen_text.setText(chat_screen_txt_string);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chat_screen, menu);
		return true;
	}

	public void sendMessage (View view) {
		
		// Read the input message and reset the EditText to be empty
		EditText inputMsgView = (EditText) this.findViewById(R.id.chat_screen_input_msg);
		String inputMsg = inputMsgView.getText().toString();
		// if the input msg is empty --> don't send. 
		if (inputMsg.isEmpty()) {
			return;
		}
		
		// Reset the EditText View and the Keyboard
		inputMsgView.setText("");
		inputMsgView.clearFocus();
		
		// Hide Keyboard
		InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
	    //check if no view has focus:
	    View v=this.getCurrentFocus();
	    if(v==null) {
	        return;
	    }
	    inputManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

		// Communicate with the chat fragment
		ChatScreenFragment chatFrag = (ChatScreenFragment) getSupportFragmentManager().findFragmentById(R.id.chat_fragment);
		chatFrag.recevieMessage(inputMsg);
		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	/**
	 * The chat screen fragment which handles the messaging and updating of the display conversation.
	 */
	public static class ChatScreenFragment extends ListFragment {

		// Needs to be class variables so the conversation can be easily updated when the data is changed
        private ArrayAdapter<String> msgs_adapter;
        private ArrayList<String> msgs;
        
		public ChatScreenFragment () {
		}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_chat_screen, container, false);
            msgs = new ArrayList<String>(); 
            msgs_adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.chat_screen_self_msg, R.id.text, msgs);
            setListAdapter(msgs_adapter);
            
            return rootView;
            
        }
        

        @Override
        public void onActivityCreated(Bundle savedState) {
            super.onActivityCreated(savedState);

            // Sets a new onItenLongClickListener
            getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
	                @Override
	                public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	                    	// Remove data if the click is long
	                		msgs.remove(arg2);
	                		msgs_adapter.notifyDataSetChanged();
	                		return true;
	                		}
            		}
                );
        }
            
        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
        	
        	
        }
        
        public void recevieMessage(String msg) {
        	msgs.add(msg);
        	msgs_adapter.notifyDataSetChanged();
        }
	}

}
