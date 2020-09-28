package com.example.rormanslamp;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

/*
Main activity create a screen to user chose one lamp.
*/
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_CONNECT = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button connectButton = findViewById(R.id.buttonConnect);

        //get adapter bluetooth, if it does not have bluetooth adapter, return null
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Test bluetooth adapter
        if (bluetoothAdapter == null){
            Toast.makeText(getApplicationContext(), "Dispositivo não possui Bluetooth", Toast.LENGTH_LONG).show();
            finish();
        }

        //Ask to enable bluetooth
        else if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        // Button "Conectar"
        connectButton.setOnClickListener(v -> {
                Intent starList = new Intent(MainActivity.this, DeviceList.class);
                startActivityForResult(starList, REQUEST_CONNECT);
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            // Check results of bluetooth
            case REQUEST_ENABLE_BT:

                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(getApplicationContext(), "Bluetooth Ativado.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Para usar o aplicativo o Bluetooth precisar estar ativado.", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;

            // Check results of MAC
            case REQUEST_CONNECT:

                if (resultCode == Activity.RESULT_OK && data != null) {
                    String MAC_Bluetooth = Objects.requireNonNull(data.getExtras()).getString(DeviceList.deviceMACAddress);

                    Intent colorAct = new Intent(MainActivity.this, ColorSelect.class);
                    colorAct.putExtra("MAC_Bluetooth", MAC_Bluetooth);

                    startActivity(colorAct);
                } else {
                    Toast.makeText(getApplicationContext(), "Falha ao obter o MAC", Toast.LENGTH_LONG).show();
                }

                break;
            // case error
            default:
                Toast.makeText(getApplicationContext(), "Erro interno", Toast.LENGTH_LONG).show();
                finish();
        }
    }
}
