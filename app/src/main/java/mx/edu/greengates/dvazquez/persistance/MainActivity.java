package mx.edu.greengates.dvazquez.persistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.edu.greengates.dvazquez.persistance.model.MyFile;
import mx.edu.greengates.dvazquez.persistance.model.Question;
import mx.edu.greengates.dvazquez.persistance.model.Questions;
import mx.edu.greengates.dvazquez.persistance.storage.CSVReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {


    private TextView tvQuestionText;
    ImageView imageView;

    private RadioGroup rgAnswers;
    private RadioButton radioAnswer1;
    private RadioButton radioAnswer2;
    private RadioButton radioAnswer3;
    private RadioButton radioAnswer4;

    Button btnNext;
    Button btnCheck;

    Questions questions;
    Map<String, int[]> questionMap;
    String[] questionWithImages;
    int currQuestionNum;
    int currTopicNum;
    Question currQuestion;
    String currTopic;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the Main Object for persistance (MyFileObject)
        final MyFile myFile = (MyFile) getApplicationContext();
        fillMyFile(myFile);

        //Read all Questions from the CSV File
        questions = CSVReader.getQuestionsFromCSV(this);

        List<String> topics = questions.getAllTopics();
        List<String> topicWithImage = pickTopics(questions, topics);

        questionWithImages = new String[topicWithImage.size()];
        topicWithImage.toArray(questionWithImages);

        questionMap = createQuestionMap(questionWithImages,questions);

        currTopicNum = 0;
        currQuestionNum = 0;
        score = 0;
        // At this point you have something like
        // questionWithImages = ["topic_1","topic_2","topic_3","topic_4"];
        // and questionMap = {
        //      topic_1:[R.drawable.topic_1_1,
        //        R.drawable.topic_1_2,
        //        R.drawable.topic_1_3,
        //        R.drawable.topic_1_4,
        //      ],
        //      topic_2:[R.drawable.topic_2_1,
        //        R.drawable.topic_2_2,
        //        R.drawable.topic_2_3,
        //        R.drawable.topic_2_4,
        //      ], etc, etc
        // }

        tvQuestionText = (TextView) findViewById(R.id.tvQuestionText);
        imageView = (ImageView) findViewById(R.id.imageView);
        rgAnswers = (RadioGroup) findViewById(R.id.rgAnswers);
        radioAnswer1 = (RadioButton) findViewById(R.id.rbAnswer1);
        radioAnswer2 = (RadioButton) findViewById(R.id.rbAnswer2);
        radioAnswer3 = (RadioButton) findViewById(R.id.rbAnswer3);
        radioAnswer4 = (RadioButton) findViewById(R.id.rbAnswer4);

        btnNext = (Button)  findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

        btnCheck = (Button)  findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(this);

        updateQuestionDisplay();
    }

    private void updateQuestionDisplay(){
        // Getting all questions for the current topic
        currTopic = questionWithImages[currTopicNum];
        List<Question> questionsByTopic = questions.getAllQuestionsByTopic(currTopic);

        currQuestion = questionsByTopic.get(currQuestionNum);
        int imageId = (questionMap.get(currTopic))[currQuestionNum];

        tvQuestionText.setText(currQuestion.getQuestion());
        tvQuestionText.setVisibility(View.GONE);
        imageView.setImageResource(imageId);
        imageView.setVisibility(View.VISIBLE);
        String[] options = currQuestion.getOptions();
        radioAnswer1.setText(options[0]);
        radioAnswer1.setChecked(false);
        radioAnswer2.setText(options[1]);
        radioAnswer2.setChecked(false);
        radioAnswer3.setText(options[2]);
        radioAnswer3.setChecked(false);
        radioAnswer4.setText(options[3]);
        radioAnswer4.setChecked(false);

    }

    /**
     * https://stackoverflow.com/questions/4427608/android-getting-resource-id-from-string
     * @param resourceName
     * @param c
     * @return
     */
    public static int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
    }

    private Map<String,int[]> createQuestionMap(String[] questionWithImages, Questions questions) {
        Map<String, int[]> questionMap = new HashMap<String, int[]>();
        for(String topic: questionWithImages){

            List<Question> selectedQuestions = questions.getAllQuestionsByTopic(topic);
            int[] imageIDS = new int[selectedQuestions.size()];
            int pos = 0;
            for(Question question: selectedQuestions){
                String questionText = question.getQuestion();
                String imageID = questionText.substring(questionText.lastIndexOf("img: ")+5);
                int resID = getId(imageID, R.drawable.class);
                imageIDS[pos] = resID;
                pos = pos + 1;
            }
            questionMap.put(topic,imageIDS);
        }
        return questionMap;
    }

    /**
     *  A private method to cherry pic questions
     * @param topics
     * @return
     */
    private List<String> pickTopics(Questions questions, List<String> topics) {
        List<String> imageTopics = new ArrayList<>();

        for(String topic: topics){
            List<Question> selectedQuestions = questions.getAllQuestionsByTopic(topic);
            for(Question question: selectedQuestions){
                if(question.getQuestion().indexOf("img:") >= 0 ){ // if question has "img:" as prefix
                    if(!imageTopics.contains(topic)) {  // if not yet in the list
                        imageTopics.add(topic);  // add to the list
                    }
                }
            }
        }
        return imageTopics;
    }

    private boolean checkAnswer(){
        String correct = currQuestion.getAnswer().trim();
        Log.println(Log.DEBUG,"answer", "Correct answer =[" + correct + "]");
        // get selected radio button from radioGroup
        int selectedId = rgAnswers.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        RadioButton radioButton = (RadioButton) findViewById(selectedId);

        String userAnswer = radioButton.getText().toString().trim();
        Log.println(Log.DEBUG,"answer", "User answer =[" + userAnswer + "]");
        return (correct.compareTo(userAnswer) == 0);

    }

    public void onClick(View v)
    {

        if (v == btnNext)
        {
            Intent myIntent = new Intent(MainActivity.this, NewActivity.class);
            myIntent.putExtra("username", "abc123"); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        }
        if (v == btnCheck) {
            if (checkAnswer()) {
                score++;
                Log.println(Log.DEBUG, "score", "Score =[" + score + "]");
            }

            currQuestionNum++;
            if (currQuestionNum == questionMap.get(currTopic).length){
                currQuestionNum = 0;
                currTopicNum++;
            }
            if ( currTopicNum < questionMap.size()){
                updateQuestionDisplay();
            }

        }
        return;

    }

    private void fillMyFile(MyFile myFile) {
        myFile.setName("Questions");
        myFile.setGrade(80);
    }


}