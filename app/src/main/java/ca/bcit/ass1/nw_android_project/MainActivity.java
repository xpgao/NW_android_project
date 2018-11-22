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

        radioGroup = findViewById(R.id.radioGroup);

        double currentLatti = getLatti();
        double currentLongi = getLongi();
    }

    public void checkButton(View v){
        int radioID = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioID);
        openDialog(radioID);
        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    public void openDialog(int radioID){
        ExampleDialog exampleDialog = new ExampleDialog();
        if(radioButton.getText().equals("Mental health")){
            exampleDialog.setID(0);
        }
        else if(radioButton.getText().equals("Addiction")){
            exampleDialog.setID(1);
        }
        else if(radioButton.getText().equals("Both")){
            exampleDialog.setID(2);
        }

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

    public double getLatti(){
        double latti = 0;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION
                )!= PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }else{
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location != null){
                latti = location.getLatitude();
                //double latti = location.getLongitude();
            }
        }
        return latti;
    }

    public double getLongi(){
        double longi = 0;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION
        )!= PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }else{
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location != null){
                longi = location.getLatitude();
                //double longi = location.getLongitude();
            }
        }
        return longi;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_LOCATION:
                getLatti();
                getLongi();
                break;
        }
    }
}
