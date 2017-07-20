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
public class BlankFragment2 extends Fragment {

    TextView tv201,tv202,tv203,tv204,tv205,tv206,tv207,tv208,tv209,tv210,tv211,tv212,tv213,tv214,tv215,tv216,tv217,tv218;
    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);

        tv201 = (TextView)view.findViewById(R.id.textView201);
        tv202 = (TextView)view.findViewById(R.id.textView202);
        tv203 = (TextView)view.findViewById(R.id.textView203);
        tv204 = (TextView)view.findViewById(R.id.textView204);
        tv205 = (TextView)view.findViewById(R.id.textView205);
        tv206 = (TextView)view.findViewById(R.id.textView206);
        tv207 = (TextView)view.findViewById(R.id.textView207);
        tv208 = (TextView)view.findViewById(R.id.textView208);
        tv209 = (TextView)view.findViewById(R.id.textView209);
        tv210 = (TextView)view.findViewById(R.id.textView210);
        tv211 = (TextView)view.findViewById(R.id.textView211);
        tv212 = (TextView)view.findViewById(R.id.textView212);
        tv213 = (TextView)view.findViewById(R.id.textView213);
        tv214 = (TextView)view.findViewById(R.id.textView214);
        tv215 = (TextView)view.findViewById(R.id.textView215);
        tv216 = (TextView)view.findViewById(R.id.textView216);
        tv217 = (TextView)view.findViewById(R.id.textView217);
        tv218 = (TextView)view.findViewById(R.id.textView218);
        // Inflate the layout for this fragment
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "Museo100-Regular.otf");
        tv201.setTypeface(tf);

        Typeface tf1 = Typeface.createFromAsset(getActivity().getAssets(), "Museo100-Regular.otf");
        tv202.setTypeface(tf1);
        tv203.setTypeface(tf1);
        tv204.setTypeface(tf1);
        tv205.setTypeface(tf1);
        tv206.setTypeface(tf1);
        tv207.setTypeface(tf1);
        tv208.setTypeface(tf1);
        tv209.setTypeface(tf1);
        tv210.setTypeface(tf1);
        tv211.setTypeface(tf1);
        tv212.setTypeface(tf1);
        tv213.setTypeface(tf1);
        tv214.setTypeface(tf1);
        tv215.setTypeface(tf1);
        tv216.setTypeface(tf1);
        tv217.setTypeface(tf1);
        tv218.setTypeface(tf1);
        return  view;
    }

}
