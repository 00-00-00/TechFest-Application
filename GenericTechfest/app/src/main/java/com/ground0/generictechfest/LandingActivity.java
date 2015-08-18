package com.ground0.generictechfest;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ground0.generictechfest.network.API;
import com.ground0.generictechfest.network.model.WorkshopList;
import com.ground0.generictechfest.network.model.Workshops;
import com.ground0.generictechfest.ui.EventAdapter;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LandingActivity extends ActionBarActivity {

    private static ProgressBar progressBar;
    private static ListView listview;
    private EventAdapter eventAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        listview=(ListView)findViewById(R.id.eventList);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        listview.setVisibility(View.GONE);

        API.getApi().getWorkshop(new Callback<Workshops>() {
            @Override
            public void success(Workshops workshops, Response response) {
                Log.i("com.ground0.generictechfest","GET Success");
                progressBar.setVisibility(View.GONE);
                listview.setVisibility(View.VISIBLE);
                eventAdapter=new EventAdapter(LandingActivity.this, workshops.getWorkshopList());
                listview.setAdapter(eventAdapter);

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                      WorkshopList wl=(WorkshopList)listview.getItemAtPosition(position);
                        Toast.makeText(LandingActivity.this,wl.getName()+"! That is epic!!",Toast.LENGTH_SHORT).show();
                    }
                });

                listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        WorkshopList wl=(WorkshopList)listview.getItemAtPosition(position);


                        Intent resultIntent=new Intent(LandingActivity.this,LandingActivity.class);
                        TaskStackBuilder stackBuilder=TaskStackBuilder.create(LandingActivity.this);
                        stackBuilder.addParentStack(LandingActivity.class);
                        stackBuilder.addNextIntent(resultIntent);

                        PendingIntent intent=stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);





                        NotificationCompat.Builder mBuilder =
                                new NotificationCompat.Builder(LandingActivity.this)
                                        .setSmallIcon(R.drawable.abc_btn_check_to_on_mtrl_015)
                                        .setContentTitle(wl.getName())
                                        .setContentText("You have registered")
                                        .setContentIntent(intent);
                        NotificationManager mNotificationManager =
                                (NotificationManager) getSystemService(LandingActivity.NOTIFICATION_SERVICE);
                        mNotificationManager.notify(0,mBuilder.build());

                        return false;
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {

            }


        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_landing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
