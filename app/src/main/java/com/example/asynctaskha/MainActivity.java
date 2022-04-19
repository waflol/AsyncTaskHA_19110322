package com.example.asynctaskha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView percent;
    private TextView textView;

    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        percent = findViewById(R.id.percent);
        textView = findViewById(R.id.status);

        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestAsyncTask exampleAsyncTask = new TestAsyncTask();
                exampleAsyncTask.execute(60);
            }
        });
    }

    private class TestAsyncTask extends AsyncTask<Integer, Integer, String> {


        @Override
        protected String doInBackground(Integer... integers) {
            for (int i = 0; i < integers[0]; i++){
                publishProgress(i *100 / integers[0]);
                try{
                    Thread.sleep(1000);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            return "Finished";
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            textView.setText("Working...");
            percent.setText("DONE");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textView.setText("Napping...");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            percent.setText(Integer.toString(values[0]) + "%");

        }
    }
}