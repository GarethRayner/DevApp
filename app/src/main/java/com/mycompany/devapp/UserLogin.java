package com.mycompany.devapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserLogin extends Activity {
    private UserSQLHelper userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        userDB = new UserSQLHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_register) {
            userService(0);
            return true;
        } else if(id == R.id.action_delete) {
            userService(1);
        }

        return super.onOptionsItemSelected(item);
    }

    private void userService(int type) {
        switch(type) {
            case 0:
                Intent intent1 = new Intent(this, UserRegister.class);
                startActivity(intent1);
                break;
            case 1:
                Intent intent2 = new Intent(this, DeleteUser.class);
                startActivity(intent2);
                break;
        }
    }

    public void userLogin(View view) {
        EditText user = (EditText) findViewById(R.id.username);
        EditText pass = (EditText) findViewById(R.id.password);

        String username = user.getText().toString();
        String password = pass.getText().toString();

        String result = userDB.getUser(username);
        if(result == null) {
            Toast toast = Toast.makeText(this, "No user found", Toast.LENGTH_SHORT);
            toast.show();
        } else if(result.compareTo(password) == 0) {
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, "Wrong user/password", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
