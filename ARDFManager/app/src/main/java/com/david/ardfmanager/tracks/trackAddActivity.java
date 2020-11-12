package com.david.ardfmanager.tracks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.david.ardfmanager.R;

public class trackAddActivity extends AppCompatActivity {

    Button confButton;
    EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.add_track);
        setContentView(R.layout.activity_track_add);

        confButton = (Button) findViewById(R.id.confButton);
        nameEditText = (EditText) findViewById(R.id.nameEditText);

        confButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name", nameEditText.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}