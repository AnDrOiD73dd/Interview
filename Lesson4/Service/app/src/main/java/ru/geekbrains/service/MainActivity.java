package ru.geekbrains.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonStartService = (Button) findViewById(R.id.buttonStartService);
        final EditText numberView = (EditText) findViewById(R.id.et_number);
        buttonStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = numberView.getText().toString();
                Intent intent = new Intent(MainActivity.this, MainService.class);
                if (!TextUtils.isEmpty(number)) {
                    intent.putExtra(MainService.KEY_PARAM, number);
                }
                startService(intent);
            }
        });
    }
}
