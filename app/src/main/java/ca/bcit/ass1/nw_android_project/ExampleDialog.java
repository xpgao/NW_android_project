package ca.bcit.ass1.nw_android_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatDialogFragment;

public class ExampleDialog extends AppCompatDialogFragment {


    int id;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Search Request").setMessage("This is a test").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(id == 0){
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    startActivity(intent);
                }else if(id == 1){
                    System.exit(0);
                }else if(id == 2){
                    System.out.println("SIDRAT");
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
