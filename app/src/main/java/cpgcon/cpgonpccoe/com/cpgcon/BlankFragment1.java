package cpgcon.cpgonpccoe.com.cpgcon;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment1 extends Fragment {
    TextView tv100,tv101,tv102,tv103,tv104,tv105,tv106,tv107,tv108,tv109,tv110,tv111,tv113;
    public BlankFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_fragment1, container, false);
        tv100 = (TextView)view.findViewById(R.id.textView100);
        tv101 = (TextView)view.findViewById(R.id.textView101);
        tv102 = (TextView)view.findViewById(R.id.textView102);
        tv103 = (TextView)view.findViewById(R.id.textView103);
        tv104 = (TextView)view.findViewById(R.id.textView104);
        tv105 = (TextView)view.findViewById(R.id.textView105);
        tv106 = (TextView)view.findViewById(R.id.textView106);
        tv107 = (TextView)view.findViewById(R.id.textView107);
        tv108 = (TextView)view.findViewById(R.id.textView108);
        tv109 = (TextView)view.findViewById(R.id.textView109);
        tv110 = (TextView)view.findViewById(R.id.textView110);
        tv111 = (TextView)view.findViewById(R.id.textView111);
        tv113 = (TextView)view.findViewById(R.id.textView113);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "Museo300-Regular.otf");
        tv100.setTypeface(tf);

        Typeface tf1 = Typeface.createFromAsset(getActivity().getAssets(), "Museo100-Regular.otf");
        tv101.setTypeface(tf1);
        tv102.setTypeface(tf1);
        tv103.setTypeface(tf1);
        tv104.setTypeface(tf1);
        tv105.setTypeface(tf1);
        tv106.setTypeface(tf1);
        tv107.setTypeface(tf1);
        tv108.setTypeface(tf1);
        tv109.setTypeface(tf1);
        tv110.setTypeface(tf1);
        tv111.setTypeface(tf1);
        tv113.setTypeface(tf1);
        // Inflate the layout for this fragment
        tv103.setMovementMethod(LinkMovementMethod.getInstance());
        return view;
    }
}
