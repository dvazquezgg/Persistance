package mx.edu.greengates.dvazquez.persistance.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question {

    private String id;
    private String question;
    private String answer;
    private String a0;
    private String a1;
    private String a2;
    private String a3;
    private String subject;

    public Question(String id, String question, String a0, String a1, String a2, String a3, String subject) {
        this.id = id;
        this.question = question;
        this.answer = a0;
        this.a0 = a0;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getSubject() {
        return subject;
    }

    public String[] getOptions(){
        String[] options = new String[4];
        options[0] = a0;
        options[1] = a1;
        options[2] = a2;
        options[3] = a3;
        return shuffleOptions(options);
    }

    /**
     * https://www.journaldev.com/32661/shuffle-array-java
     * @param options
     * @return
     */
    private String[] shuffleOptions(String[] options){

        List<String> strList = Arrays.asList(options);
        Collections.shuffle(strList);
        strList.toArray(options);

        return options;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
