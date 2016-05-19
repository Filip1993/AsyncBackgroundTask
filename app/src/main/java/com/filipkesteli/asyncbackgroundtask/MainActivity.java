package com.filipkesteli.asyncbackgroundtask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnStartService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        setupListeners();
    }

    private void initWidgets() {
        btnStartService = (Button) findViewById(R.id.btnStartService);
    }

    private void setupListeners() {
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stvaramo Intent objekt kojim pozivamo Service:
                Intent intent = new Intent(this, AsyncService.class);
                startService(intent);
            }
        });
    }


}
