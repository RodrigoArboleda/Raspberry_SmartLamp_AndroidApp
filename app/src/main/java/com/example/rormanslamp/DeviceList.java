package com.example.rormanslamp;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Set;

/*
Get all devices MACAddress and show a list.
*/
public class DeviceList extends ListActivity {


    static String deviceMACAddress = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BluetoothAdapter bluetoothAdapter;

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //array to save devices
        ArrayAdapter<String> arrayBluetooth = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        //get devices
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {

            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                arrayBluetooth.add(deviceName + "\n" + deviceHardwareAddress);
            }
        }

        //show list
        setListAdapter(arrayBluetooth);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //Get text of view
        String info_deviceAll = ((TextView) v).getText().toString();

        //Get MACAddress of text
        String MACAddress = info_deviceAll.substring(info_deviceAll.length() - 17);

        Intent returnMAC = new Intent();

        returnMAC.putExtra(deviceMACAddress, MACAddress);
        setResult(RESULT_OK, returnMAC);
        finish();

    }
}