package mx.edu.greengates.dvazquez.persistance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import mx.edu.greengates.dvazquez.persistance.model.QuestionResult;

public class ResultsTableActivity extends AppCompatActivity {

    private final String[] questions = {"Question 1","Question 2","Question 3","Question 4","Question 5",};

    private final String[] answers = {"Answers 1", "Answer 2", "Answer 3", "Answer 4", "Answer 5"};

    private final boolean[] results = {true, false, true, true, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_table);

        TableLayout tableLayout = (TableLayout)findViewById(R.id.tableLayout);
        tableLayout.removeAllViews();


        for(int i=0; i<questions.length;i++){
            String question  = questions[i];
            String answer = answers[i];
            boolean result = results[i];

            TableRow tableRow = new TableRow(this);

            final TextView columnQuestionView = new TextView(this);
            columnQuestionView.setText(String.format("%7s", question));
            final TextView columnAnswerView = new TextView(this);
            columnAnswerView.setText(String.format("%7s", answer));
            final TextView columnResultView = new TextView(this);
            columnResultView.setText(String.format("%7s", result));
            tableRow.addView(columnQuestionView);
            tableRow.addView(columnAnswerView);
            tableRow.addView(columnResultView);

            tableLayout.addView(tableRow);
        }

    }
}