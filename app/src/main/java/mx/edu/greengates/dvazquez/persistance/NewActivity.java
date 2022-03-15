package mx.edu.greengates.dvazquez.persistance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import mx.edu.greengates.dvazquez.persistance.model.MyFile;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        final MyFile myFile = (MyFile) getApplicationContext();

        Log.d("NewActivity", myFile.getName());
        Log.d("NewActivity", String.valueOf(myFile.getGrade()));

    }
}