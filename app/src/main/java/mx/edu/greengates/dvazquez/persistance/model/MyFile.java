package mx.edu.greengates.dvazquez.persistance.model;

import android.app.Application;

import java.io.Serializable;

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
