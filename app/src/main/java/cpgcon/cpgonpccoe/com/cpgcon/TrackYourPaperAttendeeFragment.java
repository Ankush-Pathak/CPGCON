package cpgcon.cpgonpccoe.com.cpgcon;


import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrackYourPaperAttendeeFragment extends Fragment {
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    EditText editText;
    ListView listView;
    String[] track,name,venue,building;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;

    public TrackYourPaperAttendeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_track_your_paper_attendee, container, false);
        track = new String[50];
        name = new String[50];
        venue = new String[50];
        building = new String[50];
        editText = (EditText)view.findViewById(R.id.editTextAttendee);
        listView = (ListView)view.findViewById(R.id.listViewAttendee);
        sqLiteDatabase = ((TrackYourPaperTabbed)(getActivity())).sqLiteDatabase;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 if(charSequence.length() > 1)
                    updateListView();
                else
                     listView.setAdapter(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog(i);
            }
        });
        return view;

    }
    void updateListView()
    {
        if (!editText.getText().toString().equalsIgnoreCase("")) {
            arrayList = new ArrayList<String>();
            int i = 0;
            String search = editText.getText().toString();
            cursor = sqLiteDatabase.rawQuery("Select * from attendee where Name LIKE '%" + search + "%'",null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                if(i > 49)
                    break;
                name[i] = cursor.getString(1);
                track[i] = cursor.getString(0);
                venue[i] = cursor.getString(2);
                building[i] = cursor.getString(3);
                arrayList.add("Name : " + name[i] + "\nTrack : " + track[i]);
                cursor.moveToNext();
                i++;
            }
            arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(arrayAdapter);
        }
    }

    void showDialog(int position)
    {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialog_attendee);
        dialog.setTitle("Details");
        TextView textViewName,textViewTrack,textViewVenue,textViewBuildding;
        Button button;

        button = (Button)dialog.findViewById(R.id.btnOkAttendee);
        textViewName = (TextView)dialog.findViewById(R.id.textViewAttendeeName);
        textViewTrack = (TextView)dialog.findViewById(R.id.textViewAttendeeTrack);
        textViewVenue = (TextView)dialog.findViewById(R.id.textViewAttendeeRoom);
        textViewBuildding = (TextView)dialog.findViewById(R.id.textViewAttendeeBuilding);
        textViewName.append(" " + name[position]);
        textViewBuildding.append(" " + building[position]);
        textViewTrack.append(" " + track[position]);
        textViewVenue.append(" " + venue[position]);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
