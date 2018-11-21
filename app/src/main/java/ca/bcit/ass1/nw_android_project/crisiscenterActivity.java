package ca.bcit.ass1.nw_android_project;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class crisiscenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crisiscenter);
    }
    public void  browser1(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://crisiscentre.bc.ca/get-help/"));
        startActivity(browserIntent);
    }

    public void  browser2(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://chatlive.youthinbc.com/SightMaxAgentInterface/PreChatSurvey.aspx?accountID=1&siteID=3&queueID=9"));
        startActivity(browserIntent);
    }
}