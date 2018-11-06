package com.example.qgusd.swapp;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class NameList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_list);

        Button pressButton = (Button)findViewById(R.id.btn_add);
        final EditText editName = (EditText)findViewById(R.id.editName);
        final EditText editNum = (EditText)findViewById(R.id.editNum);

        pressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.adapter.addItem(editName.getText().toString(),
                        editNum.getText().toString());
            }
        });
    }
}
