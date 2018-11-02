package com.example.qgusd.swapp;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    ListView bluetoothList = null;
    public static final String BT_PREFERENCES = "BtPrefs";
    public static final String BP_PREFERENCES_PAIRED_DEVICE = "SELECTED_DEVICE"; // String
    public static boolean bluetoothcnonnected = false;
    public static String bluetooth_connectdevice;
    BluetoothAdapter mBluetoothAdapter;
    int temp, temp2,temp3;

    final int PERMISSION=1;
    static Handler myHandler;
    //list_item my_list_item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        Button bluebutton = (Button)findViewById(R.id.btn_paired);
        bluebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
                if (pairedDevices.size() > 0) {
                    final String[] bluetoothdevice = new String[pairedDevices.size()];
                    int i = 0;
                    for (BluetoothDevice device : pairedDevices) {
                        bluetoothdevice[i++] = device.getName();
                    }
                    bluetoothList = new ListView(BluetoothActivity.this);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(BluetoothActivity.this, R.layout.bluetooth_list, R.id.bluetoothdevice, bluetoothdevice);
                    bluetoothList.setAdapter(adapter);
                    bluetoothList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            String strText = bluetoothdevice[position];
                            if(bluetoothcnonnected == false){
                                listclick(bluetoothdevice, position,strText);
                                bluetooth_connectdevice = strText;
                            }
                            else if(bluetoothcnonnected == true){
                                if(bluetooth_connectdevice == strText)
                                {
                                    //연결되어있으면 되어있다고 메세지
                                    AlertDialog.Builder builder = new AlertDialog.Builder(BluetoothActivity.this);
                                    builder.setCancelable(true);
                                    builder.setPositiveButton("OK", null);
                                    builder.setMessage(strText + "에 이미 연결 되어있습니다.");
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }
                                else
                                {
                                    listclick(bluetoothdevice, position,strText);
                                    bluetooth_connectdevice = strText;
                                }
                            }
                        }
                    });
                    AlertDialog.Builder builder = new AlertDialog.Builder(BluetoothActivity.this);
                    builder.setCancelable(true);
                    builder.setView(bluetoothList);
                    builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Activity 에서 대화상자를 닫도록 메소드를 호출한다.
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }
    protected void listclick(String[] bluetoothdevice , int position, String strText){
        SharedPreferences mPairedSettings;
        mPairedSettings = getSharedPreferences(BT_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPairedSettings.edit();
        editor.putString(BP_PREFERENCES_PAIRED_DEVICE, strText);
        editor.commit();
        final String dName=strText;
        //블루투스 확인 메시지 다이얼로그 띄우기
        AlertDialog.Builder builder = new AlertDialog.Builder(BluetoothActivity.this);
        builder.setCancelable(true);
        builder.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Activity 에서 대화상자를 닫도록 메소드를 호출한다.

                if(!mBluetoothAdapter.isEnabled())
                {
                    Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBluetooth, 0);
                }
                Bluetooth.getInstance().setDevicename(dName);
                bluetoothcnonnected = true;

                Intent intent = new Intent(getApplicationContext(), ArduinoService.class);
                startService(intent);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Activity 에서 대화상자를 닫도록 메소드를 호출한다.
                dialog.cancel();
            }
        });
        builder.setTitle("확인");
        builder.setMessage(strText + "에 블루투스 연결을 하겠습니까?");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
