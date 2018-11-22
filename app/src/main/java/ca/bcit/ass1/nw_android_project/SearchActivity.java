package ca.bcit.ass1.nw_android_project;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.support.v7.widget.ShareActionProvider;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SearchActivity extends AppCompatActivity {


    static public String name;
    static public String category;
    static public double latitude;
    static public double longitude;

    //not sure what next line is for, could be causing problems
    public String TAG = Root.class.getSimpleName();
    public ProgressDialog pDialog;
    public ListView lv;
    // URL to get contacts JSON
    public static String SERVICE_URL;
    public ArrayList<Root> service_details;
    /**
     * Async task class to get json by making HTTP call
     */
    public class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(SearchActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        public Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            SERVICE_URL = "https://api.myjson.com/bins/b86q6";
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(SERVICE_URL);
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray toonJsonArray = jsonObj.getJSONArray("services");
                    String sessionCategory = getIntent().getStringExtra("category");
                    TreeMap<Double, Root> myMap = new TreeMap<>();
                    for(int i = 0; i < toonJsonArray.length(); i++){
                        JSONObject obj = toonJsonArray.getJSONObject(i);
                        if(sessionCategory.compareTo(obj.getString("category")) == 0) {
                            name = obj.getString("name");
                            category = obj.getString("category");
                            latitude = obj.getDouble("latitude");
                            longitude = obj.getDouble("longitude");

                            Root toon = new  Root();
                            // adding each child node to HashMap key => value
                            toon.setName(name);
                            toon.setCategory(category);
                            toon.setLatitude(latitude);
                            toon.setLongitude(longitude);
                            // adding contact to contact list
                            myMap.put(latitude, toon);
                            //service_details.add(toon);
                        }else if(sessionCategory.compareTo("Both") == 0){
                            name = obj.getString("name");
                            category = obj.getString("category");
                            latitude = obj.getDouble("latitude");
                            longitude = obj.getDouble("longitude");

                            Root toon = new  Root();
                            // adding each child node to HashMap key => value
                            toon.setName(name);
                            toon.setCategory(category);
                            toon.setLatitude(latitude);
                            toon.setLongitude(longitude);
                            // adding contact to contact list
                            myMap.put(latitude, toon);
                            //service_details.add(toon);
                        }
                    }

                    Iterator it = myMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();
                        service_details.add((Root)pair.getValue());
//                        it.remove(); // avoids a ConcurrentModificationException
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            RootAdapter adapter = new RootAdapter(SearchActivity.this, service_details);
            // Attach the adapter to a ListView
            lv.setAdapter(adapter);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        service_details = new ArrayList<Root>();
        lv = (ListView) findViewById(R.id.center_details);
        //need to change this
        new SearchActivity.GetContacts().execute();
    }

//    public double getLatti(){
//        double latti = 0;
//        if(ActivityCompat.checkSelfPermission())
//    }
}
