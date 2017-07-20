package cpgcon.cpgonpccoe.com.cpgcon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class About extends AppCompatActivity {
    Button btnPccoe;
    Button btnCpgcon;
    Button btnDepatment;
    Button btnAcm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnPccoe = (Button)findViewById(R.id.btnPccoe);
        btnCpgcon = (Button)findViewById(R.id.btnCpgcon);
        btnDepatment = (Button)findViewById(R.id.btnDepartment);
        btnAcm = (Button)findViewById(R.id.btnAcm);
        btnPccoe.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {

                        try {
                            btnPccoe.setBackgroundResource(R.drawable.about_pccoe_animate);
                            Intent intent = new Intent(About.this, SecondActivity.class);
                            startActivity(intent);
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        btnCpgcon.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        btnCpgcon.setBackgroundResource(R.drawable.aboutcpgcon_animate);
                        Intent intent = new Intent(About.this,SecondActivity2.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                        finish();
                    }
                }
        );
        btnDepatment.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        btnDepatment.setBackgroundResource(R.drawable.about_dept_animate);
                        Intent intent = new Intent(About.this,SecondActivity3.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                        finish();
                    }
                }
        );
        btnAcm.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        btnAcm.setBackgroundResource(R.drawable.about_cesa_anim);
                        Intent intent = new Intent(About.this,SecondActivity4.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                        finish();
                    }
                } );
        btnPccoe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (btnPccoe.isPressed())
                    btnPccoe.setBackgroundResource(R.drawable.about_pccoe);
                else
                    btnPccoe.setBackgroundResource(R.drawable.about_pccoe_animate);
                return false;
            }
        });
        btnCpgcon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (btnCpgcon.isPressed())
                    btnCpgcon.setBackgroundResource(R.drawable.aboutcpgcon_animate);
                else
                    btnCpgcon.setBackgroundResource(R.drawable.aboutcpgcon);
                return false;
            }
        });
        btnDepatment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(btnDepatment.isPressed())
                    btnDepatment.setBackgroundResource(R.drawable.about_dept_animate);
                else
                    btnDepatment.setBackgroundResource(R.drawable.about_dept);
                return false;
            }
        });
        btnAcm.setOnTouchListener(new View.OnTouchListener() {
                                      @Override
                                      public boolean onTouch(View v, MotionEvent event) {
                                          if(event.getAction() == MotionEvent.ACTION_BUTTON_PRESS)
                                              btnAcm.setBackgroundResource(R.drawable.about_cesa_anim);
                                          else
                                              btnAcm.setBackgroundResource(R.drawable.about_cesa);
                                          return false;
                                      }
                                  }
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(About.this,MainActivity.class);
        intent.putExtra("key",1);
        startActivity(intent);
        finish();
    }
}
