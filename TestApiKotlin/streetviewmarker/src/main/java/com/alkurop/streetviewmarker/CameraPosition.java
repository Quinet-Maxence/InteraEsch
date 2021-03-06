package com.alkurop.streetviewmarker;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public class CameraPosition {
    public final LatLng location;
    public final StreetViewPanoramaCamera camera;

    public CameraPosition (LatLng center, StreetViewPanoramaCamera camera) {
        this.location = center;
        this.camera = camera;
    }

    @Override
    public String toString () {
        return "UpdatePosition{" +
                "location=" + location +
                ", camera=" + camera +
                '}';
    }
}
