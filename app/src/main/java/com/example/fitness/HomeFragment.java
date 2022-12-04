package com.example.fitness;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.IDNA;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    //TODO: add hold button for info

    Context thiscontext;

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

    Date today;
    String todayString;
    String weekdayString;
    String yearString;

    workoutRecViewAdapter adapter;

    //used for web view
    Intent i;

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
                    changeVisibleAndInsert(calf, "Calves");
                    changeVisible(calf2, "Calves");
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

    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.bicepBtnL:
            case R.id.bicepBtnR:
                i.putExtra(WebViewActivity.WEBSITE_ADDRESS, "https://musclewiki.com/Exercises/Male/Biceps");
                startActivity(i);
                return true;
            case R.id.forearmBtnL:
            case R.id.forearmBtnR:
            case R.id.forearm2LBtn:
            case R.id.forearm2RBtn:
                i.putExtra(WebViewActivity.WEBSITE_ADDRESS, "https://musclewiki.com/Exercises/Male/Forearms");
                startActivity(i);
                return true;
            case R.id.shoulderBtnL:
            case R.id.shoulderBtnR:
            case R.id.shoulder2LBtn:
            case R.id.shoulder2RBtn:
                i.putExtra(WebViewActivity.WEBSITE_ADDRESS, "https://musclewiki.com/Exercises/Male/Shoulders");
                startActivity(i);
                return true;
            case R.id.quadBtnL:
            case R.id.quadBtnR:
                i.putExtra(WebViewActivity.WEBSITE_ADDRESS, "https://musclewiki.com/Exercises/Male/Quads");
                startActivity(i);
                return true;
            case R.id.calf1BtnL:
            case R.id.calf1BtnR:
            case R.id.calf2BtnL:
            case R.id.calf2BtnR:
                i.putExtra(WebViewActivity.WEBSITE_ADDRESS, "https://musclewiki.com/Exercises/Male/Calves");
                startActivity(i);
                return true;
            case R.id.trapsBtn:
            case R.id.traps2Btn:
                i.putExtra(WebViewActivity.WEBSITE_ADDRESS, "https://musclewiki.com/Exercises/Male/Traps");
                startActivity(i);
                return true;
            case R.id.chestBtn:
                i.putExtra(WebViewActivity.WEBSITE_ADDRESS, "https://musclewiki.com/Exercises/Male/Chest");
                startActivity(i);
                return true;
            case R.id.absBtn:
                i.putExtra(WebViewActivity.WEBSITE_ADDRESS, "https://musclewiki.com/Exercises/Male/Abdominals");
                startActivity(i);
                return true;
            case R.id.tricepLBtn:
            case R.id.tricepRBtn:
                i.putExtra(WebViewActivity.WEBSITE_ADDRESS, "https://musclewiki.com/Exercises/Male/Triceps");
                startActivity(i);
                return true;
            case R.id.latsLBtn:
            case R.id.latsRBtn:
                i.putExtra(WebViewActivity.WEBSITE_ADDRESS, "https://musclewiki.com/Exercises/Male/Lats");
                startActivity(i);
                return true;
            case R.id.glutesBtn:
                i.putExtra(WebViewActivity.WEBSITE_ADDRESS, "https://musclewiki.com/Exercises/Male/Glutes");
                startActivity(i);
                return true;
            case R.id.lowerBackBtn:
                i.putExtra(WebViewActivity.WEBSITE_ADDRESS, "https://musclewiki.com/Exercises/Male/Lowerback");
                startActivity(i);
                return true;
            case R.id.traps3Btn:
                i.putExtra(WebViewActivity.WEBSITE_ADDRESS, "https://musclewiki.com/Exercises/Male/Traps_middle");
                startActivity(i);
                return true;
            case R.id.hamstringBtn:
                i.putExtra(WebViewActivity.WEBSITE_ADDRESS, "https://musclewiki.com/Exercises/Male/Hamstrings");
                startActivity(i);
                return true;
        }
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thiscontext = container.getContext();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        today = Calendar.getInstance().getTime();
        SimpleDateFormat month = new SimpleDateFormat("MMM dd");
        SimpleDateFormat weekday = new SimpleDateFormat("EEEE");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");

        todayString = month.format(today);
        weekdayString = weekday.format(today);
        yearString = year.format(today);

        //initialise intent for web view
        i = new Intent(thiscontext, WebViewActivity.class);

        //gets database from Database class
        DB = Database.getInstance(thiscontext);

        //these are the buttons used to change front and back view
        groupA = view.findViewById(R.id.groupA); //front view group of buttons and imageViews
        groupB = view.findViewById(R.id.groupB); //back view group

        back = view.findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groupA.setVisibility(groupA.INVISIBLE);
                groupB.setVisibility(groupB.VISIBLE);
            }
        });

        front = view.findViewById(R.id.frontBtn);
        front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groupB.setVisibility(groupB.INVISIBLE);
                groupA.setVisibility(groupA.VISIBLE);
            }
        });


        workoutRecView = view.findViewById(R.id.workoutRecView);

        list = new ArrayList<>();
        loadcard();

        adapter = new workoutRecViewAdapter();
        adapter.setList(list);

        workoutRecView.setAdapter(adapter);
        workoutRecView.setLayoutManager(new LinearLayoutManager(thiscontext));


        //initialise all images
        bicep = view.findViewById(R.id.bicep);
        shoulder = view.findViewById(R.id.shoulder1);
        forearm = view.findViewById(R.id.forearm1);
        chest = view.findViewById(R.id.chest);
        abs = view.findViewById(R.id.abs);
        quad = view.findViewById(R.id.quad);
        calf = view.findViewById(R.id.calf1);
        traps = view.findViewById(R.id.traps1);
        tricep = view.findViewById(R.id.tricep);
        forearm2 = view.findViewById(R.id.forearm2);
        shoulder2 = view.findViewById(R.id.shoulder2);
        traps2 = view.findViewById(R.id.traps2);
        traps3 = view.findViewById(R.id.traps3);
        lats = view.findViewById(R.id.lats);
        glutes = view.findViewById(R.id.glutes);
        calf2 = view.findViewById(R.id.calf2);
        lowerback = view.findViewById(R.id.lowerBack);
        hamstring = view.findViewById(R.id.hamstring);

        //initialise all buttons
        bicepBtnL = view.findViewById(R.id.bicepBtnL);
        bicepBtnL.setOnClickListener(this);
        bicepBtnL.setOnLongClickListener(this);
        bicepBtnR = view.findViewById(R.id.bicepBtnR);
        bicepBtnR.setOnClickListener(this);
        bicepBtnR.setOnLongClickListener(this);

        shoulderBtnL = view.findViewById(R.id.shoulderBtnL);
        shoulderBtnL.setOnClickListener(this);
        shoulderBtnL.setOnLongClickListener(this);
        shoulderBtnR = view.findViewById(R.id.shoulderBtnR);
        shoulderBtnR.setOnClickListener(this);
        shoulderBtnR.setOnLongClickListener(this);

        chestBtn = view.findViewById(R.id.chestBtn);
        chestBtn.setOnClickListener(this);
        chestBtn.setOnLongClickListener(this);

        absBtn = view.findViewById(R.id.absBtn);
        absBtn.setOnClickListener(this);
        absBtn.setOnLongClickListener(this);

        quadBtnL = view.findViewById(R.id.quadBtnL);
        quadBtnL.setOnClickListener(this);
        quadBtnL.setOnLongClickListener(this);
        quadBtnR = view.findViewById(R.id.quadBtnR);
        quadBtnR.setOnClickListener(this);
        quadBtnR.setOnLongClickListener(this);

        forearmBtnL = view.findViewById(R.id.forearmBtnL);
        forearmBtnL.setOnClickListener(this);
        forearmBtnL.setOnLongClickListener(this);
        forearmBtnR = view.findViewById(R.id.forearmBtnR);
        forearmBtnR.setOnClickListener(this);
        forearmBtnR.setOnLongClickListener(this);

        calfBtnL = view.findViewById(R.id.calf1BtnL);
        calfBtnL.setOnClickListener(this);
        calfBtnL.setOnLongClickListener(this);
        calfBtnR = view.findViewById(R.id.calf1BtnR);
        calfBtnR.setOnClickListener(this);
        calfBtnR.setOnLongClickListener(this);

        trapsBtn = view.findViewById(R.id.trapsBtn);
        trapsBtn.setOnClickListener(this);
        trapsBtn.setOnLongClickListener(this);

        tricepLBtn = view.findViewById(R.id.tricepLBtn);
        tricepLBtn.setOnClickListener(this);
        tricepLBtn.setOnLongClickListener(this);
        tricepRBtn = view.findViewById(R.id.tricepRBtn);
        tricepRBtn.setOnClickListener(this);
        tricepRBtn.setOnLongClickListener(this);

        forearmLBtn = view.findViewById(R.id.forearm2LBtn);
        forearmLBtn.setOnClickListener(this);
        forearmLBtn.setOnLongClickListener(this);
        forearmRBtn = view.findViewById(R.id.forearm2RBtn);
        forearmRBtn.setOnClickListener(this);
        forearmRBtn.setOnLongClickListener(this);

        shoulderLBtn = view.findViewById(R.id.shoulder2LBtn);
        shoulderLBtn.setOnClickListener(this);
        shoulderLBtn.setOnLongClickListener(this);
        shoulderRBtn = view.findViewById(R.id.shoulder2RBtn);
        shoulderRBtn.setOnClickListener(this);
        shoulderRBtn.setOnLongClickListener(this);

        traps2Btn = view.findViewById(R.id.traps2Btn);
        traps2Btn.setOnClickListener(this);
        traps2Btn.setOnLongClickListener(this);
        traps3Btn = view.findViewById(R.id.traps3Btn);
        traps3Btn.setOnClickListener(this);
        traps3Btn.setOnLongClickListener(this);

        glutesBtn = view.findViewById(R.id.glutesBtn);
        glutesBtn.setOnClickListener(this);
        glutesBtn.setOnLongClickListener(this);

        hamstringBtn = view.findViewById(R.id.hamstringBtn);
        hamstringBtn.setOnClickListener(this);
        hamstringBtn.setOnLongClickListener(this);

        calf2LBtn = view.findViewById(R.id.calf2BtnL);
        calf2LBtn.setOnClickListener(this);
        calf2LBtn.setOnLongClickListener(this);
        calf2RBtn = view.findViewById(R.id.calf2BtnR);
        calf2RBtn.setOnClickListener(this);
        calf2RBtn.setOnLongClickListener(this);

        lowerBackBtn = view.findViewById(R.id.lowerBackBtn);
        lowerBackBtn.setOnClickListener(this);
        lowerBackBtn.setOnLongClickListener(this);

        latsLBtn = view.findViewById(R.id.latsLBtn);
        latsLBtn.setOnClickListener(this);
        latsLBtn.setOnLongClickListener(this);
        latsRBtn = view.findViewById(R.id.latsRBtn);
        latsRBtn.setOnClickListener(this);
        latsRBtn.setOnLongClickListener(this);

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
        Boolean checkinsertdata = DB.insertdata(name, weekdayString, todayString, yearString);
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
        LocalDate today = LocalDate.now();
        String yesterday = today.minusDays(1).format(DateTimeFormatter.ofPattern("MMM dd"));
        String beforeYesterday = today.minusDays(2).format(DateTimeFormatter.ofPattern("MMM dd"));
        Cursor result = DB.getNameFromDate(todayString, yesterday, beforeYesterday, yearString);

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
                case "Calves":
                    changeVisible(calf, "Calves");
                    changeVisible(calf2, "Calves");
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