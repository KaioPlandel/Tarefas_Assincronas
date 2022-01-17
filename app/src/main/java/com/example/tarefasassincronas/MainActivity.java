package com.example.tarefasassincronas;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.number.IntegerWidth;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonIniciar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        buttonIniciar = findViewById(R.id.buttonIniciar);

        buttonIniciar.setOnClickListener(v -> iniciarAsyncTask());


    }

    public void iniciarAsyncTask(){

        MyAsyncTask task = new MyAsyncTask();
        task.execute(10);

    }

    /* 1 parametro a ser passado pela classe
    .  2 tipo de valor que vai ser utilizado para o progresso de tarefa
    .  3 retorno apos tarefa finalizada

     */

    class MyAsyncTask extends AsyncTask<Integer, Integer, String>{





        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            int numero = integers[0];

            for(int i =0; i <= numero; i++){
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return "Finalizado";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
        }



    }
}