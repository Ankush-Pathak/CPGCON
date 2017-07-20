package cpgcon.cpgonpccoe.com.cpgcon;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.gc();
        setContentView(R.layout.activity_second2);
        ImageView img=(ImageView)findViewById(R.id.imageView);
        //img.setMaxHeight();
        //for title
        Typeface tf=Typeface.createFromAsset(getAssets(),"Museo300-Regular.otf");
        TextView tv=(TextView)findViewById(R.id.abt_cpgcon_title);
        tv.setTypeface(tf);

        //for content
        Typeface tf2= Typeface.createFromAsset(getAssets(),"Museo100-Regular.otf");
        TextView tv2=(TextView)findViewById(R.id.infoCpgcon);
        tv2.setTypeface(tf2);

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this,About.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        finish();
    }

}