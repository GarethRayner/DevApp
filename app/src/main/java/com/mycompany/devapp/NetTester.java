package com.mycompany.devapp;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class NetTester extends Activity {
    DownloadManager downloadManager;
    String downloadFileUrl = "http://images.clipartpanda.com/tick-clipart-KTnby6jTq.png";
    long downReference;
    BroadcastReceiver downloadComplete;
    BroadcastReceiver notificationClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_tester);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED);

        notificationClicked = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String extraId = DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS;
                long[] references = intent.getLongArrayExtra(extraId);
                for(long reference : references) {
                    if(reference == downReference) {

                    }
                }
            }
        };

        registerReceiver(notificationClicked, filter);

        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);

        downloadComplete = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

                if(downReference == reference) {
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(reference);
                    Cursor cursor = downloadManager.query(query);

                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                    int status = cursor.getInt(columnIndex);
                    int fileNameIndex= cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
                    String savedFilePath = cursor.getString(fileNameIndex);

                    int columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON);
                    int reason = cursor.getInt(columnReason);

                    switch (status) {
                        case DownloadManager.STATUS_SUCCESSFUL:
                            Bitmap image = BitmapFactory.decodeFile(savedFilePath);

                            ImageView testImage = (ImageView) findViewById(R.id.testImage);
                            testImage.setImageBitmap(image);
                            break;
                        case DownloadManager.STATUS_FAILED:
                            Toast.makeText(NetTester.this, "Failed: " + reason, Toast.LENGTH_SHORT).show();
                            break;
                        case DownloadManager.STATUS_PAUSED:
                            Toast.makeText(NetTester.this, "Paused: " + reason, Toast.LENGTH_SHORT).show();
                            break;
                        case DownloadManager.STATUS_PENDING:
                            Toast.makeText(NetTester.this, "Pending...", Toast.LENGTH_SHORT).show();
                            break;
                        case DownloadManager.STATUS_RUNNING:
                            Toast.makeText(NetTester.this, "Running...", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
        };

        registerReceiver(downloadComplete, intentFilter);
    }

    public void onPause() {
        super.onPause();
        unregisterReceiver(downloadComplete);
        unregisterReceiver(notificationClicked);
    }

    public void testNet(View view) {
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(downloadFileUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setDescription("Downloading test file...").setTitle("Test File Download");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        downReference = downloadManager.enqueue(request);
    }
}
