package com.filipkesteli.asyncbackgroundtask;

import android.app.Service;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by Filip on 19.5.2016..
 */
public class BackgroundAsyncTask extends AsyncTask<Integer, Integer, String> {
    //AsyncTask<Params, Progress, Result>

    //Definiramo objekt service tipa Service kako bismo handlali
    private Service service;

    //constructor za objekt service -> otvaramo se constructorom:
    public BackgroundAsyncTask(Service service) {
        this.service = service;
    }

    //Overrideamo metode -> doInBackground -> metoda u BACKGROUND THREAD-u
    //Promijenjiv broj parametara:
    //Integer params je PARAMS
    @Override
    protected String doInBackground(Integer... params) {
        int taskCount = params[0]; //prvi parametar iz params array-a
        for (int i = 0; i < taskCount; i++) {
            performLongTask(); //Ta ce metoda performati spavanje

            //idemo javiti Activityju koliki je progress:
            int progress = (int) ((i+1) / (float)taskCount * 100);
            publishProgress(progress); //overrideana metoda koja trigerrira progress
        }
        return "Servis je gotov"; //vracamo String
    }

    //Ova metoda ce unutar ove klase privatno performati spavanje
    private void performLongTask() {
        //Svaki put spavaj 2 sekunde -> recimo kad uzima podatke iz interneta:
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }

    /*Runs on the UI thread after publishProgress is invoked. The specified values are the values passed to publishProgress.*/
    //Integer values je PROGRESS
    @Override
    protected void onProgressUpdate(Integer... values) {
        //svaki put
        int progress = values[0];
        Toast.makeText(service, progress + "%", Toast.LENGTH_SHORT).show();
    }

    //String s je RESULT
    @Override
    protected void onPostExecute(String s) {
        //String s je ono sto metoda doInBackground vraca
        Toast.makeText(service, s, Toast.LENGTH_SHORT).show();
        service.stopSelf(); //STOP service -> zaustavlja servis ako radi u pozadini!!
    }
}


