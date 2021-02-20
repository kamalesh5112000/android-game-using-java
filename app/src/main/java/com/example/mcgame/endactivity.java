package com.example.mcgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class endactivity extends AppCompatActivity {

    private Button startbtn;
    private TextView displays;
    private String score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endactivity);

        score = getIntent().getExtras().get("Score").toString();


        startbtn = (Button) findViewById(R.id.paly_btn);
        displays = (TextView) findViewById(R.id.scoreid);
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainintent = new Intent(endactivity.this,MainActivity.class);
                startActivity(mainintent);
            }
        });

        displays.setText("Score : " + score);

    }
}
