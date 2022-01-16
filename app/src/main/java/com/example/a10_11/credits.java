package com.example.a10_11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class credits extends AppCompatActivity {

    Intent si;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        tv = findViewById(R.id.text1);
        tv.setText("Author : Noa Shetrit\n\n" +
                "Description: hiii :)\n" +
                "the application gives you 3 options:\n" +
                "1 -  save the text in file\n" +
                "2 - reset text file\n" +
                "3 - exit and save the text in file\n" +
                "this program work with Internal Files.\n" +
                "Have a gooood day ;))) " );
        tv.setTextSize(20);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        String oper=item.getTitle().toString();
        if(oper.equals("Main ->>")){
            si = new Intent(this,MainActivity.class);
            startActivity(si);
        }

        return true;
    }
}