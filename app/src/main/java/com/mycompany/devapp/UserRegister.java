package com.mycompany.devapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegister extends Activity {
    private UserSQLHelper userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        userDB = new UserSQLHelper(this);
    }

    public void registerUser(View view) {
        EditText user = (EditText) findViewById(R.id.username);
        EditText pass = (EditText) findViewById(R.id.password);

        String username = user.getText().toString();
        String password = pass.getText().toString();

        InsertUserTask bGTask = new InsertUserTask();
        bGTask.setDB(userDB);
        String[] args = new String[2];
        args[0] = username;
        args[1] = password;
        bGTask.execute(args);
        Toast toast = Toast.makeText(this, "User Created!", Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this, UserLogin.class);
        startActivity(intent);
    }

    private class InsertUserTask extends AsyncTask<String, Void, Void> {
        private UserSQLHelper data;

        @Override
        protected Void doInBackground(String... params) {
            data.addUser(params[0], params[1]);
            return null;
        }

        public void setDB(UserSQLHelper db) {
            data = db;
        }
    }
}
