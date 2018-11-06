package com.example.qgusd.swapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.ContextCompat;
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

    static ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        ListView listView;

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem("김현우", "010-9845-5632") ;
        // 두 번째 아이템 추가.
        adapter.addItem("지인2", "000-0000-0000") ;
        // 세 번째 아이템 추가.
        adapter.addItem("지인3", "000-0000-0000") ;
        adapter.addItem("지인4", "000-0000-0000") ;
        adapter.addItem("지인5", "000-0000-0000") ;



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) adapterView.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;

                Intent intent = new Intent(RegisterActivity.this, NameList.class);
                startActivity(intent);
            }
        });
    }
}
