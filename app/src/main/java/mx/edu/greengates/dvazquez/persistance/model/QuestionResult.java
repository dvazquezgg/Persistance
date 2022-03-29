package mx.edu.greengates.dvazquez.persistance.model;

public class QuestionResult extends Question{

    boolean result;
    int selectedAnswer;

    public QuestionResult(String id, String question, String a0, String a1, String a2, String a3, String subject) {
        super(id, question, a0, a1, a2, a3, subject);
        this.result = false;
        this.selectedAnswer = 0;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(int selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
