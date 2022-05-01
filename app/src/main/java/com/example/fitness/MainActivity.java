package com.example.fitness;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button back;
    private DBHelper DB;
    private RecyclerView workoutRecView;
    ArrayList<workoutmodel> list;

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

    private boolean biceplock = false;
    private boolean abslock = false;
    private boolean forearmlock = false;
    private boolean trapslock = false;
    private boolean chestlock = false;
    private boolean shoulderlock = false;
    private boolean quadlock = false;
    private boolean calflock = false;

    Date date = new Date();
    SimpleDateFormat weekday = new SimpleDateFormat("EEEE");
    SimpleDateFormat month = new SimpleDateFormat("MMM dd");

    workoutRecViewAdapter adapter;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bicepBtnL:
            case R.id.bicepBtnR:
                if(biceplock == false){
                    changeVisible(bicep, "Bicep");
                    break;
                }
                Toast.makeText(this, "Hold to delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.forearmBtnL:
            case R.id.forearmBtnR:
                if(forearmlock == false){
                    changeVisible(forearm, "Forearm");
                    break;
                }
                Toast.makeText(this, "Hold to delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.shoulderBtnL:
            case R.id.shoulderBtnR:
                if(shoulderlock == false){
                    changeVisible(shoulder, "Shoulder");
                    break;
                }
                Toast.makeText(this, "Hold to delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.quadBtnL:
            case R.id.quadBtnR:
                if(quadlock == false){
                    changeVisible(quad, "Quad");
                    break;
                }
                Toast.makeText(this, "Hold to delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.calf1BtnL:
            case R.id.calf1BtnR:
                if(calflock == false){
                    changeVisible(calf, "Calf");
                    break;
                }
                Toast.makeText(this, "Hold to delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.trapsBtn:
                if(trapslock == false){
                    changeVisible(traps, "Upper Traps");
                    break;
                }
                Toast.makeText(this, "Hold to delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chestBtn:
                if(chestlock == false){
                    changeVisible(chest, "Chest");
                    break;
                }
                Toast.makeText(this, "Hold to delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.absBtn:
                if(abslock == false){
                    changeVisible(abs, "Abs");
                    break;
                }
                Toast.makeText(this, "Hold to delete", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //TODO: add hold to delete function

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DBHelper(this);
        back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBackActivity();
            }
        });

        workoutRecView = findViewById(R.id.workoutRecView);

        list = new ArrayList<>();
        loadcard();

        adapter = new workoutRecViewAdapter();
        adapter.setList(list);

        workoutRecView.setAdapter(adapter);
        workoutRecView.setLayoutManager(new LinearLayoutManager(this));


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

        render();
    }


    public void openBackActivity(){
        Intent intent = new Intent(this, MainActivity2_backpage.class);
        startActivity(intent);
    }

    private void changeVisible(View view, String name) {

        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.INVISIBLE);
            Boolean checkdeletedata = DB.deletedata(name, month.format(date));
            if (checkdeletedata == false){
                Toast.makeText(this, "Failed to delete", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            }
            updatecard(null);
            return;
        }

        view.setVisibility(View.VISIBLE);
        Boolean checkinsertdata = DB.insertdata(name, weekday.format(date), month.format(date));
        if (checkinsertdata == false){
            Toast.makeText(this, "Failed to insert", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
            updatecard(name);
        }
    }

    public void updatecard(String name){
        if (name != null){
            list.add(0, new workoutmodel(name, weekday.format(date), month.format(date)));
            adapter.notifyDataSetChanged();
            return;
        }
        list.clear();
        Cursor result = DB.getdata();
        while (result.moveToNext()){
            list.add(new workoutmodel(result.getString(0), result.getString(1), result.getString(2)));
        }
        adapter.notifyDataSetChanged();
    }

    public void loadcard(){
        list.clear();
        Cursor result = DB.getdata();
        while (result.moveToNext()){
            list.add(new workoutmodel(result.getString(0), result.getString(1), result.getString(2)));
        }
    }

    public void render(){
        LocalDate today = LocalDate.now();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate yesyesterday = LocalDate.now().minusDays(2);
        String day = today.format(DateTimeFormatter.ofPattern("MMM dd"));
        String daybefore = yesterday.format(DateTimeFormatter.ofPattern("MMM dd"));
        String daydaybefore = yesyesterday.format(DateTimeFormatter.ofPattern("MMM dd"));
        Cursor result = DB.getNameFromDate(day, daybefore, daydaybefore);

        while (result.moveToNext()){
            switch (result.getString(0)){
                case "Bicep":
                    changeVisible(bicep, "Bicep");
                    biceplock = true; //locks button if its already in data base
                    break;
                case "Forearm":
                    changeVisible(forearm, "Forearm");
                    forearmlock = true;
                    break;
                case "Shoulder":
                    changeVisible(shoulder, "Shoulder");
                    shoulderlock = true;
                    break;
                case "Quad":
                    changeVisible(quad, "Quad");
                    quadlock = true;
                    break;
                case "Calf":
                    changeVisible(calf, "Calf");
                    calflock = true;
                    break;
                case "Upper Traps":
                    changeVisible(traps, "Upper Traps");
                    trapslock = true;
                    break;
                case "Chest":
                    changeVisible(chest, "Chest");
                    chestlock = true;
                    break;
                case "Abs":
                    changeVisible(abs, "Abs");
                    abslock = true;
                    break;
            }
        }

    }
}