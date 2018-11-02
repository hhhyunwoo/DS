package com.example.qgusd.swapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    ArrayList<String> Items;
    ArrayAdapter<String> Adapter;
    ListView listView;
    Button btnAdd;
    EditText editText;
    EditText editNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        listView = (ListView)findViewById(R.id.listview);
        Items = new ArrayList<String>();

        Items.add("지인 1");
        Items.add("지인 2");
        Items.add("지인 3");
        Items.add("지인 4");
        Items.add("지인 5");

        Adapter = new ArrayAdapter<String>(this,
                R.layout.namelist,
                R.id.name,
                Items);
        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int position, long id) {
                String strText = (String) adapterView.getItemAtPosition(position);

                Intent intent = new Intent(RegisterActivity.this, NameList.class);
                startActivity(intent);
            }
        });
    }
}
