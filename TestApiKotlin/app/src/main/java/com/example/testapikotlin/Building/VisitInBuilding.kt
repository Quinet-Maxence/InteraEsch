package com.example.testapikotlin.Building

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import FPView
import android.content.Context
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.testapikotlin.R
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class VisitInBuilding : AppCompatActivity() {

    lateinit var getlocation : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visit_in_building)


        /**
         * variable initialisation
         */
        getlocation =  getString(R.string.fp_view_location)
        val firstPersonView: FPView = FPView('N', 1, getlocation, this)
        val view: ImageView = findViewById(R.id.FPView)
        val rButton: Button = findViewById(R.id.goRight)
        val lButton: Button = findViewById(R.id.goLeft)
        val goButton: ImageButton = findViewById(R.id.goforward)

        /**
         * Button usage initialisation
         */

        rButton.setOnClickListener {
            firstPersonView.turnRight()
            Picasso.get().load(firstPersonView.displayID()).rotate(90F).into(view)
            if (firstPersonView.checkDestination()) goButton.visibility = View.VISIBLE
            else goButton.visibility = View.INVISIBLE
        }
        lButton.setOnClickListener {
            firstPersonView.turnLeft()
            Picasso.get().load(firstPersonView.displayID()).rotate(90F).into(view)
            if (firstPersonView.checkDestination()) goButton.visibility = View.VISIBLE
            else goButton.visibility = View.INVISIBLE
        }
        goButton.setOnClickListener {
            firstPersonView.goForward()
            Picasso.get().load(firstPersonView.displayID()).rotate(90.0F).into(view)
            if (firstPersonView.checkDestination()) goButton.visibility = View.VISIBLE
            else goButton.visibility = View.INVISIBLE
        }

    }
}

