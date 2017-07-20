package cpgcon.cpgonpccoe.com.cpgcon;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

public class TrackYourPaper extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] paperId,paperDomain,location,date,time,resultsIds,resultsDom,resultsLoc,resultsDat,resultsTime;
    SQLiteDatabase paperDb;
    Spinner spinner;
    Cursor databasePaper;
    EditText editTextSearch;
    ListView listView;
    ArrayList<String> displayResultList;
    ArrayAdapter<String> displayResult;
    TextView textView;
    ArrayAdapter<String> dataAdapter;
    List<String>categories;
    Handler handler;
    TextView textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_your_paper);
        initialise();


        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence != "")
                    search();
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

    }
    public void call(String num)
    {

        Intent callintent=new Intent(Intent.ACTION_CALL);
        callintent.setData(Uri.parse(num));
        try
        {
            startActivity(callintent);
        }
        catch(android.content.ActivityNotFoundException e)
        {
            Toast.makeText(this, "Unable to make call", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }

    public void initialise()
    {
        System.out.println("Hello from the other side!");
        paperId = new String[]{"123456","112233","445566","778899","789456","354564","876432","683222","567335","456666"};
        paperDomain = new String[]{"Data mining","Parallel computing","Big data","Information security"};
        location = new String[]{"202","111","111","206"};
        date = new String[]{"25/3/16","26/3/16"};
        time = new String[]{"9:00","14:00"};
        paperDb = openOrCreateDatabase("PaperDb",MODE_PRIVATE,null);
        paperDb.execSQL("CREATE TABLE IF NOT EXISTS PaperDb(paperId VARCHAR,paperDomain VARCHAR,location VARCHAR,date VARCHAR,time VARCHAR);");
        paperDb.delete("PaperDb",null,null);
        paperDb = openOrCreateDatabase("PaperDb",MODE_PRIVATE,null);
        paperDb.execSQL("CREATE TABLE IF NOT EXISTS PaperDb(paperId VARCHAR,paperDomain VARCHAR,location VARCHAR,date VARCHAR,time VARCHAR);");
        int j = 0;
        for(int i = 0;i < 10;i++)
        {
            j = i % 4;
            paperDb.execSQL("INSERT INTO PaperDb VALUES('" + paperId[i] + "','" + paperDomain[j] + "','" + location[j] + "','" + date[j%2] + "','" + time[j%2] + "');");
        }

        editTextSearch = (EditText)findViewById(R.id.editTextSearch);
        listView = (ListView)findViewById(R.id.listView);
        textView = (TextView)findViewById(R.id.textViewHelper);
        resultsDom = new String[10];
        resultsIds = new String[10];
        resultsLoc = new String[10];
        resultsDat = new String[10];
        resultsTime = new String[10];

        //For Contact
        spinner=(Spinner)findViewById(R.id.spinner);
        categories=new ArrayList<String>();
        categories.add("Contact respective In charges");
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
        categories.add("Adi");
        dataAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categories);
        dataAdapter.setDropDownViewResource(R.layout.dropdown_newline);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);

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
        databasePaper = paperDb.rawQuery("SELECT * FROM PaperDb WHERE paperId LIKE '%" + idOrDomain + "%' or paperDomain LIKE '%" + idOrDomain + "%'",null);
        databasePaper.moveToFirst();
        while (databasePaper.isAfterLast() == false)
        {
            if(i >= 5)
                break;
            resultsIds[i] = databasePaper.getString(0);
            resultsDom[i] = databasePaper.getString(1);
            resultsLoc[i] = databasePaper.getString(2);
            resultsDat[i] = databasePaper.getString(3);
            resultsTime[i] = databasePaper.getString(4);
            displayResultList.add("Paper Id : " + databasePaper.getString(0) + "\nDomain : " + databasePaper.getString(1));
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
        displayResult = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,displayResultList);
        listView.setAdapter(displayResult);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TrackYourPaper.this,MainActivity.class);
        intent.putExtra("key",1);
        startActivity(intent);
        finish();
    }
    public void displayDialog(int i)
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.customdialog);

        dialog.setTitle("Details");
        TextView textViewId,textViewDom,textViewDate,textViewVen,textViewTime;
        Button btnOk;
        textViewDate = (TextView)dialog.findViewById(R.id.textViewDate);
        textViewDom = (TextView)dialog.findViewById(R.id.textViewDom);
        textViewId = (TextView)dialog.findViewById(R.id.textViewID);
        textViewVen = (TextView)dialog.findViewById(R.id.textViewVenue);
        textViewTime = (TextView)dialog.findViewById(R.id.textViewTime);
        textViewDate.append(" " + resultsDat[i]);
        textViewDom.append(" " + resultsDom[i]);
        textViewTime.append(" " + resultsTime[i]);
        textViewVen.append(" " + resultsLoc[i]);
        textViewId.append(" " + resultsIds[i]);
        btnOk = (Button)dialog.findViewById(R.id.btnOkDialog);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item=parent.getItemAtPosition(position).toString();
        System.out.println("In method");
    switch (position)
    {
        case 0:Typeface tf1= Typeface.createFromAsset(getAssets(),"Museo100-Regular.otf");
            TextView selectedtext=(TextView)parent.getChildAt(0);
                selectedtext.setTypeface(tf1);
            selectedtext.setTextSize(21);
            if(selectedtext!=null)
            {
                selectedtext.setTextColor(Color.RED);
            }
            break;


        case 1: Toast.makeText(parent.getContext(), "Calling " + item + " In charge", Toast.LENGTH_SHORT).show();
            call("tel:7798635345");
            break;
        case 2: Toast.makeText(parent.getContext(), "Calling " + item + " In charge", Toast.LENGTH_SHORT).show();
            call("tel:9226094966");
            break;
        case 4: Toast.makeText(parent.getContext(), "Calling " + item + " In charge", Toast.LENGTH_SHORT).show();
            call("tel:9226094968");
            break;
        case 3: Toast.makeText(parent.getContext(), "Calling " + item + " In charge", Toast.LENGTH_SHORT).show();
            call("tel:9226094972");
            break;
        case 5: Toast.makeText(parent.getContext(), "Calling " + item + " In charge", Toast.LENGTH_SHORT).show();
            call("tel:9226094982");
            break;
        case 6:Toast.makeText(parent.getContext(), "Calling " + item + " In charge", Toast.LENGTH_SHORT).show();
            call("tel:9226094971");
            break;
        case 7:Toast.makeText(parent.getContext(), "Calling " + item + " In charge", Toast.LENGTH_SHORT).show();
            call("tel:9823334381");
            break;
        case 8:Toast.makeText(parent.getContext(), "Calling " + item + " In charge", Toast.LENGTH_SHORT).show();
            call("tel:9226094984");
            break;
        case 9:Toast.makeText(parent.getContext(), "Calling " + item + " In charge", Toast.LENGTH_SHORT).show();
            call("tel:9226094969");
            break;
        case 10:Toast.makeText(parent.getContext(), "Calling " + item + " In charge", Toast.LENGTH_SHORT).show();
            call("tel:9226094990");
            break;
        case 11:Toast.makeText(parent.getContext(), "Calling " + item + " In charge", Toast.LENGTH_SHORT).show();
            call("tel:8380095194");
            break;
        case 12:Toast.makeText(parent.getContext(), "Calling " + item + " In charge", Toast.LENGTH_SHORT).show();
            call("tel:9226094961");
            break;
        case 13:Toast.makeText(parent.getContext(), "Calling " + item + " In charge", Toast.LENGTH_SHORT).show();
            call("tel:9226094981");
            break;
        case 14:Toast.makeText(parent.getContext(), "Calling " + item + " In charge", Toast.LENGTH_SHORT).show();
            call("tel:9226094975");
            break;
        case 15:Toast.makeText(parent.getContext(), "Calling " + item + " In charge", Toast.LENGTH_SHORT).show();
            call("tel:9226094964");
            break;
        case 16:Toast.makeText(parent.getContext(), "Calling " + item + " In charge", Toast.LENGTH_SHORT).show();
            call("tel:8793298397");
            break;
    }
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                spinner.setSelection(0);
            }
        },1800);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

