package com.j_hawk.whattoplay;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;


import com.j_hawk.whattoplay.data.DBHelper;

import java.util.ArrayList;


public class filterpage extends AppCompatActivity {

    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.localsearching);
        dbHelper = new DBHelper(getApplicationContext());
        ArrayList<String> mechanics =  dbHelper.getAllMechanics();
        ArrayList<String> categories = dbHelper.getAllCategories();
        for(int i = 0; i < mechanics.size(); i++){
            Log.i("abc",mechanics.get(i));
        }
        LayoutInflater Minflater = getLayoutInflater();
        LayoutInflater Cinflater = getLayoutInflater();
        Context context = getBaseContext();
        MechanicsItemAdapter myMitemadapter = new MechanicsItemAdapter(Minflater,mechanics);
        CategoriesItemAdapter myCitemadepter = new CategoriesItemAdapter(Cinflater,categories);
        ListView mechanlv = (ListView) findViewById(R.id.machenics);
        ListView categorylv = (ListView) findViewById(R.id.categorylv);

        ViewGroup.LayoutParams paramsM = mechanlv.getLayoutParams();
        int totalHeight1 = 0;
        for (int i = 0; i < myMitemadapter.getCount(); i++) {
            View listItem = myMitemadapter.getView(i, null, mechanlv);
            listItem.measure(0, 0);
            totalHeight1 += listItem.getMeasuredHeight();
        }
        paramsM.height = totalHeight1 + (mechanlv.getDividerHeight() * (myMitemadapter.getCount() - 1));
        mechanlv.setLayoutParams(paramsM);

        ViewGroup.LayoutParams paramsC = categorylv.getLayoutParams();
        int totalHeight2 = 0;
        for (int i = 0; i < myCitemadepter.getCount(); i++) {
            View listItem = myCitemadepter.getView(i, null, categorylv);
            listItem.measure(0, 0);
            totalHeight2 += listItem.getMeasuredHeight();
        }
        paramsC.height = totalHeight2 + (categorylv.getDividerHeight() * (myCitemadepter.getCount() - 1));
        categorylv.setLayoutParams(paramsC);
        categorylv.setAdapter(myCitemadepter);
        mechanlv.setAdapter(myMitemadapter);
        Button searchGO = (Button) findViewById(R.id.start);

        Spinner numberofplayer = (Spinner) findViewById(R.id.numberofplayer);
        ArrayAdapter<CharSequence> adapter_players = ArrayAdapter.createFromResource(context,
                R.array.number, android.R.layout.simple_spinner_item);
        adapter_players.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberofplayer.setAdapter(adapter_players);

        Spinner age = (Spinner) findViewById(R.id.age);
        ArrayAdapter<CharSequence> adapter_age = ArrayAdapter.createFromResource(context,
                R.array.age, android.R.layout.simple_spinner_item);
        adapter_age.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age.setAdapter(adapter_age);

        Spinner time = (Spinner) findViewById(R.id.time);
        ArrayAdapter<CharSequence> adapter_time = ArrayAdapter.createFromResource(context,
                R.array.time, android.R.layout.simple_spinner_item);
        adapter_time.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time.setAdapter(adapter_time);

        CheckBox numberRecomm = (CheckBox) findViewById(R.id.numberrecomm);
        numberRecomm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        CheckBox ageRecomm = (CheckBox) findViewById(R.id.agerecomm);
        searchGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public static class MechanicsItemAdapter extends BaseAdapter {
        private ArrayList<String> mitem;
        private LayoutInflater minflater;
        private boolean check;

        public MechanicsItemAdapter(LayoutInflater inflater, ArrayList<String> items) {
            mitem = items;
            minflater = inflater;
            check = false;

        }

        @Override
        public int getCount() {
            return mitem.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View viewInformation = minflater.inflate(R.layout.checkbox_item, null);
            String item = mitem.get(i);
            CheckBox cb = viewInformation.findViewById(R.id.type);
            cb.setText(item);
            check = cb.isChecked();
            return viewInformation;
        }
    }
    public static class CategoriesItemAdapter extends BaseAdapter {
        private ArrayList<String> mitem;
        private LayoutInflater minflater;


        public CategoriesItemAdapter(LayoutInflater inflater, ArrayList<String> items) {
            mitem = items;
            minflater = inflater;
        }

        @Override
        public int getCount() {
            return mitem.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View viewInformation = minflater.inflate(R.layout.checkbox_item, null);
            String item = mitem.get(i);
            CheckBox cb = viewInformation.findViewById(R.id.type);
            cb.setText(item.toString());

            return viewInformation;
        }
    }
}
