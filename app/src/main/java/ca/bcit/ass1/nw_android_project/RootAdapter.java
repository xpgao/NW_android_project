package ca.bcit.ass1.nw_android_project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class RootAdapter extends ArrayAdapter<Root> {
    Context _context;
    public RootAdapter(Context context, ArrayList<Root> roots) {
        super(context, 0, roots);
        _context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        // Get the data item for this position
        Root root = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_layout, parent, false);
        }
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView category = (TextView) convertView.findViewById(R.id.category);
        TextView lati = (TextView) convertView.findViewById(R.id.lati);
        TextView longi = (TextView) convertView.findViewById(R.id.longi);


//
        // Populate the data into the template view using the data object
        name.setText("Name: ");
        name.append(root.getName());
        category.setText("Category: ");
        category.append(root.getCategory());
        lati.setText("Lati: ");
        lati.append(Double.toString(root.getLatitude()));
        longi.setText("Longi: ");
        longi.append(Double.toString(root.getLongitude()));

        // Return the completed view to render on screen
        return convertView;
    }
}