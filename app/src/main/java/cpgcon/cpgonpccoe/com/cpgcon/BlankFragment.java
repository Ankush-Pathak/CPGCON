package cpgcon.cpgonpccoe.com.cpgcon;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */

public class BlankFragment extends Fragment implements View.OnClickListener,View.OnTouchListener{
    Button btnMoveDown, btnTrackPaper, btnCheckIn, btnCmtLogin, btnGuideline, btnHowToReach, btnAbout;
    View view;
    Handler handler;
    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_menu,container,false);
        initialise();
        return view;

    }

    void initialise()
    {
        btnMoveDown = (Button)view.findViewById(R.id.btnMoveDown);
        btnTrackPaper = (Button) view.findViewById(R.id.btnTrackPaper);
        btnCheckIn = (Button) view.findViewById(R.id.btnCheckIn);
        btnCmtLogin = (Button) view.findViewById(R.id.btnCmtLogin);
        btnGuideline = (Button) view.findViewById(R.id.btnGuideline);
        btnHowToReach = (Button) view.findViewById(R.id.btnHowToReach);
        btnAbout = (Button) view.findViewById(R.id.btnAbout);

        btnAbout.setOnClickListener(this);
        btnCheckIn.setOnClickListener(this);
        btnCmtLogin.setOnClickListener(this);
        btnGuideline.setOnClickListener(this);
        btnHowToReach.setOnClickListener(this);
        btnTrackPaper.setOnClickListener(this);
        btnAbout.setOnTouchListener(this);
        btnCheckIn.setOnTouchListener(this);
        btnCmtLogin.setOnTouchListener(this);
        btnGuideline.setOnTouchListener(this);
        btnHowToReach.setOnTouchListener(this);
        btnTrackPaper.setOnTouchListener(this);
        btnMoveDown.setOnClickListener(this);
        btnMoveDown.setVisibility(View.INVISIBLE);

    }
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {

            /*case R.id.btnMoveDown:
                ((MainActivity)getActivity()).removeFragment();
                //getActivity().finish();
                //Toast.makeText(getActivity(),"In btnMoveDown",Toast.LENGTH_LONG).show();
                break;*/

            case R.id.btnTrackPaper:
                btnTrackPaper.setBackgroundResource(R.drawable.trackpaper_animate);
                intent = new Intent(getActivity(), TrackYourPaperTabbed.class);
                startActivity(intent);
                break;

            case R.id.btnCheckIn:
                btnCheckIn.setBackgroundResource(R.drawable.check_in_animate);
                intent = new Intent(getActivity(),CheckIn.class);
                startActivity(intent);
                break;

            case R.id.btnCmtLogin:
                btnCmtLogin.setBackgroundResource(R.drawable.cmtlogin_animate);
                intent = new Intent(getActivity(), CmtLogin.class);
                startActivity(intent);
                break;

            case R.id.btnGuideline:
                btnGuideline.setBackgroundResource(R.drawable.guidelines_animate);
                intent = new Intent(getActivity(),Guidelines1.class);
                startActivity(intent);
                break;

            case R.id.btnHowToReach:
                btnHowToReach.setBackgroundResource(R.drawable.waymap_animate);
                intent = new Intent(getActivity(), HowTooReach.class);
                startActivity(intent);
                break;

            case R.id.btnAbout:
                btnAbout.setBackgroundResource(R.drawable.about_animate);
                intent = new Intent(getActivity(), About.class);
                startActivity(intent);
                break;
        }
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                getActivity().finish();
            }
        }, 1800);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {

            case R.id.btnTrackPaper:

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                    btnTrackPaper.setBackgroundResource(R.drawable.trackpaper_animate);
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                    btnTrackPaper.setBackgroundResource(R.drawable.trackpaper);
                break;

            case R.id.btnCheckIn:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                    btnCheckIn.setBackgroundResource(R.drawable.check_in_animate);
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                    btnCheckIn.setBackgroundResource(R.drawable.checkin);
                break;

            case R.id.btnCmtLogin:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                    btnCmtLogin.setBackgroundResource(R.drawable.cmtlogin_animate);
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                    btnCmtLogin.setBackgroundResource(R.drawable.cmtlogin);
                break;

            case R.id.btnGuideline:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                    btnGuideline.setBackgroundResource(R.drawable.guidelines_animate);
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                    btnGuideline.setBackgroundResource(R.drawable.guidelines);
                break;

            case R.id.btnHowToReach:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                    btnHowToReach.setBackgroundResource(R.drawable.waymap_animate);
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                    btnHowToReach.setBackgroundResource(R.drawable.waymap);
                break;

            case R.id.btnAbout:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                    btnAbout.setBackgroundResource(R.drawable.about_animate);
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                    btnAbout.setBackgroundResource(R.drawable.about);
                break;
        }

        return false;
    }

}
