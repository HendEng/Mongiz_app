package com.example.future.mongiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Begin extends AppCompatActivity {
    Button btn;
    TextView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        about = (TextView) findViewById(R.id.about);
        about.setText("");
        btn = (Button) findViewById(R.id.start);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Begin.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}