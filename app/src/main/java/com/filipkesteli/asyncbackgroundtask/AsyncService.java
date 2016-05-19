package com.filipkesteli.asyncbackgroundtask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class AsyncService extends Service {

    //konstanta -> broj taskova:
    private static final int NUMBER_OF_TASKS = 5;

    //Constructor po defaultu:
    public AsyncService() {
    }

    /*Base interface for a remotable object, the core part of a lightweight remote procedure call mechanism designed for high performance when performing in-process and cross-process calls. This interface describes the abstract protocol for interacting with a remotable object. Do not implement this interface directly, instead extend from Binder.*/
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /*Called by the system every time a client explicitly starts the service
    by calling Context.startService, providing the arguments it supplied
    and a unique integer token representing the start request.
    Do not call this method directly.*/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Kad se starta servis, dajem mu NUMBER_OF_TASKS zadatka da obavi:
        //dajem mu sebe kao servis
        BackgroundAsyncTask backgroundAsyncTask = new BackgroundAsyncTask(this);
        backgroundAsyncTask.execute(NUMBER_OF_TASKS);
        return START_STICKY;
    }

    //onDestroy metoda koja ubija servis
    @Override
    public void onDestroy() {
        Toast.makeText(AsyncService.this, "onDestroy je pozvan!!!!", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
