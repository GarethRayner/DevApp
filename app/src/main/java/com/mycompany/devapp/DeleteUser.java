package com.mycompany.devapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteUser extends Activity {
    private UserSQLHelper userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        userDB = new UserSQLHelper(this);
    }

    public void deleteUser(View view) {
        EditText user = (EditText) findViewById(R.id.delete_entry);

        String username = user.getText().toString();

        String userExists = userDB.getUser(username);

        if(userExists != null) {
            DeleteUserTask deleteUser = new DeleteUserTask();
            deleteUser.setDB(userDB);

            String usna[] = {username};
            deleteUser.execute(usna);

            Toast toast = Toast.makeText(this, "User deleted", Toast.LENGTH_SHORT);
            toast.show();

            Intent intent = new Intent(this, UserLogin.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, "User not found", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private class DeleteUserTask extends AsyncTask<String, Void, Void> {
        private UserSQLHelper data;

        @Override
        protected Void doInBackground(String... params) {
            data.deleteUser(params[0]);
            return null;
        }

        public void setDB(UserSQLHelper db) {
            data = db;
        }
    }
}
