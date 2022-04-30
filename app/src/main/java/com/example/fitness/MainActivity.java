package com.example.fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button back;

    //all images
    private ImageView bicep;
    private ImageView abs;
    private ImageView traps;
    private ImageView chest;
    private ImageView shoulder;
    private ImageView quad;
    private ImageView calf;
    private ImageView forearm;

    //all buttons
    private Button bicepBtnL;
    private Button bicepBtnR;
    private Button absBtn;
    private Button forearmBtnL;
    private Button forearmBtnR;
    private Button trapsBtn;
    private Button chestBtn;
    private Button shoulderBtnL;
    private Button shoulderBtnR;
    private Button quadBtnL;
    private Button quadBtnR;
    private Button calfBtnL;
    private Button calfBtnR;


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bicepBtnL:
            case R.id.bicepBtnR:
                changeVisible(bicep);
                break;
            case R.id.forearmBtnL:
            case R.id.forearmBtnR:
                changeVisible(forearm);
                break;
            case R.id.shoulderBtnL:
            case R.id.shoulderBtnR:
                changeVisible(shoulder);
                break;
            case R.id.quadBtnL:
            case R.id.quadBtnR:
                changeVisible(quad);
                break;
            case R.id.calf1BtnL:
            case R.id.calf1BtnR:
                changeVisible(calf);
                break;
            case R.id.trapsBtn:
                changeVisible(traps);
                break;
            case R.id.chestBtn:
                changeVisible(chest);
                break;
            case R.id.absBtn:
                changeVisible(abs);
                break;
        }
    }

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

        //initialise all images
        bicep = findViewById(R.id.bicep);
        shoulder = findViewById(R.id.shoulder1);
        forearm = findViewById(R.id.forearm1);
        chest = findViewById(R.id.chest);
        abs = findViewById(R.id.abs);
        quad = findViewById(R.id.quad);
        calf = findViewById(R.id.calf1);
        traps = findViewById(R.id.traps1);

        //initialise all buttons
        bicepBtnL = findViewById(R.id.bicepBtnL);
        bicepBtnL.setOnClickListener(this);
        bicepBtnR = findViewById(R.id.bicepBtnR);
        bicepBtnR.setOnClickListener(this);

        shoulderBtnL = findViewById(R.id.shoulderBtnL);
        shoulderBtnL.setOnClickListener(this);
        shoulderBtnR = findViewById(R.id.shoulderBtnR);
        shoulderBtnR.setOnClickListener(this);

        chestBtn = findViewById(R.id.chestBtn);
        chestBtn.setOnClickListener(this);

        absBtn = findViewById(R.id.absBtn);
        absBtn.setOnClickListener(this);

        quadBtnL = findViewById(R.id.quadBtnL);
        quadBtnL.setOnClickListener(this);
        quadBtnR = findViewById(R.id.quadBtnR);
        quadBtnR.setOnClickListener(this);

        forearmBtnL = findViewById(R.id.forearmBtnL);
        forearmBtnL.setOnClickListener(this);
        forearmBtnR = findViewById(R.id.forearmBtnR);
        forearmBtnR.setOnClickListener(this);

        calfBtnL = findViewById(R.id.calf1BtnL);
        calfBtnL.setOnClickListener(this);
        calfBtnR = findViewById(R.id.calf1BtnR);
        calfBtnR.setOnClickListener(this);

        trapsBtn = findViewById(R.id.trapsBtn);
        trapsBtn.setOnClickListener(this);
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