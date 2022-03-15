package mx.edu.greengates.dvazquez.persistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mx.edu.greengates.dvazquez.persistance.model.MyFile;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyFile myFile = (MyFile) getApplicationContext();
        fillMyFile(myFile);

        btnNext = (Button)  findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
    }


    public void onClick(View v)
    {

        if (v == btnNext)
        {
            Intent myIntent = new Intent(MainActivity.this, NewActivity.class);
            myIntent.putExtra("username", "abc123"); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        }
        return;

    }
    private void fillMyFile(MyFile myFile) {
        myFile.setName("Liu");
        myFile.setGrade(80);
    }


}