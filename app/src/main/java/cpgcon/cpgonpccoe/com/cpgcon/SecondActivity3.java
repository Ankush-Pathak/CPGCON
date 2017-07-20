package cpgcon.cpgonpccoe.com.cpgcon;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class SecondActivity3 extends AppCompatActivity {
    ImageView imageView;
    PhotoViewAttacher photoViewAttacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second3);
        //for title
        Typeface tf= Typeface.createFromAsset(getAssets(),"Museo300-Regular.otf");
        TextView tv=(TextView)findViewById(R.id.abt_departement_title);
        tv.setTypeface(tf);

        //for content
        Typeface tf2=Typeface.createFromAsset(getAssets(),"Museo100-Regular.otf");
        TextView tv2=(TextView)findViewById(R.id.infoDepartement);
        tv2.setTypeface(tf2);

        imageView = (ImageView) findViewById(R.id.imageView);

        // Set the Drawable displayed
        Drawable bitmap = getResources().getDrawable(R.drawable.department);
        imageView.setImageDrawable(bitmap);

        // Attach a PhotoViewAttacher, which takes care of all of the zooming functionality.
        photoViewAttacher = new PhotoViewAttacher(imageView);
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this,About.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }

}
