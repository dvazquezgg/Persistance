package mx.edu.greengates.dvazquez.persistance.model;

import android.app.Application;

import java.io.Serializable;

/**
 * This class extends Application to be able to be shared across all Activities in the App
 * It also implements Serializable to work with the ObjectInputStream auxiliar class
 * The properties only reflect what you need  for the class to store
 *
 */
public class MyFile extends Application implements Serializable {

    String name;
    int grade;

    public MyFile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
