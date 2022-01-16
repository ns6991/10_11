package com.example.a10_11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author Noa Shetrit ns6991@bs.amalnet.k12.il
 * @version 1.1
 * @since 16/1/2022
 * this program works with files, saving a text in file, read from it and write to it.
 */

public class MainActivity extends AppCompatActivity {
    final String fileName = "textSave.txt";
    boolean saved = false; //check if the text saved in file
    EditText et;
    TextView tv;

    BufferedWriter bw;
    OutputStreamWriter osw;
    FileOutputStream fos;

    FileInputStream fis;
    InputStreamReader isr;
    BufferedReader br;

    Intent si;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textt);
        et = (EditText) findViewById(R.id.et);
        si = new Intent(this, credits.class);

    }

    /**
     * The function occurs when the program loads
     * and displays the information stored in the file
     */
    @Override
    public void onStart() {
        super.onStart();
        tv.setText(read(fileName));
    }


    /**
     * The function occurs when the program closed or paused
     * and save the information the user wrote in the file
     */
    @Override
    public void onStop() {
        super.onStop();
        if(!saved) saveText();

    }

    /**
     * saves the text in edit text to the file
     */
    private void saveText() {
        write(fileName,read(fileName)+et.getText().toString());
        tv.setText(read(fileName));
        saved = true;
    }

    /**
     * The method saves the user input to the file.
     *
     * @param view
     */
    public void save(View view) {
        saveText();

    }

    /**
     * The method clears the file.
     *
     * @param view
     */
    public void reset(View view) {
        write(fileName,"");
        tv.setText(read(fileName));
    }

    /**
     * The method saves the current text and exits the app
     *
     * @param view
     */
    public void close(View view) {
        if(!saved) saveText();
        finish();

    }

    /**
     * write to the file.
     *
     * @param fileN name of file
     * @param textToWrite the text that will be written in the file
     */
    public void write(String fileN, String textToWrite){
        try {
            fos = openFileOutput(fileN,MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            bw.write(textToWrite);
            bw.close();


        } catch (Exception e) {
            String s = "txt open file failed";
            Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
        }
    }

    /**
     * The method reads a file.
     *
     * @param fileN the name of the file
     * @return The contents of the file
     */
    public String read(String fileN ){
        fis= null;
        try {
            fis = openFileInput(fileN);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String line = br.readLine();
            while (line != null) {
                sb.append(line+'\n');
                line = br.readLine();
            }
            String strrd = sb.toString();
            isr.close();
            return strrd;
        } catch (Exception e) {
            String s = "txt open file failed";
            Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
        }
        return "";

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        String oper=item.getTitle().toString();
        if(oper.equals("Credits ->>")){
            si = new Intent(this,credits.class);
            startActivity(si);
        }

        return true;
    }
}