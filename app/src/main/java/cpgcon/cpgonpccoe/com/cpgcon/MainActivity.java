package cpgcon.cpgonpccoe.com.cpgcon;
/*Coded by Alohamora, the to be greatest programmers earth has seen*/

import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Color;

import android.graphics.Shader;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CountDownTimer;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Period;


import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int flag=0,frag_flag = 1;
    BlankFragment fragment;
    FragmentManager fragmentManager;
    Button btnMoveUp;
    int height;
    double offset;
    TextView textView;
    Typeface typeface;
    CircleProgressView circleProgressViewSec, circleProgressViewHrs, circleProgressViewMin, circleProgressViewDays;
    DateTime dateTime, dateTimeNow;
    Period periodSec, periodHrs, periodMin, periodDays;
    CountDownTimer countDownTimerSec, countDownTimerHrs, countDownTimerMin, countDownTimerDays;
    int spinnerBarWidth, spinnerTextSize,contourSize,i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        System.gc();
        setContentView(R.layout.activity_main);
        flag=0;
        initialise();
        updateHrs();
        updateMin();
        updateDays();
        updateSeconds();
        btnMoveUp.setOnClickListener(MainActivity.this);
        if(getIntent().getExtras()!=null)
        {
            final Bundle bundle = getIntent().getExtras();
            i=bundle.getInt("key");
            if(i==1)
                moveButton();
        }
        //sharedPreferences=getSharedPreferences("home", MODE_PRIVATE);
    }

    void initialise() {
        System.gc();
        fragment = new BlankFragment();
        fragmentManager = getSupportFragmentManager();
        typeface = Typeface.createFromAsset(getAssets(), "AGENCYR.TTF");
        height = this.getResources().getDisplayMetrics().heightPixels;
        offset = this.getResources().getDisplayMetrics().densityDpi;
        offset = offset * 0.55;
        btnMoveUp = (Button) findViewById(R.id.btnMoveUp);
        btnMoveUp.setText("^");
        circleProgressViewSec = (CircleProgressView) findViewById(R.id.circularProgressSec);
        dateTime = new DateTime(2016, 3, 25,9, 05);
        dateTimeNow = DateTime.now();
        circleProgressViewHrs = (CircleProgressView) findViewById(R.id.circularProgressHrs);
        circleProgressViewMin = (CircleProgressView) findViewById(R.id.circularProgressMin);
        circleProgressViewDays = (CircleProgressView) findViewById(R.id.circularProgressDays);
        circleProgressViewSec.setTextTypeface(typeface);
        circleProgressViewMin.setTextTypeface(typeface);
        circleProgressViewHrs.setTextTypeface(typeface);
        circleProgressViewDays.setTextTypeface(typeface);
        spinnerBarWidth = circleProgressViewSec.getBarWidth() - 25;
        spinnerTextSize = circleProgressViewSec.getTextSize() + 45;
        contourSize = 3;
        textView = (TextView) findViewById(R.id.textView2);
        textView.setTypeface(typeface);


    }

    void moveButton() {
        btnMoveUp.setVisibility(View.INVISIBLE);
        fragmentManager.beginTransaction().replace(R.id.mainactivity_fragment,fragment).commit();
        flag=1;
    }



    void updateSeconds() {

        circleProgressViewSec.setBarColor(Color.parseColor("#ff7043"));
        circleProgressViewSec.setMaxValue(60);
        //circleProgressViewSec.setValueAnimated(100);
        //circleProgressViewSec.setText(String.valueOf(period.getDays()));
        circleProgressViewSec.setTextSize(spinnerTextSize);
        circleProgressViewSec.setShowTextWhileSpinning(false);
        circleProgressViewSec.setTextMode(TextMode.TEXT);
        circleProgressViewSec.setTextTypeface(typeface);
        circleProgressViewSec.setContourSize(contourSize);
        circleProgressViewSec.setRimWidth(0);
        circleProgressViewSec.setBarWidth(spinnerBarWidth);
        dateTimeNow = DateTime.now();
        if (!dateTimeNow.isAfter(dateTime)) {
            countDownTimerSec = new CountDownTimer(dateTime.getMillis(), 1000) {
                @Override
                public void onTick(long l) {
                    dateTimeNow = DateTime.now();
                    if(dateTimeNow.isAfter(dateTime))
                    {
                        countDownTimerSec.cancel();
                        countDownTimerDays.cancel();
                        countDownTimerHrs.cancel();
                        countDownTimerMin.cancel();
                        TextView textView = (TextView)findViewById(R.id.textView2);
                        textView.setText("Welcome to cPGCON 2016!");
                        textView.setTextColor(Color.parseColor("#ff7043"));

                    }


                    periodSec = new Period(dateTimeNow, dateTime);
                    periodHrs = new Period(dateTimeNow, dateTime);
                    periodMin = new Period(dateTimeNow, dateTime);
                    periodDays = new Period(dateTimeNow, dateTime);
                    circleProgressViewSec.setText(String.valueOf(periodSec.getSeconds()) + "sec");
                    circleProgressViewSec.setValueAnimated(periodSec.getSeconds());
                    if (periodSec.getSeconds() == 59) {
                        circleProgressViewHrs.setText(String.valueOf(periodHrs.getHours()) + "hrs");
                        circleProgressViewHrs.setValueAnimated(periodHrs.getHours());
                        circleProgressViewMin.setText(String.valueOf(periodMin.getMinutes()) + "min");
                        circleProgressViewMin.setValueAnimated(periodMin.getMinutes());
                        circleProgressViewDays.setText(String.valueOf(Days.daysBetween(dateTimeNow, dateTime).getDays()) + "days");
                        circleProgressViewDays.setValueAnimated(Days.daysBetween(dateTimeNow, dateTime).getDays());
                    }
                }

                @Override
                public void onFinish()
                {

                    Toast.makeText(getApplicationContext(),"Conference concluded on 25th of march",Toast.LENGTH_LONG).show();
                }
            }.start();
        }
        else
        {
            circleProgressViewDays.setValue(0);
            circleProgressViewDays.setText("0days");
            circleProgressViewSec.setValue(0);
            circleProgressViewSec.setText("0sec");
            circleProgressViewMin.setValue(0);
            circleProgressViewMin.setText("0min");
            circleProgressViewHrs.setValue(0);
            circleProgressViewHrs.setText("0hrs");
            TextView textView = (TextView)findViewById(R.id.textView2);
            textView.setText("Welcome to cPGCON 2016!");
            textView.setTextColor(Color.parseColor("#ff7043"));
            frag_flag = 0;
            moveButton();
        }
        //circleProgressViewSec.setValueAnimated(0,60,60000);
    }

    void updateHrs() {

        circleProgressViewHrs.setBarColor(Color.parseColor("#ff7043"));
        circleProgressViewHrs.setMaxValue(24);
        //circleProgressViewSec.setValueAnimated(100);
        //circleProgressViewSec.setText(String.valueOf(period.getDays()));
        circleProgressViewHrs.setTextSize(spinnerTextSize);
        circleProgressViewHrs.setShowTextWhileSpinning(false);
        circleProgressViewHrs.setTextMode(TextMode.TEXT);
        circleProgressViewHrs.setContourSize(contourSize);
        circleProgressViewHrs.setRimWidth(0);
        circleProgressViewHrs.setBarWidth(spinnerBarWidth);
        if (!dateTimeNow.isAfter(dateTime)) {
            countDownTimerHrs = new CountDownTimer(dateTime.getMillis(), 1000 * 60 * 60) {
                @Override
                public void onTick(long l) {
                    dateTimeNow = DateTime.now();
                    periodHrs = new Period(dateTimeNow, dateTime);
                    circleProgressViewHrs.setText(String.valueOf(periodHrs.getHours()) + "hrs");
                    circleProgressViewHrs.setValueAnimated(periodHrs.getHours());

                }

                @Override
                public void onFinish() {

                }
            }.start();
        } else {
            circleProgressViewDays.setValue(0);
            circleProgressViewDays.setText("0days");
            circleProgressViewSec.setValue(0);
            circleProgressViewSec.setText("0sec");
            circleProgressViewMin.setValue(0);
            circleProgressViewMin.setText("0min");
            circleProgressViewHrs.setValue(0);
            circleProgressViewHrs.setText("0hrs");
            TextView textView = (TextView)findViewById(R.id.textView2);
            textView.setText("Welcome to cPGCON 2016!");
            textView.setTextColor(Color.parseColor("#ff7043"));
            frag_flag = 0;
            moveButton();
        }
        //circleProgressViewSec.setValueAnimated(0,60,60000);
    }

    void updateMin() {

        circleProgressViewMin.setBarColor(Color.parseColor("#ff7043"));
        circleProgressViewMin.setMaxValue(60);
        //circleProgressViewSec.setValueAnimated(100);
        //circleProgressViewSec.setText(String.valueOf(period.getDays()));
        circleProgressViewMin.setTextSize(spinnerTextSize);
        circleProgressViewMin.setShowTextWhileSpinning(false);
        circleProgressViewMin.setTextMode(TextMode.TEXT);
        circleProgressViewMin.setContourSize(contourSize);
        circleProgressViewMin.setRimWidth(0);
        circleProgressViewMin.setBarWidth(spinnerBarWidth);
        if (!dateTimeNow.isAfter(dateTime)) {
            countDownTimerMin = new CountDownTimer(dateTime.getMillis(), 1000 * 30) {
                @Override
                public void onTick(long l) {
                    dateTimeNow = DateTime.now();
                    periodMin = new Period(dateTimeNow, dateTime);
                    circleProgressViewMin.setText(String.valueOf(periodMin.getMinutes()) + "min");
                    circleProgressViewMin.setValueAnimated(periodMin.getMinutes());

                }

                @Override
                public void onFinish() {

                }
            }.start();
        } else {
            circleProgressViewDays.setValue(0);
            circleProgressViewDays.setText("0days");
            circleProgressViewSec.setValue(0);
            circleProgressViewSec.setText("0sec");
            circleProgressViewMin.setValue(0);
            circleProgressViewMin.setText("0min");
            circleProgressViewHrs.setValue(0);
            circleProgressViewHrs.setText("0hrs");
            TextView textView = (TextView)findViewById(R.id.textView2);
            textView.setText("Welcome to cPGCON 2016!");
            textView.setTextColor(Color.parseColor("#ff7043"));
            frag_flag = 0;
            moveButton();
        }
        //circleProgressViewSec.setValueAnimated(0,60,60000);
    }

    void updateDays() {

        circleProgressViewDays.setBarColor(Color.parseColor("#ff7043"));
        circleProgressViewDays.setMaxValue(10);
        //circleProgressViewSec.setValueAnimated(100);
        //circleProgressViewSec.setText(String.valueOf(period.getDays()));
        circleProgressViewDays.setTextSize(spinnerTextSize);
        circleProgressViewDays.setShowTextWhileSpinning(false);
        circleProgressViewDays.setTextMode(TextMode.TEXT);
        circleProgressViewDays.setContourSize(contourSize);
        circleProgressViewDays.setRimWidth(0);
        circleProgressViewDays.setBarWidth(spinnerBarWidth);
        dateTimeNow = DateTime.now();
        periodDays = new Period(dateTimeNow, dateTime);

        /*circleProgressViewDays.setText(String.valueOf(Days.daysBetween(dateTimeNow, dateTime).getDays()));
        circleProgressViewDays.setValueAnimated(Days.daysBetween(dateTimeNow, dateTime).getDays());
        */if (!dateTimeNow.isAfter(dateTime)) {
            countDownTimerDays = new CountDownTimer(dateTime.getMillis(), 1000 * 60 * 60) {
                @Override
                public void onTick(long l) {
                    dateTimeNow = DateTime.now();
                    periodDays = new Period(dateTimeNow, dateTime);
                    circleProgressViewDays.setText(String.valueOf(Days.daysBetween(dateTimeNow, dateTime).getDays()) + "days");
                    circleProgressViewDays.setValueAnimated(Days.daysBetween(dateTimeNow, dateTime).getDays());

                }

                @Override
                public void onFinish() {

                }
            }.start();
        } else {
            circleProgressViewDays.setValue(0);
            circleProgressViewDays.setText("0days");
            circleProgressViewSec.setValue(0);
            circleProgressViewSec.setText("0sec");
            circleProgressViewMin.setValue(0);
            circleProgressViewMin.setText("0min");
            circleProgressViewHrs.setValue(0);
            circleProgressViewHrs.setText("0hrs");
            TextView textView = (TextView)findViewById(R.id.textView2);
            textView.setText("Welcome to cPGCON 2016!");
            textView.setTextColor(Color.parseColor("#ff7043"));
            frag_flag = 0;
            moveButton();
        }
        //circleProgressViewSec.setValueAnimated(0,60,60000);
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnMoveUp:
                //Toast.makeText(this, "In btnMoveUp", Toast.LENGTH_LONG).show();
                if (btnMoveUp.getText() == "^") {
                    moveButton();
                    btnMoveUp.setVisibility(View.INVISIBLE);
                    break;
                }
                countDownTimerSec.cancel();

        }

    }

    @Override
    public void onBackPressed() {
        if(flag == 1 && frag_flag == 1)
        {
            removeFragment();
            flag=0;
        }
        else {
            finish();
            super.onBackPressed();
        }


    }
    public void removeFragment()
    {
        fragmentManager.beginTransaction().remove(fragment).commit();
        btnMoveUp.setVisibility(View.VISIBLE);
    }

}
