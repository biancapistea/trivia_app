package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.model.Question;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int questionIndex=0;
    private static int score=0;

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        MainActivity.score = score;
    }

    private final Question[] questions= new Question[]{
            new Question(R.string.question_anotimp,true),
            new Question(R.string.question_citit,true),
            new Question(R.string.question_basket,false),
            new Question(R.string.question_pictat, true),
            new Question(R.string.question_materie,true),
            new Question(R.string.question_filme, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        updateIndex();
        updateQuestion();
        binding.buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionIndex>0){
                    questionIndex--;
                    updateQuestion();
                    updateIndex();
                    resetButtons();
                    setButtons();
                }
            }
        });
        binding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionIndex<questions.length-1){
                    questionIndex++;
                }
                else{
                    Intent intent=new Intent(getApplicationContext(), FinalScoreActivity.class);
                    view.getContext().startActivity(intent);
                    finish();
                }
                updateQuestion();
                updateIndex();
                resetButtons();
                setButtons();
            }
        });
        binding.buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkAnswer(true)){
                    binding.buttonTrue.setBackgroundColor(Color.GREEN);
                }
                else
                {
                    binding.buttonTrue.setBackgroundColor(Color.RED);
                }
                updateButtonState(false);
            }
        });
        binding.buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkAnswer(false)){
                    binding.buttonFalse.setBackgroundColor(Color.GREEN);
                }
                else
                {
                    binding.buttonFalse.setBackgroundColor(Color.RED);
                }
                updateButtonState(false);
            }
        });

    }
    private void setButtons() {
        boolean userAnswer = questions[questionIndex].getUserAnswer();
        if (questions[questionIndex].isAnswered()) {
            if (userAnswer == questions[questionIndex].getAnswer()) {
                if (userAnswer)
                    binding.buttonTrue.setBackgroundColor(Color.GREEN);
                else
                    binding.buttonFalse.setBackgroundColor(Color.GREEN);
            } else {
                if (userAnswer)
                    binding.buttonTrue.setBackgroundColor(Color.RED);
                else
                    binding.buttonFalse.setBackgroundColor(Color.RED);
            }
            updateButtonState(false);
        }
    }
    private boolean checkAnswer(boolean userAnswer){
        questions[questionIndex].setUserAnswer(userAnswer);
        questions[questionIndex].setAnswered(true);

        if(questions[questionIndex].getAnswer()==userAnswer){
            score=score+1;
            binding.score.setText("Scorul pana in acest moment este: "+score);
            return true;
        }
        else{
            Toast.makeText(getApplicationContext(),"Raspunsul nu este corect!",Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private void updateButtonState(boolean isEnabled) {
        binding.buttonFalse.setEnabled(isEnabled);
        binding.buttonTrue.setEnabled(isEnabled);
    }

    private void resetButtons(){
        updateButtonState(true);
        binding.buttonTrue.setBackgroundColor(getResources().getColor(R.color.purple_500));
        binding.buttonFalse.setBackgroundColor(getResources().getColor(R.color.purple_500));
    }

    private void updateQuestion() {
        binding.question.setText(questions[questionIndex].getQuestionId());
    }
    @SuppressLint("SetTextI18n")
    private void updateIndex(){
        binding.questionIndex.setText("Intrebarea "+ (questionIndex + 1));
    }


}