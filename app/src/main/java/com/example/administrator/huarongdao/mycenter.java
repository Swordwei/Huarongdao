package com.example.administrator.huarongdao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class mycenter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycenter);
    }

    public void game(View view) {
        Intent intent = new Intent(mycenter.this, MainActivity.class);
        startActivity(intent);
    }
}
