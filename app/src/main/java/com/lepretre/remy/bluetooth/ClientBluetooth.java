package com.lepretre.remy.bluetooth;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;

public class ClientBluetooth extends Thread {
    private final BluetoothSocket blueSocket;
    private final BluetoothDevice blueDevice;
    BluetoothAdapter blueAdapter = BluetoothAdapter.getDefaultAdapter();

    public ClientBluetooth(BluetoothDevice device) {
        // On utilise un objet temporaire car blueSocket et blueDevice sont "final"
        BluetoothSocket tmp = null;
        blueDevice = device;

        // On récupère un objet BluetoothSocket grâce à l'objet BluetoothDevice
        try {
            // MON_UUID est l'UUID (comprenez identifiant serveur) de l'application. Cette valeur est nécessaire côté serveur également !
            tmp = device.createRfcommSocketToServiceRecord(ServeurBluetooth.uuid);
        } catch (IOException e) { }
        blueSocket = tmp;
    }

    public void run() {
        // On annule la découverte des périphériques (inutile puisqu'on est en train d'essayer de se connecter)
        blueAdapter.cancelDiscovery();

        try {
            // On se connecte. Cet appel est bloquant jusqu'à la réussite ou la levée d'une erreur
            blueSocket.connect();
        } catch (IOException connectException) {
            // Impossible de se connecter, on ferme la socket et on tue le thread
            try {
                blueSocket.close();
            } catch (IOException closeException) { }
            return;
        }

        // Utilisez la connexion (dans un thread séparé) pour faire ce que vous voulez
        new ServeurBluetooth().run();
    }

    // Annule toute connexion en cours et tue le thread
    public void cancel() {
        try {
            blueSocket.close();
        } catch (IOException e) { }
    }
}

