package ca.bcit.ass1.nw_android_project;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;

    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //getLocation();

        radioGroup = findViewById(R.id.radioGroup);
    }

    public void checkButton(View v){
        int radioID = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioID);
        openDialog(radioID);
        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    public void openDialog(int radioID){
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.setID(radioID);
        exampleDialog.show(getSupportFragmentManager(), "Example Dialog");
    }

    public void  crisis(View view){
        Intent intent = new Intent(MainActivity.this, crisiscenterActivity.class);
        startActivity(intent);
    }

    public void  goSearch(View view){
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }


//CODE FOR GETTING LONG LATI
//    void getLocation(){
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
//                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION
//                )!= PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
//        }else{
//            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//            if(location != null){
//                double latti = location.getLatitude();
//                double longi = location.getLongitude();
//
//                ((EditText) findViewById(R.id.lati)).setText("Latitude: " + latti);
//                ((EditText) findViewById(R.id.longi)).setText("Longitude: " + longi);
//            }else{
//                ((EditText) findViewById(R.id.lati)).setText("Could not find Latitude");
//                ((EditText) findViewById(R.id.longi)).setText("Could not find Longitude");
//            }
//        }
//    }

//CODE FOR GETTING LONG LATI
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        switch (requestCode){
//            case REQUEST_LOCATION:
//                getLocation();
//                break;
//        }
//    }
}
