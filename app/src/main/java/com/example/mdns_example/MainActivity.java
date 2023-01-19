package com.example.mdns_example;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private NsdManager mNsdManager;
    ListView listView;
    String serviceName;
    private NsdManager.DiscoveryListener mDiscoveryListener;
    StringBuilder sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.scanBtn);
        sb = new StringBuilder();
        listView=findViewById(R.id.wifiList);
        ArrayList<StringBuilder> deviceList = new ArrayList<>();
        NsdServiceInfo serviceInfo = new NsdServiceInfo();
        serviceInfo.setServiceName("NsdChat");
        serviceInfo.setServiceType("_http._tcp.");
        serviceInfo.setPort(1337);




        mNsdManager = (NsdManager) getSystemService(Context.NSD_SERVICE);
        mDiscoveryListener = new NsdManager.DiscoveryListener() {
            @Override
            public void onDiscoveryStarted(String serviceType) {
                // Discovery has started
                Log.d("NSD", "Discovery started");
            }
            @Override
            public void onServiceFound(NsdServiceInfo serviceInfo) {
                // A service was found
                Log.d("NSD", "Service found: " + serviceInfo.getServiceName());
                Toast.makeText(MainActivity.this, serviceInfo.getServiceName(), Toast.LENGTH_SHORT).show();
               //sb.append(serviceInfo.getServiceName());
            }

            @Override
            public void onServiceLost(NsdServiceInfo serviceInfo) {
                // A service was lost
                Log.d("NSD", "Service lost: " + serviceInfo.getServiceName());
            }

            @Override
            public void onDiscoveryStopped(String serviceType) {
                // Discovery has stopped
                Log.d("NSD", "Discovery stopped");
            }

            @Override
            public void onStartDiscoveryFailed(String serviceType, int errorCode) {
                // Discovery failed to start
                Log.d("NSD", "Discovery failed to start");
            }

            @Override
            public void onStopDiscoveryFailed(String serviceType, int errorCode) {
                // Discovery failed to stop
                Log.d("NSD", "Discovery failed to stop");
            }
        };



    }



}