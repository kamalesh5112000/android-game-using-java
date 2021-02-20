package com.example.mcgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class gameoverActivity extends AppCompatActivity {

    private Button startgameagin;
    private TextView displayscore;
    private String score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        score=getIntent().getExtras().get("Score").toString();

        startgameagin = (Button)findViewById(R.id.button);
        displayscore = (TextView)findViewById(R.id.displayscore);
        startgameagin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent mainintent =new Intent(gameoverActivity.this,MainActivity.class);
                startActivity(mainintent);

            }
        });

        displayscore.setText("Score ="+score);
    }
}
