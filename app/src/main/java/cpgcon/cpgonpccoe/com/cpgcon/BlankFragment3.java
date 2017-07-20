package cpgcon.cpgonpccoe.com.cpgcon;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment3 extends Fragment {

    TextView tv301,tv302,tv303,tv304,tv305,tv306,tv307;

    public BlankFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_fragment3, container, false);
        tv301 = (TextView)view.findViewById(R.id.textView301);
        tv302 = (TextView)view.findViewById(R.id.textView302);
        tv303 = (TextView)view.findViewById(R.id.textView303);
        tv304 = (TextView)view.findViewById(R.id.textView304);
        tv305 = (TextView)view.findViewById(R.id.textView305);
        tv306 = (TextView)view.findViewById(R.id.textView306);
        tv307 = (TextView)view.findViewById(R.id.textView307);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "Museo300-Regular.otf");
        tv301.setTypeface(tf);

        Typeface tf1 = Typeface.createFromAsset(getActivity().getAssets(), "Museo100-Regular.otf");
        tv302.setTypeface(tf1);
        tv303.setTypeface(tf1);
        tv304.setTypeface(tf1);
        tv305.setTypeface(tf1);
        tv306.setTypeface(tf1);
        tv307.setTypeface(tf1);
        // Inflate the layout for this fragment
        return view;
    }

}
