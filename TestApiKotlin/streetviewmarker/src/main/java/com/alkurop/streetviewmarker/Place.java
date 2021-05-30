package com.alkurop.streetviewmarker;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcelable;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.maps.model.LatLng;

public interface Place extends Parcelable {
    @NonNull
    String getId();

    @NonNull LatLng getLocation();

    @Nullable
    String getMarkerPath();

    @DrawableRes
    int getDrawableRes();

    @Nullable Bitmap getBitmap(Context context, int distanceMeters);
}
