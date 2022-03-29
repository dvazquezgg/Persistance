package mx.edu.greengates.dvazquez.persistance.model;

import java.util.ArrayList;
import java.util.List;

public class Questions {

    private final List<Question> questions;

    public Questions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }
    public Question getQuestion(String identifier) {
        Question selectedQuestion = null;  // initial Value
        for(Question question: questions){ // Simple linear search
            if(question.getQuestion().compareTo(identifier) == 0){ // comparison
                selectedQuestion = question;
                break;
            }
        }
        return selectedQuestion;
    }

    public void addQuestion(Question question){
        questions.add(question);
    }

    public List<String> getAllTopics(){
        List<String> topics = new ArrayList<>();
        for( Question question: questions){
            if(!topics.contains(question.getId())){ // If we dont have the topic added to the list
                topics.add(question.getId());
            }
        }
        return topics;
    }

    public List<Question> getAllQuestionsByTopic(String topic){
        List<Question> topicQuestions = new ArrayList<>();
        for( Question question: questions){
            if((question.getId().compareTo(topic)==0)){
                topicQuestions.add(question);
            }
        }
        return topicQuestions;
    }
}
