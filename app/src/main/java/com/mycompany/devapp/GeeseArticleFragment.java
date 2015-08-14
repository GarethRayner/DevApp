package com.mycompany.devapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeeseArticleFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.geese_article_fragment, container, false);

        String imageUrl = "http://images4.alphacoders.com/278/278026.jpg";

        ImageView imageHold = (ImageView) view.findViewById(R.id.article_image);
        imageHold.setTag(imageUrl);
        new DownloadImageTask().execute(imageHold);

        return view;
    }

    private class DownloadImageTask extends AsyncTask<ImageView, Void, Bitmap> {
        ImageView view = null;

        @Override
        protected Bitmap doInBackground(ImageView... views) {
            Log.d("ASync", "Entering ASync.");
            view = views[0];
            try {
                InputStream image = null;
                URL url = new URL((String) view.getTag());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                conn.connect();
                int response = conn.getResponseCode();
                Log.d("HTTP Status", String.valueOf(response));
                image = conn.getInputStream();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;

                BitmapFactory.decodeStream(image, null, options);
                int imageHeight = options.outHeight;
                int imageWidth = options.outWidth;
                String imageType = options.outMimeType;

                options.inSampleSize = getProperSize(options, 400, 225);
                options.inJustDecodeBounds = false;

                //Give it a fresh connection.
                image.close();
                HttpURLConnection conn2 = (HttpURLConnection) url.openConnection();
                conn2.setReadTimeout(10000);
                conn2.setConnectTimeout(15000);
                conn2.setRequestMethod("GET");
                conn2.setDoInput(true);
                conn2.connect();
                response = conn2.getResponseCode();
                Log.d("HTTP Status", String.valueOf(response));
                image = conn2.getInputStream();

                Bitmap downIm = BitmapFactory.decodeStream(image, null, options);
                if(downIm == null) {
                    Log.d("ASync", "Bitmap is null!");
                }
                image.close();
                return downIm;
            } catch (Exception e) {
                Log.d("ASync", "Exception! " + e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.d("ASync", "Setting bitmap..");
            if(bitmap == null) {

            }
            view.setImageBitmap(bitmap);
        }

        private int getProperSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            if(height > reqHeight || width > reqWidth) {
                final int halfHeight = height / 2;
                final int halfWidth = width / 2;

                while((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                    inSampleSize *= 2;
                }
            }

            Log.d("ASync", "Calculating proper size: " + String.valueOf(inSampleSize));

            return inSampleSize;
        }
    }
}
