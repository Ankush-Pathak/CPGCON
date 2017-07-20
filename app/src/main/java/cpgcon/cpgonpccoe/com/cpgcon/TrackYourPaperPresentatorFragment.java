package cpgcon.cpgonpccoe.com.cpgcon;


import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrackYourPaperPresentatorFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    String[] resultsIds,resultsDom,resultsLoc,resultsDat,resultsTime,resultsName;
    SQLiteDatabase sqLiteDatabase;
    Spinner spinner;
    Cursor databasePaper;
    EditText editTextSearch;
    ListView listView;
    ArrayList<String> displayResultList;
    ArrayAdapter<String> displayResult;
    TextView textView;
    ArrayAdapter<String> dataAdapter;
    List<String> categories;
    Handler handler;
    TextView textview;
    View view;

    public TrackYourPaperPresentatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_track_your_paper_presentator, container, false);
        initialise();
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() > 1)
                    search();
                else
                    listView.setAdapter(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //search();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                displayDialog(i);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }



    public void initialise()
    {

        sqLiteDatabase = ((TrackYourPaperTabbed)getActivity()).sqLiteDatabase;
        int j = 0;


        editTextSearch = (EditText)view.findViewById(R.id.editTextSearch);
        listView = (ListView)view.findViewById(R.id.listView);
        textView = (TextView)view.findViewById(R.id.textViewHelper);
        resultsDom = new String[500];
        resultsIds = new String[500];
        resultsLoc = new String[500];
        resultsDat = new String[500];
        resultsTime = new String[500];
        resultsName = new String[500];

        //For Contact
        spinner=(Spinner)view.findViewById(R.id.spinner);
        categories=new ArrayList<String>();
        categories.add("Contact respective Incharges");
        categories.add("Soft Computing and Artificial Intelligence");
        categories.add("Data mining and information retrieval");
        categories.add("Big data analytics");
        categories.add("Operating system and compilers");
        categories.add("Image processing /Signal processing");
        categories.add("Computer graphics and visualization");
        categories.add("Networking and cloud computing");
        categories.add("Mobile and wireless communication/Networking");
        categories.add("Parallel Computing");
        categories.add("Information security / Cyber security");
        categories.add("Video/virtual reality");
        categories.add("Software Engineering and Architecture");
        categories.add("Distributed computing");
        categories.add("Others");
        categories.add("Research Scholar(Ph.D)");
        dataAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,categories);
        dataAdapter.setDropDownViewResource(R.layout.dropdown_newline);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);

    }
    public void call(String num)
    {

        Intent callintent=new Intent(Intent.ACTION_DIAL);
        callintent.setData(Uri.parse(num));
        try
        {
            startActivity(callintent);
        }
        catch(android.content.ActivityNotFoundException e)
        {
            Toast.makeText(getActivity(), "Unable to make call", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }


    public void search()
    {
        int i = 0;
        textView.setText("In search not found!");
        //Toast.makeText(this,"In search.",Toast.LENGTH_SHORT).show();
        displayResultList = new ArrayList<>();
        String idOrDomain;

        idOrDomain = editTextSearch.getText().toString();
        if(idOrDomain == null)
            idOrDomain = "445sdsd";
        databasePaper = sqLiteDatabase.rawQuery("SELECT * FROM data WHERE paper_id LIKE '" + idOrDomain + "' or track LIKE '%" + idOrDomain + "%' or author LIKE '%" + idOrDomain + "%'",null);
        databasePaper.moveToFirst();
        while (databasePaper.isAfterLast() == false)
        {
            if(i > 499)
                break;
            resultsIds[i] = databasePaper.getString(1);
            resultsDom[i] = databasePaper.getString(6);
            resultsLoc[i] = databasePaper.getString(7);
            resultsDat[i] = databasePaper.getString(5);
            resultsTime[i] = databasePaper.getString(8);
            resultsName[i] = databasePaper.getString(3);
            displayResultList.add("Name : " + databasePaper.getString(3) + "\nPaper Id : " + databasePaper.getString(1) + "\nDomain : " + databasePaper.getString(6));
            databasePaper.moveToNext();
            i++;

            displayFinalList();
        }
        i = 0;

        displayFinalList();
    }
    public void displayFinalList()
    {

        textView.setText("In search found!");
        displayResult = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,displayResultList);
        listView.setAdapter(displayResult);
    }

    /*@Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(getActivity(),MainActivity.class);
        intent.putExtra("key",1);
        startActivity(intent);
        getActivity().finish();
    }*/
    public void displayDialog(int i)
    {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.customdialog);

        dialog.setTitle("Details");
        TextView textViewId,textViewDom,textViewDate,textViewVen,textViewTime,textViewName;
        Button btnOk;
        textViewDate = (TextView)dialog.findViewById(R.id.textViewDate);
        textViewDom = (TextView)dialog.findViewById(R.id.textViewDom);
        textViewId = (TextView)dialog.findViewById(R.id.textViewID);
        textViewVen = (TextView)dialog.findViewById(R.id.textViewVenue);
        textViewTime = (TextView)dialog.findViewById(R.id.textViewTime);
        textViewName = (TextView)dialog.findViewById(R.id.textViewAuthorName);
        textViewDate.append(" " + resultsDat[i]);
        textViewDom.append(" " + resultsDom[i]);
        textViewTime.append(" " + resultsTime[i]);
        textViewVen.append(" " + resultsLoc[i]);
        textViewId.append(" " + resultsIds[i]);
        textViewName.append(" " + resultsName[i]);
        btnOk = (Button)dialog.findViewById(R.id.btnOkDialog);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void displayDialog1(String trackName, final String strinNumber,final String name)
    {
        final Dialog dialog1 = new Dialog(getActivity());
        dialog1.setContentView(R.layout.dialogbox);
        dialog1.setTitle("Contact Details");
        TextView textViewName1,textViewTrack1;
        textViewName1=(TextView)dialog1.findViewById(R.id.textViewName1);
        textViewTrack1=(TextView)dialog1.findViewById(R.id.textViewTrack1);
        textViewTrack1.append(trackName);
        textViewName1.append(name);
        Button buttonCall;
        buttonCall=(Button)dialog1.findViewById(R.id.buttonCall);
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call(strinNumber);
                dialog1.dismiss();
                Toast.makeText(getContext(), "Calling " + name , Toast.LENGTH_SHORT).show();
            }

        });
        dialog1.show();

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item=parent.getItemAtPosition(position).toString();
        System.out.println("In method");
        String phnum,name;

        switch (position)
        {
            case 0:Typeface tf1= Typeface.createFromAsset(getActivity().getAssets(),"Museo100-Regular.otf");
                TextView selectedtext=(TextView)parent.getChildAt(0);
                selectedtext.setTypeface(tf1);
                selectedtext.setTextSize(21);
                if(selectedtext!=null)
                {
                    selectedtext.setTextColor(Color.RED);
                }
                break;


            case 1:
                name="Prof Sonali Tidke";
                phnum="tel:7798635345";
                displayDialog1(item,phnum,name);
                break;

            case 2:
                name="Dr.Anuradha Thakre";
                phnum="tel:9226094966";
                displayDialog1(item,phnum,name);


                break;
            case 4:
                name="Prof Sonal Gore";
                phnum="tel:9226094968";
                displayDialog1(item,phnum,name);


                break;
            case 3:
                name="Prof Sushma Vispute";
                phnum="tel:9226094972";
                displayDialog1(item,phnum,name);


                break;
            case 5:
                name="Prof Harshada Mhaske";;
                phnum="tel:9226094982";
                displayDialog1(item,phnum,name);


                break;
            case 6:
                name="Prof Shailaja Pede";
                phnum="tel:9226094971";
                displayDialog1(item,phnum,name);


                break;
            case 7:
                name="Prof Meghana Lokhande";
                phnum="tel:9823334381";
                displayDialog1(item,phnum,name);


                break;
            case 8:
                name="Prof Atul Pawar";
                phnum="tel:9226094984";
                displayDialog1(item,phnum,name);


                break;
            case 9:
                name="Prof B.Mahalakshmi";
                phnum="tel:9226094969";
                displayDialog1(item,phnum,name);


                break;
            case 10:
                name="Dr.Sonali Patil";
                phnum="tel:9226094990";
                displayDialog1(item,phnum,name);


                break;
            case 11:
                name="Prof Santvana Gudhade";
                phnum="tel:8380095194";
                displayDialog1(item,phnum,name);


                break;
            case 12:
                name="Dr.Jayant Umale";
                phnum="tel:9226094961";
                displayDialog1(item,phnum,name);


                break;
            case 13:
                name="Prof S S Deshmukh";
                phnum="tel:9226094981";
                displayDialog1(item,phnum,name);


                break;
            case 14:
                name="Prof Rahul Patil";
                phnum="tel:9226094975";
                displayDialog1(item,phnum,name);


                break;
            case 15:
                name="Prof S S Sambare";
                phnum="tel:9226094964";
                displayDialog1(item,phnum,name);


                break;

        }
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                spinner.setSelection(0);
            }
        }, 1800);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
