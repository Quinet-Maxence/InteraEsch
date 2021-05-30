package com.example.testapikotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback
import com.google.android.gms.maps.StreetViewPanorama
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.StreetViewPanoramaCamera
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation
import com.google.android.gms.maps.model.StreetViewSource

class StreetViewFragment : Fragment(), OnStreetViewPanoramaReadyCallback {

    private val streetViewPanorama: StreetViewPanorama? = null
    private var secondlocation = false
    var lion_head: ImageButton? = null
    var lion_sleep: ImageButton? = null
    var text_helper: ImageView? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var v : View = inflater.inflate(R.layout.fragment_street_view, container, false)

        lion_head = v.findViewById(R.id.imageButton4)
        lion_sleep = v.findViewById(R.id.imageButton)
        text_helper = v.findViewById(R.id.imageView5)

        lion_head!!.visibility = View.INVISIBLE
        text_helper!!.visibility = View.INVISIBLE

        lion_sleep!!.setOnClickListener {
            lion_sleep!!.visibility = View.INVISIBLE
            lion_head!!.visibility = View.VISIBLE
            text_helper!!.visibility = View.VISIBLE
            lion_head!!.setOnClickListener {
                lion_sleep!!.visibility = View.VISIBLE
                lion_head!!.visibility = View.INVISIBLE
                text_helper!!.visibility = View.INVISIBLE
            }
        }
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val streetViewPanoramaFragment: SupportStreetViewPanoramaFragment = childFragmentManager.findFragmentById(R.id.googleMapsStreetView) as SupportStreetViewPanoramaFragment
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this)
    }

    override fun onStreetViewPanoramaReady(streetViewPanorama: StreetViewPanorama) {

        // Belval : 49.506848684362524, 5.948468007751615
        if (secondlocation) {
            streetViewPanorama?.setPosition(LatLng(49.506848684362524, 5.948468007751615), StreetViewSource.OUTDOOR)
        } else {
            streetViewPanorama?.setPosition(LatLng(49.506848684362524, 5.948468007751615))
        }
        streetViewPanorama.isStreetNamesEnabled = true
        streetViewPanorama.isPanningGesturesEnabled = true
        streetViewPanorama.isZoomGesturesEnabled = true
        streetViewPanorama.isUserNavigationEnabled = true
        streetViewPanorama.animateTo(
                StreetViewPanoramaCamera.Builder().orientation(StreetViewPanoramaOrientation(20.toFloat(), 20.toFloat())).zoom(streetViewPanorama.getPanoramaCamera().zoom).build(), 2000
        )

        streetViewPanorama?.setOnStreetViewPanoramaCameraChangeListener(panoramachangelistner)

    }

    private val panoramachangelistner = StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener {
        fun onStreetViewPanoramaCameraChange(streetViewPanoramaCamera: StreetViewPanoramaCamera?) {
            Toast.makeText(context, "Location updated", Toast.LENGTH_SHORT).show()

            }
        }
    }


