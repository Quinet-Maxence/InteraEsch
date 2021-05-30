package com.example.testapikotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toolbar

/**
 * This is the home page fragment that you see just after the loading
 * It is a simple non interactive fragment, that has animations for the beauty of it
 */

class BlankFragment : Fragment() {

    /**
     * To create and modify a fragment we must use mainly 3 fragment methods and override their content following this guideline:
     * Creating a Fragment uses "onCreate" to setup xml buttons and features. (Unnecessary here)
     * then "onCreateView" to setup the view
     * then onViewCreated to setup or modify everything else
     *
     * the order is important and thus must be respected.
     */

    /** Initialize our custom made animations & image */


    lateinit var animation: Animation
    lateinit var view: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /** Inflate the layout for this fragment **/
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** loading our custom made animations & image */
        animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        this.view = requireView().findViewById(R.id.homedesign)
        this.view.animation = animation
    }
}