package com.mycompany.devapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Notes extends Activity {
    SharedPreferences note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        note = this.getPreferences(Context.MODE_PRIVATE);
        if(note.contains("note")) {
            TextView noteEntry = (TextView) findViewById(R.id.notes_view_space);
            noteEntry.setText(note.getString("note", "No notes available"));
        }
    }

    public void changeNote(View view) {
        EditText noteEntry = (EditText) findViewById(R.id.notes_entry);
        TextView notesDisplay = (TextView) findViewById(R.id.notes_view_space);

        String noteMessage = noteEntry.getText().toString();

        note.edit().putString("note", noteMessage).apply();
        notesDisplay.setText(noteMessage);
    }
}
