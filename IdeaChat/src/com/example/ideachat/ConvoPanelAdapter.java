package com.example.ideachat;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ConvoPanelAdapter extends ArrayAdapter<String> {
	private final Context context;
	private ArrayList<String> names;
	private ArrayList<String> descriptions;
		
	public ConvoPanelAdapter(Context context, ArrayList<String> names, ArrayList<String> descriptions) {
		// Initialize array adapter with the names.  otherwise the array adapter will be empty. 
		super(context, R.layout.convo_panel);
		this.context = context;
		this.names = names;
		this.descriptions = descriptions;
	}
		
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		//Check the convertview to handle when a similar view is recycled. 
		// http://stackoverflow.com/questions/11945563/how-listviews-recycling-mechanism-works
		View rowView = convertView;
		if (rowView == null) {
			rowView = inflater.inflate(R.layout.convo_panel, parent, false);
		}

		// Get the components of the contact panel and set them. 
		TextView name = (TextView) rowView.findViewById(R.id.convo_panel_name);
		TextView description = (TextView) rowView.findViewById(R.id.convo_panel_description);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.convo_panel_img);

		name.setText(names.get(position));
		description.setText(descriptions.get(position));
		
		// Set the tag of the view
		// TODO: Read the Tag of the view --> Tag is unique chat ID?
		// http://stackoverflow.com/questions/5291726/what-is-the-main-purpose-of-settag-gettag-methods-of-view
		rowView.setTag(names.get(position));
		
		return rowView;
	}
	
	@Override
	public int getCount () {
		// TODO: Count right now uses the number of the contacts in the list. 
		return names.size();
	}
	
	
	// TODO: Handle the addition of convos.  Load from local file, Make additions, Deletions. 
	
}
