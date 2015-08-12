package com.mycompany.devapp;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

public class VerifyFunctionality extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_functionality);
    }

    public void verify(View view) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(android.R.drawable.ic_dialog_info);
        mBuilder.setAutoCancel(true);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        String tag = view.getTag().toString();
        switch(tag) {
            case "duck":
                mBuilder.setContentTitle("Duck Article Verified");
                mBuilder.setContentText("Duck article viewing is working correctly.");
                Intent duckResultIntent = new Intent(this, ArticleRead.class);
                duckResultIntent.putExtra("article", "duck");

                stackBuilder.addParentStack(ArticleRead.class);
                stackBuilder.addNextIntent(duckResultIntent);
                break;
            case "geese":
                mBuilder.setContentTitle("Geese Article Verified");
                mBuilder.setContentText("Geese article viewing is working correctly.");
                Intent geeseResultIntent = new Intent(this, ArticleRead.class);
                geeseResultIntent.putExtra("article", "geese");

                stackBuilder.addParentStack(ArticleRead.class);
                stackBuilder.addNextIntent(geeseResultIntent);
                break;
            case "slider":
                mBuilder.setContentTitle("Article Slider Verified");
                mBuilder.setContentText("Article Slider is working correctly.");
                Intent sliderResultIntent = new Intent(this, ArticleSlider.class);

                stackBuilder.addParentStack(ArticleSlider.class);
                stackBuilder.addNextIntent(sliderResultIntent);
                break;
            case "new":
                mBuilder.setContentTitle("New Article Viewer Verified");
                mBuilder.setContentText("New article viewing is working correctly.");
                Intent newResultIntent = new Intent(this, NewArt.class);

                stackBuilder.addParentStack(NewArt.class);
                stackBuilder.addNextIntent(newResultIntent);
                break;
            case "free":
                mBuilder.setContentTitle("Free Press Verified");
                mBuilder.setContentText("Free Press entry is working correctly.");
                Intent resultIntent = new Intent(this, FreePress.class);

                stackBuilder.addParentStack(FreePress.class);
                stackBuilder.addNextIntent(resultIntent);
                break;
        }

        PendingIntent backtrack = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(backtrack);

        int notificationId = 001;

        NotificationManager notify = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notify.notify(notificationId, mBuilder.build());
    }
}
