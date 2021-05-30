package com.example.testapikotlin;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.testapikotlin.Building.BasicFit;
import com.example.testapikotlin.Building.BelvalPlaza;
import com.example.testapikotlin.Building.LearningCenter;
import com.example.testapikotlin.Building.UniversityBelval;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * This is the google map fragment
 * with markers implemented to show where are visitable buildings
 * We use clickable markers, to point at building and to get a description of them.
 */

public class FragmentMap extends Fragment implements OnMapReadyCallback{

    /**
     * To create and modify a fragment we must use mainly 3 fragments methods and override their content following this guideline:
     * Creating a Fragment uses "onCreate" to setup xml buttons and features. (Unnecessary here)
     * then "onCreateView" to setup the view
     * then onViewCreated to setup or modify everything else
     *
     * the order is important and thus must be respected.
     */

    SupportMapFragment mapFragment;
    private Button button;

    /** List of Markers */
    private Marker university;
    private Marker plaza;
    private Marker basicFit;
    private Marker learningCenter;

    /**
     * Text_helper for set visibility
     */

    ImageButton lion_head;
    ImageButton lion_sleep;
    ImageView text_helper;

    ImageButton mapButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){

        View v = inflater.inflate(R.layout.fragment_map, container, false);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_view);

        lion_head = (ImageButton) v.findViewById(R.id.imageButton3);
        lion_sleep = (ImageButton) v.findViewById(R.id.imageButton2);
        text_helper = (ImageView) v.findViewById(R.id.imageView4);

        mapButton = (ImageButton) v.findViewById(R.id.mapButton);

        lion_head.setVisibility(View.INVISIBLE);
        text_helper.setVisibility(View.INVISIBLE);

        lion_sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lion_sleep.setVisibility(View.INVISIBLE);
                lion_head.setVisibility(View.VISIBLE);
                text_helper.setVisibility(View.VISIBLE);

                lion_head.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lion_sleep.setVisibility(View.VISIBLE);
                        lion_head.setVisibility(View.INVISIBLE);
                        text_helper.setVisibility(View.INVISIBLE);

                    }
                });
            }
        });

        if(mapFragment == null){

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map_view, mapFragment).commit();

        }
        mapFragment.getMapAsync(this);


        return v;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng start = new LatLng(49.5058888466625,5.947077193647345 );

        /** Délimitation de la zone de la map */
        LatLngBounds belvalBounds = new LatLngBounds(
                new LatLng(49.49155633363456,5.918539698174152), // SW bounds
                new LatLng(49.5139730886897,5.996387440635127) // NE bounds
        );

        // Localisation de départ quand on lance le map Google
        //  googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(start, 15));

        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(belvalBounds, 30));
            }
        });

        /** Centrer la map sur une zone donnée */
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(belvalBounds.getCenter(), 15));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(belvalBounds,1));

        /** initialisation des balise marker */
        university = googleMap.addMarker(new MarkerOptions().position(new LatLng(49.50410998735133,5.949007832384585)).title("University"));
        university.setTag(0);
        university.setTitle("University");
        plaza = googleMap.addMarker(new MarkerOptions().position(new LatLng(49.50048920749708,5.94613052124096)).title("Belval Plaza"));
        plaza.setTag(0);
        plaza.setTitle("plaza");
        basicFit = googleMap.addMarker(new MarkerOptions().position(new LatLng(49.501568500327274,5.942979459994788)).title("Belval Plaza"));
        basicFit.setTag(0);
        basicFit.setTitle("basicFit");
        learningCenter = googleMap.addMarker(new MarkerOptions().position(new LatLng(49.5023983342443,5.9472881609491735)).title("Belval Plaza"));
        learningCenter.setTag(0);
        learningCenter.setTitle("learningCenter");


        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                switch(marker.getTitle()){
                    case "University":
                        Intent intent1 = new Intent(FragmentMap.this.getActivity(), UniversityBelval.class);
                        startActivity(intent1);
                        break;
                    case "plaza":
                        Intent intent2 = new Intent(FragmentMap.this.getActivity(), BelvalPlaza.class);
                        startActivity(intent2);
                        break;
                    case "basicFit":
                        Intent intent3 = new Intent(FragmentMap.this.getActivity(), BasicFit.class);
                        startActivity(intent3);
                        break;
                    case "learningCenter":
                        Intent intent4 = new Intent(FragmentMap.this.getActivity(), LearningCenter.class);
                        startActivity(intent4);
                        break;
                }
                return false;
            }
        });

        /** Maximum & Minimum Zoom Possible */
        googleMap.setMinZoomPreference(10);
        googleMap.setMaxZoomPreference(17);

        /** Differents types de map */
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mapButton.setOnClickListener(new View.OnClickListener() {
            int mapNumber = 0;
            @Override
            public void onClick(View v) {
                switch (mapNumber) {
                    case 0:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        mapNumber = 1;
                        break;
                    case 1:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        mapNumber = 2;
                        break;
                    case 2:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        mapNumber = 3;
                        break;
                    case 3:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                        mapNumber = 4;
                        break;

                    case 4:
                        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        mapNumber = 0;
                        break;
                }
            }
        });

    }


}
