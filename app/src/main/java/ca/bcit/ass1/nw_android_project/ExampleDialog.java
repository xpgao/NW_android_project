package ca.bcit.ass1.nw_android_project;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatDialogFragment;

import static ca.bcit.ass1.nw_android_project.MainActivity.REQUEST_LOCATION;

public class ExampleDialog extends AppCompatDialogFragment {


    int id;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Search Request").setMessage("This is a test").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(id == 0){

                    String sessionCategory = "Mental Health";
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    intent.putExtra("category", sessionCategory);
                    startActivity(intent);
                }else if(id == 1){
                    String sessionCategory = "Addiction";
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    intent.putExtra("category", sessionCategory);
                    startActivity(intent);
                }else if(id == 2){
                    String sessionCategory = "Both";
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    intent.putExtra("category", sessionCategory);
                    startActivity(intent);
                }
//                    Intent intent = new Intent(getActivity(), SearchActivity.class);
//                    startActivity(intent);
            }
        });
        return builder.create();
    }

    public void setID(int _id) {
        id = _id;
    }
}
