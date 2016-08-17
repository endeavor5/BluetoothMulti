package org.sgnhi.yevgeny.bluetoothmulti;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //bluetooth class
    BluetoothAdapter bluetoothAdapter;
    ArrayList<BluetoothSocket> bluetoothSocketArrayList = null;
    //view class
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        textView.setText("");
        bluetoothSocketArrayList = new ArrayList<>();
        for(BluetoothDevice device : bluetoothAdapter.getBondedDevices()){
            textView.setText(textView.getText() + device.getName() + " : " + device.getAddress().toString() + "\n");
            try {
                bluetoothSocketArrayList.add(device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0;i < bluetoothSocketArrayList.size(); i++){
            try {
                bluetoothSocketArrayList.get(i).connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
