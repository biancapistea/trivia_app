package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalScoreActivity extends AppCompatActivity {
    private TextView scoreTextView;
    private TextView felicitariTextView;
    private Button tryAgainButton;
    private int score=MainActivity.getScore();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);
        felicitariTextView=findViewById(R.id.felicitari);
        tryAgainButton=findViewById(R.id.buttonTryAgain);
        scoreTextView=findViewById(R.id.scoreText);
        if(score>=4){
            felicitariTextView.setText("Felicitari!");
            scoreTextView.setText("Ati obtinut "+score+ " puncte");
        }
        else
        {
            felicitariTextView.setText("Hmm..mai incearca!");
            scoreTextView.setText("Ati obtinut "+score+ " puncte");
        }
        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.setScore(0);
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}