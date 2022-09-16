package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalquestionsTextView;
    TextView questionTestView;
    Button ansA, ansB, ansC, ansD;
    Button submitbtn;

    int score = 0;
    int totalquestions = question_answer.question.length;
    int currentquestionindex = 0;
    String selectedanswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        totalquestionsTextView = findViewById(R.id.total_question);
        questionTestView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_c);
        ansD = findViewById(R.id.ans_D);
        submitbtn = findViewById(R.id.submit);


        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitbtn.setOnClickListener(this);

        totalquestionsTextView.setText("Total questions: " + totalquestions);

             loadnewquestion();
    }

    @Override
    public void onClick(View v) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedbutton = (Button) v;
        if (clickedbutton.getId() == R.id.submit) {
            if (selectedanswer.equals(question_answer.correctanswers[currentquestionindex])) {
                score++;
            }
            currentquestionindex++;
            loadnewquestion();

        } else {
            //options button clicked

            selectedanswer = clickedbutton.getText().toString();
            clickedbutton.setBackgroundColor(Color.MAGENTA);
        }

    }

    public void loadnewquestion() {

        if (currentquestionindex == totalquestions) {
            finishquiz();
            return;
        }
        questionTestView.setText(question_answer.question[currentquestionindex]);
        ansA.setText(question_answer.options[currentquestionindex][0]);
        ansB.setText(question_answer.options[currentquestionindex][1]);
        ansC.setText(question_answer.options[currentquestionindex][2]);
        ansD.setText(question_answer.options[currentquestionindex][3]);
    }

    void finishquiz() {
        String passStatus = "";
        if (score >totalquestions*0.70){
            passStatus="passed";
        }
        else{
            passStatus="Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("score is  " +  score   + "   out of "+ totalquestions)
                .setPositiveButton("Restart",(dialogInterface , i) -> restartquiz())
                .setCancelable(false)
                .show();

    }
     void restartquiz(){
        score=0;
        currentquestionindex=0;
        loadnewquestion();
     }

}