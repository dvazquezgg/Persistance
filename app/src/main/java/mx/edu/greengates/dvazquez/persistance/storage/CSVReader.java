package mx.edu.greengates.dvazquez.persistance.storage;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import mx.edu.greengates.dvazquez.persistance.model.Question;
import mx.edu.greengates.dvazquez.persistance.model.Questions;

public class CSVReader {

    public final int ID = 0;
    public final int QUESTION = 1;
    public final int ANSWER = 2;
    public final int A1 = 3;
    public final int A2 = 4;
    public final int A3 = 5;
    public final int SUBJECT = 6;
    Context context;
    private String filename;
    private ArrayList<String[]> document;

    public CSVReader(Context context, String filename) {
        this.context = context;
        this.filename = filename;
        this.document = this.readCSV();

    }

    /**
     * This method reads the file assigned to the object and returns a String[] array
     * @return String[]
     */
    public ArrayList<String[]> readCSV(){
        ArrayList<String[]> document = new ArrayList<>();

        try {
            InputStream inputStream = context.getAssets().open(this.filename);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader csvReader = new BufferedReader(inputStreamReader);
            String row;
            int rowNum = 0;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                document.add(data);
            }
            csvReader.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
            System.out.println("Error" + ioe.getMessage());
        }
        return document;
    }

    public void printDocument(){
        for (String[] row: document){
            for(int col = 0; col < row.length; col++){
                System.out.print(row[col] + " | ");
            }
            System.out.println();
        }
    }

    public static List<String> getRewardsFromCSV(Context context){

        List<String> rewards = new ArrayList<>();

        AssetManager assetManager = context.getAssets();
        InputStream is = null;

        try {
            String REWARD_FILE = "rewards.csv";
            is = assetManager.open(REWARD_FILE);
            BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            String line = "";
            StringTokenizer st = null;
            while ((line = reader.readLine()) != null) {
                st = new StringTokenizer(line, ",");
                String reward = st.nextToken();
                rewards.add(reward);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rewards;
    }

    public static Questions getQuestionsFromCSV(Context context){
        int ID = 0;
        int QUESTION = 1;
        int A0 = 2;
        int A1 = 3;
        int A2 = 4;
        int A3 = 5;
        int SUBJECT = 6;

        ArrayList<Question> questions = new ArrayList<>();
        Questions questionsFromFile = new Questions(questions);

        AssetManager assetManager = context.getAssets();
        InputStream is = null;

        try {
            String QUIZ_FILE = "Questions.csv";
            is = assetManager.open(QUIZ_FILE);
            BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            String line = "";
            StringTokenizer st = null;
            int numLine = 0;
            while ((line = reader.readLine()) != null) {
                if(numLine >0 ){
                    st = new StringTokenizer(line, ",");

                    String id = st.nextToken();
                    String question= st.nextToken();
                    String answer= st.nextToken();
                    String a1 = st.nextToken();
                    String a2 = st.nextToken();
                    String a3 = st.nextToken();
                    String subject = st.nextToken();

                    Question quizObj = new Question( id, question, answer, a1, a2, a3,  subject);

                    questionsFromFile.addQuestion(quizObj);
                }
                numLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questionsFromFile;
    }

    public List<Question> createQuestions(){

        List<Question> questionList = new ArrayList<>();
        int rowNum = 0;
        for (String[] row: document){
            if ( rowNum > 0 ) {
                String id = row[ID];
                String question = row[QUESTION];
                String answer = row[ANSWER];
                String a1 = row[ANSWER];
                String a2 = row[ANSWER];
                String a3 = row[ANSWER];
                String subject = row[SUBJECT];
                Question questionObj = new Question(id, question, answer, a1, a2, a3, subject);
                questionList.add(questionObj);
            }
            rowNum ++;
        }
        return questionList;

    }


}
