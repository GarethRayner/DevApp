package com.mycompany.devapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

public class ArticleRead extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_read);

        FragmentManager articleLoader = getFragmentManager();

        FragmentTransaction articleLoaderTrans = articleLoader.beginTransaction();

        Intent getChoice = getIntent();

        String choice = getChoice.getStringExtra("article");

        if(choice.compareTo("duck") == 0) {
            DuckArticleFragment duckArticle = new DuckArticleFragment();

            articleLoaderTrans.replace(R.id.articleContainer, duckArticle);
        } else {
            GeeseArticleFragment geeseArticle = new GeeseArticleFragment();

            articleLoaderTrans.replace(R.id.articleContainer, geeseArticle);
        }

        articleLoaderTrans.commit();
    }

}
