package com.example.fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button back;
    private ImageButton bicep;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBackActivity();
            }
        });

        bicep = findViewById(R.id.bicepBtn);
        bicep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
            }
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeVisible(bicep);
            }
        });
    }
    public void openBackActivity(){
        Intent intent = new Intent(this, MainActivity2_backpage.class);
        startActivity(intent);
    }

    private void changeVisible(View view) {
        if(view.getVisibility()==View.VISIBLE)
        {
            view.setVisibility(View.INVISIBLE);
            return;
        }

        view.setVisibility(View.VISIBLE);
    }
}