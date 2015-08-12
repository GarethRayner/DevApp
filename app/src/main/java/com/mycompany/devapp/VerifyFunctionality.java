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

    public void verifyApp(View view) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.verify_ic)
                .setContentTitle("App Verified")
                .setContentText("The app is working correctly");

        Intent resultIntent = new Intent(this, HomeScreen.class);

        PendingIntent backtrack = PendingIntent.getActivity(
                this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(backtrack);

        int notificationId = 001;

        NotificationManager notify = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notify.notify(notificationId, mBuilder.build());
    }

    public void verifyArticleDuck(View view) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.verify_ic)
                        .setContentTitle("Duck Article Verified")
                        .setContentText("Duck article viewing is working correctly.");

        mBuilder.setAutoCancel(true);

        Intent resultIntent = new Intent(this, ArticleRead.class);
        resultIntent.putExtra("article", "duck");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(ArticleRead.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent backtrack = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(backtrack);

        int notificationId = 001;

        NotificationManager notify = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notify.notify(notificationId, mBuilder.build());
    }
}
