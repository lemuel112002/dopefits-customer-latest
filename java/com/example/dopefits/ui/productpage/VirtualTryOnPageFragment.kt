package com.example.dopefits.ui.productpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.dopefits.R

class VirtualTryOnPageFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Handle any arguments if necessary
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_virtual_try_on_page, container, false)

        // Find the back button and set a click listener
        val backButton: Button = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            // Navigate back to HomeFragment
            findNavController().navigate(R.id.nav_home)
        }

        // Find the ImageView and set a click listener
        val imageViewOne: ImageView = view.findViewById(R.id.imageViewOne)
        imageViewOne.setOnClickListener {
            // Navigate to YellowHoodieFragment
            findNavController().navigate(R.id.yellowHoodieFragment)
        }

        val imageViewTwo: ImageView = view.findViewById(R.id.imageViewTwo)
        imageViewTwo.setOnClickListener {
            // Navigate to YellowHoodieFragment
            findNavController().navigate(R.id.oliveGreenTshirtFragment)
        }

        val imageViewThree: ImageView = view.findViewById(R.id.imageViewThree)
        imageViewThree.setOnClickListener {
            // Navigate to BrownSweatshirtFragment
            findNavController().navigate(R.id.brownSweatshirtFragment)
        }

        val imageViewFour: ImageView = view.findViewById(R.id.imageViewFour)
        imageViewFour.setOnClickListener {
            // Navigate to VarsityJacketFragment
            findNavController().navigate(R.id.varsityJacketFragment)
        }

        val imageViewFive: ImageView = view.findViewById(R.id.imageViewFive)
        imageViewFive.setOnClickListener {
            // Navigate to WhiteLongSleeveFragment
            findNavController().navigate(R.id.blackPufferJacketFragment)
        }

        return view
    }

}
