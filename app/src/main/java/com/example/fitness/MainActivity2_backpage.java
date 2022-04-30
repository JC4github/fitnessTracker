package com.example.fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2_backpage extends AppCompatActivity {

    private Button front;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_backpage);
        front = findViewById(R.id.frontBtn);
        front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFrontActivity();
            }
        });
    }

    public void openFrontActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}