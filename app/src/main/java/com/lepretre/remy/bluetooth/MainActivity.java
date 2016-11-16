package com.lepretre.remy.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    //code de retour pour l'appel à la permission du bluetooth
    private final static int REQUEST_CODE_ENABLE_BLUETOOTH = 0;

    //collection de device avec bluetooth connu par l'appareil
    private Set<BluetoothDevice> friendDevices;

    //liste de devices inconnu de l'appareil
    private List<BluetoothDevice> unknowDevices = new ArrayList<BluetoothDevice>();

    //variable pour manipuler le bluetooth
    protected BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    //pour récupérer les appareils dont le bluetooth est activé mais dont on est pas encore appairé avec
    private BroadcastReceiver bluetoothReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Détecter si le bluetooth est disponible*/
        /*if (bluetoothAdapter == null)
            Toast.makeText(this, "Pas de Bluetooth", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Avec Bluetooth", Toast.LENGTH_SHORT).show();*/

        /*forcer le démarrage du bluetooth*/
        /*if (!bluetoothAdapter.isEnabled())
            bluetoothAdapter.enable();*/

        /*demander gentiment à l'utilisateur s'il veut bien activer le bluetooth*/
        /*if (!bluetoothAdapter.isEnabled()) {
            Intent enableBlueTooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBlueTooth, REQUEST_CODE_ENABLE_BLUETOOTH);
        }*/

        //récupère la liste des devices auxquels l'appareil est appairé
        /*friendDevices = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice blueDevice : devices) {
            Toast.makeText(TutoBluetoothActivity.this, "Device = " + blueDevice.getName(), Toast.LENGTH_SHORT).show();
        }*/

        //on récupère les appareils dont on est pas encore appairé pour les afficher
        /*bluetoothReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if(!unknowDevices.contains(device)) {
                        unknowDevices.add(device);
                        Toast.makeText(MainActivity.this, "New Device = " + unknowDevices.size(), Toast.LENGTH_SHORT).show();
                        String[] unknowDevicesName = new String[unknowDevices.size()];
                        for(int i = 0; i<unknowDevices.size(); i++) {
                            unknowDevicesName[i] = unknowDevices.get(i).getName();
                        }

                        ListView listview = (ListView) findViewById(R.id.listview);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, unknowDevicesName);
                        listview.setAdapter(adapter);
                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                                Toast.makeText(MainActivity.this, "Device Name = " + unknowDevices.get(position).getName(), Toast.LENGTH_SHORT).show();
                                //new ClientBluetooth(unknowDevices.get(position)).run();
                            }
                        });
                    }
                }
            }
        };*/

        /*IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(bluetoothReceiver, filter);
        bluetoothAdapter.startDiscovery();*/

        /*Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);
        startActivity(discoverableIntent);*/
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //vérifie que c'est bien la demande d'activation du bluetooth que l'on va gérer
        if (requestCode != REQUEST_CODE_ENABLE_BLUETOOTH)
            return;

        if (resultCode == RESULT_OK)
            Toast.makeText(this, "Bluetooth activer", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Bluetooth échouer", Toast.LENGTH_SHORT).show();

    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bluetoothAdapter.cancelDiscovery();
        unregisterReceiver(bluetoothReceiver);
    }

}
