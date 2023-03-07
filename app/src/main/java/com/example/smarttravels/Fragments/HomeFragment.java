package com.example.smarttravels.Fragments;


import static com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smarttravels.MainActivity;
import com.example.smarttravels.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class HomeFragment extends Fragment {
    Context myContext;
    private FusedLocationProviderClient fusedLocationClient;
    Button b1;
    TextView latitude;
    TextView longitude;
    public static final int PERMISSION_GOT = 7;

    public HomeFragment(Context myContext) {
        this.myContext = myContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(myContext);


        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        if (view != null) {
            b1 = (Button) view.findViewById(R.id.locationButton);
            latitude = (TextView) getView().findViewById(R.id.locationlat);
            longitude = (TextView) getView().findViewById(R.id.locationlong);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (ActivityCompat.checkSelfPermission(myContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(myContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION);
                        // here to request the missing permissions, and then overriding
                        // public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.

                        // other 'case' lines to check for other
                        // permissions this app might request


                        return;
                    }else {
                        Task<Location> locationTask = fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY, null)
                                .addOnSuccessListener((OnSuccessListener<Location>) location -> {
                                    // Got last known location. In some rare situations this can be null.
                                    if (location != null) {
                                        latitude.setText("Latitude :" + location.getLatitude());
                                        longitude.setText("Longitude :" + location.getLongitude());
                                    }
                                });
                    }
                }
            });
        }
    }
    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                } else {
                    ActivityCompat.requestPermissions((Activity) myContext, new String[] { Manifest.permission.ACCESS_COARSE_LOCATION }, 1);// Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            });
}

