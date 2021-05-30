package com.example.testapikotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class ContactFragment : Fragment() {

    /**
     * To create and modify a fragment we must use mainly 3 fragments methods and override their content following this guideline:
     * Creating a Fragment uses "onCreate" to setup xml buttons and features. (Unnecessary here)
     * then "onCreateView" to setup the view
     * then onViewCreated to setup or modify everything else
     *
     * the order is important and thus must be respected.
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

}