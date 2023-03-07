package com.example.smarttravels.Fragments;


import static com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY;

import android.Manifest;
import android.content.Context;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
                        // here to request the missing permissions, and then overriding
                        // public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    Task<Location> locationTask = fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY,null)
                            .addOnSuccessListener((OnSuccessListener<Location>) location -> {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
                                    latitude.setText("Latitude :"+location.getLatitude());
                                    longitude.setText("Longitude :"+location.getLongitude());
                                }
                            });
            }
        });
        }
    }


}