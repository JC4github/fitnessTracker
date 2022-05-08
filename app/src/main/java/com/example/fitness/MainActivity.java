package com.example.fitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button back;
    private Button front;
    private DBHelper DB;
    private RecyclerView workoutRecView;
    ArrayList<workoutmodel> list;

    private RelativeLayout groupA;
    private RelativeLayout groupB;

    //all images
    private ImageView bicep;
    private ImageView abs;
    private ImageView traps;
    private ImageView chest;
    private ImageView shoulder;
    private ImageView quad;
    private ImageView calf;
    private ImageView forearm;
    private ImageView tricep;
    private ImageView forearm2;
    private ImageView glutes;
    private ImageView shoulder2;
    private ImageView hamstring;
    private ImageView calf2;
    private ImageView lowerback;
    private ImageView lats;
    private ImageView traps2;
    private ImageView traps3;

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
    private Button tricepLBtn;
    private Button tricepRBtn;
    private Button forearmLBtn;
    private Button forearmRBtn;
    private Button shoulderLBtn;
    private Button shoulderRBtn;
    private Button traps2Btn;
    private Button traps3Btn;
    private Button glutesBtn;
    private Button hamstringBtn;
    private Button calf2LBtn;
    private Button calf2RBtn;
    private Button lowerBackBtn;
    private Button latsLBtn;
    private Button latsRBtn;

    private boolean biceplock = false;
    private boolean abslock = false;
    private boolean forearmlock = false;
    private boolean trapslock = false;
    private boolean chestlock = false;
    private boolean shoulderlock = false;
    private boolean quadlock = false;
    private boolean calflock = false;
    private boolean triceplock = false;
    private boolean latslock = false;
    private boolean hamstringlock = false;
    private boolean gluteslock = false;
    private boolean traps3lock = false;
    private boolean lowerbacklock = false;

    LocalDate today;
    String todayString;
    String weekdayString;

    workoutRecViewAdapter adapter;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bicepBtnL:
            case R.id.bicepBtnR:
                if (biceplock == false) {
                    changeVisibleAndInsert(bicep, "Biceps");
                    break;
                }
                break;
            case R.id.forearmBtnL:
            case R.id.forearmBtnR:
            case R.id.forearm2LBtn:
            case R.id.forearm2RBtn:
                if (forearmlock == false) {
                    changeVisibleAndInsert(forearm, "Forearms");
                    changeVisible(forearm2, "Forearms");
                    break;
                }
                break;
            case R.id.shoulderBtnL:
            case R.id.shoulderBtnR:
            case R.id.shoulder2LBtn:
            case R.id.shoulder2RBtn:
                if (shoulderlock == false) {
                    changeVisibleAndInsert(shoulder2, "Shoulders");
                    changeVisible(shoulder, "Shoulders");
                    break;
                }
                break;
            case R.id.quadBtnL:
            case R.id.quadBtnR:
                if (quadlock == false) {
                    changeVisibleAndInsert(quad, "Quads");
                    break;
                }
                break;
            case R.id.calf1BtnL:
            case R.id.calf1BtnR:
            case R.id.calf2BtnL:
            case R.id.calf2BtnR:
                if (calflock == false) {
                    changeVisibleAndInsert(calf, "Calfs");
                    changeVisible(calf2, "Calfs");
                    break;
                }
                break;
            case R.id.trapsBtn:
            case R.id.traps2Btn:
                if (trapslock == false) {
                    changeVisibleAndInsert(traps2, "Upper Traps");
                    changeVisible(traps, "Upper Traps");
                    break;
                }
                break;
            case R.id.chestBtn:
                if (chestlock == false) {
                    changeVisibleAndInsert(chest, "Chest");
                    break;
                }
                break;
            case R.id.absBtn:
                if (abslock == false) {
                    changeVisibleAndInsert(abs, "Abs");
                    break;
                }
                break;
            case R.id.tricepLBtn:
            case R.id.tricepRBtn:
                if (triceplock == false) {
                    changeVisibleAndInsert(tricep, "Triceps");
                    break;
                }
                break;
            case R.id.latsLBtn:
            case R.id.latsRBtn:
                if (latslock == false) {
                    changeVisibleAndInsert(lats, "Lats");
                    break;
                }
                break;
            case R.id.glutesBtn:
                if (gluteslock == false) {
                    changeVisibleAndInsert(glutes, "Glutes");
                    break;
                }
                break;
            case R.id.lowerBackBtn:
                if (lowerbacklock == false) {
                    changeVisibleAndInsert(lowerback, "Lower Back");
                    break;
                }
                break;
            case R.id.traps3Btn:
                if (traps3lock == false) {
                    changeVisibleAndInsert(traps3, "Mid Traps");
                    break;
                }
                break;
            case R.id.hamstringBtn:
                if (hamstringlock == false) {
                    changeVisibleAndInsert(hamstring, "Hamstrings");
                    break;
                }
                break;
        }
    }

    //TODO: add hold to delete function

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        today = LocalDate.now();
        todayString = today.format(DateTimeFormatter.ofPattern("MMM dd"));
        weekdayString = today.format(DateTimeFormatter.ofPattern("EEEE"));

        groupA = findViewById(R.id.groupA);
        groupB = findViewById(R.id.groupB);

        DB = new DBHelper(this);

        back = findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groupA.setVisibility(groupA.INVISIBLE);
                groupB.setVisibility(groupB.VISIBLE);
            }
        });

        front = findViewById(R.id.frontBtn);
        front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groupB.setVisibility(groupB.INVISIBLE);
                groupA.setVisibility(groupA.VISIBLE);
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
        tricep = findViewById(R.id.tricep);
        forearm2 = findViewById(R.id.forearm2);
        shoulder2 = findViewById(R.id.shoulder2);
        traps2 = findViewById(R.id.traps2);
        traps3 = findViewById(R.id.traps3);
        lats = findViewById(R.id.lats);
        glutes = findViewById(R.id.glutes);
        calf2 = findViewById(R.id.calf2);
        lowerback = findViewById(R.id.lowerBack);
        hamstring = findViewById(R.id.hamstring);

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

        tricepLBtn = findViewById(R.id.tricepLBtn);
        tricepLBtn.setOnClickListener(this);
        tricepRBtn = findViewById(R.id.tricepRBtn);
        tricepRBtn.setOnClickListener(this);

        forearmLBtn = findViewById(R.id.forearm2LBtn);
        forearmLBtn.setOnClickListener(this);
        forearmRBtn = findViewById(R.id.forearm2RBtn);
        forearmRBtn.setOnClickListener(this);

        shoulderLBtn = findViewById(R.id.shoulder2LBtn);
        shoulderLBtn.setOnClickListener(this);
        shoulderRBtn = findViewById(R.id.shoulder2RBtn);
        shoulderRBtn.setOnClickListener(this);

        traps2Btn = findViewById(R.id.traps2Btn);
        traps2Btn.setOnClickListener(this);
        traps3Btn = findViewById(R.id.traps3Btn);
        traps3Btn.setOnClickListener(this);

        glutesBtn = findViewById(R.id.glutesBtn);
        glutesBtn.setOnClickListener(this);
        hamstringBtn = findViewById(R.id.hamstringBtn);
        hamstringBtn.setOnClickListener(this);
        calf2LBtn = findViewById(R.id.calf2BtnL);
        calf2LBtn.setOnClickListener(this);
        calf2RBtn = findViewById(R.id.calf2BtnR);
        calf2RBtn.setOnClickListener(this);
        lowerBackBtn = findViewById(R.id.lowerBackBtn);
        lowerBackBtn.setOnClickListener(this);
        latsLBtn = findViewById(R.id.latsLBtn);
        latsLBtn.setOnClickListener(this);
        latsRBtn = findViewById(R.id.latsRBtn);
        latsRBtn.setOnClickListener(this);

        render();
    }

    public void changeVisible(View view, String name) {

        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.INVISIBLE);
            return;
        }

        view.setVisibility(View.VISIBLE);
    }

    public void changeVisibleAndInsert(View view, String name) {

        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.INVISIBLE);
            DB.deletedata(name, todayString);
            updatecard(null);
            return;
        }

        view.setVisibility(View.VISIBLE);
        Boolean checkinsertdata = DB.insertdata(name, weekdayString, todayString);
        Toast.makeText(this, "name is: " + name, Toast.LENGTH_SHORT).show();
        if (checkinsertdata == true) {
            updatecard(name);
        }
    }

    public void updatecard(String name) {
        if (name != null) {
            list.add(0, new workoutmodel(name, weekdayString, todayString));
            adapter.notifyDataSetChanged();
            return;
        }
        list.clear();
        Cursor result = DB.getdata();
        while (result.moveToNext()) {
            list.add(0, new workoutmodel(result.getString(0), result.getString(1), result.getString(2)));
        }
        adapter.notifyDataSetChanged();
    }

    public void loadcard() {
        list.clear();
        Cursor result = DB.getdata();
        while (result.moveToNext()) {
            list.add(0, new workoutmodel(result.getString(0), result.getString(1), result.getString(2)));
        }
    }

    public void render() {
        //find all entry within 3 days and render them
        String yesterday = today.minusDays(1).format(DateTimeFormatter.ofPattern("MMM dd"));
        String beforeYesterday = today.minusDays(2).format(DateTimeFormatter.ofPattern("MMM dd"));
        Cursor result = DB.getNameFromDate(todayString, yesterday, beforeYesterday);

        while (result.moveToNext()) {
            switch (result.getString(0)) {
                case "Biceps":
                    changeVisible(bicep, "Biceps");
                    biceplock = true; //locks button if its already in data base
                    break;
                case "Forearms":
                    changeVisible(forearm, "Forearms");
                    changeVisible(forearm2, "Forearms");
                    forearmlock = true;
                    break;
                case "Shoulders":
                    changeVisible(shoulder, "Shoulders");
                    changeVisible(shoulder2, "Shoulders");
                    shoulderlock = true;
                    break;
                case "Quads":
                    changeVisible(quad, "Quads");
                    quadlock = true;
                    break;
                case "Calfs":
                    changeVisible(calf, "Calfs");
                    changeVisible(calf2, "Calfs");
                    calflock = true;
                    break;
                case "Upper Traps":
                    changeVisible(traps, "Upper Traps");
                    changeVisible(traps2, "Upper Traps");
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
                case "Triceps":
                    changeVisible(tricep, "Triceps");
                    triceplock = true; //locks button if its already in data base
                    break;
                case "Hamstrings":
                    changeVisible(hamstring, "Hamstrings");
                    hamstringlock = true;
                    break;
                case "Glutes":
                    changeVisible(glutes, "Glutes");
                    gluteslock = true;
                    break;
                case "Mid Traps":
                    changeVisible(traps3, "Mid Traps");
                    traps3lock = true;
                    break;
                case "Lower Back":
                    changeVisible(lowerback, "Lower Back");
                    lowerbacklock = true;
                    break;
                case "Lats":
                    changeVisible(lats, "Lats");
                    latslock = true;
                    break;
            }
        }

    }
}